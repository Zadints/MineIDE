package org.example.mineide.infraestructure.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ServerManager {

    private volatile Process process;
    private volatile BufferedWriter serverInput;

    public synchronized void start(
            Path javaExecutable,
            Path projectDirectory,
            Path serverJar,
            Consumer<String> onLog,
            Consumer<Boolean> onState
    ) throws IOException {

        if (process != null && process.isAlive()) {
            throw new IllegalStateException(
                    "El servidor ya está ejecutándose."
            );
        }

        if (!Files.isRegularFile(javaExecutable)) {
            throw new IOException(
                    "No se encontró el ejecutable Java: " + javaExecutable
            );
        }

        if (!Files.isDirectory(projectDirectory)) {
            throw new IOException(
                    "No existe la carpeta del proyecto: " + projectDirectory
            );
        }

        if (!Files.isRegularFile(serverJar)) {
            throw new IOException(
                    "No se encontró el JAR: " + serverJar
            );
        }

        ProcessBuilder builder = new ProcessBuilder(
                List.of(
                        javaExecutable.toAbsolutePath().toString(),
                        "-jar",
                        serverJar.toAbsolutePath().toString(),
                        "nogui"
                )
        );

        builder.directory(projectDirectory.toFile());
        builder.redirectErrorStream(true);

        process = builder.start();

        serverInput = new BufferedWriter(
                new OutputStreamWriter(
                        process.getOutputStream(),
                        StandardCharsets.UTF_8
                )
        );

        Process currentProcess = process;

        onState.accept(true);
        onLog.accept("Iniciando servidor de Minecraft...");

        Thread logThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            currentProcess.getInputStream(),
                            StandardCharsets.UTF_8
                    ))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    onLog.accept(line);
                }

                int exitCode = currentProcess.waitFor();
                onLog.accept(
                        "El servidor terminó. Código de salida: " + exitCode
                );

            } catch (IOException e) {
                onLog.accept("Error leyendo la consola: " + e.getMessage());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            } finally {
                synchronized (ServerManager.this) {
                    if (process == currentProcess) {
                        process = null;
                        serverInput = null;
                    }
                }

                onState.accept(false);
            }
        }, "MineIDE-Server-Logs");

        logThread.setDaemon(true);
        logThread.start();
    }

    public void stop(Consumer<String> onLog) {
        Process currentProcess = process;
        BufferedWriter input = serverInput;

        if (currentProcess == null || !currentProcess.isAlive()) {
            onLog.accept("El servidor no está ejecutándose.");
            return;
        }

        Thread stopThread = new Thread(() -> {
            try {
                onLog.accept("Enviando comando de apagado...");

                if (input != null) {
                    input.write("stop");
                    input.newLine();
                    input.flush();
                }

                if (!currentProcess.waitFor(15, TimeUnit.SECONDS)) {
                    onLog.accept(
                            "El servidor no respondió; finalizando el proceso..."
                    );
                    currentProcess.destroy();

                    if (!currentProcess.waitFor(5, TimeUnit.SECONDS)) {
                        currentProcess.destroyForcibly();
                    }
                }

            } catch (IOException e) {
                onLog.accept(
                        "No se pudo enviar el apagado: " + e.getMessage()
                );

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                currentProcess.destroy();
            }
        }, "MineIDE-Server-Stop");

        stopThread.setDaemon(true);
        stopThread.start();
    }

    public boolean isRunning() {
        Process currentProcess = process;
        return currentProcess != null && currentProcess.isAlive();
    }
}