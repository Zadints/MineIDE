package org.lrserver.console;

import org.lrserver.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class console{
    public static void start() throws Exception{
        Thread consola = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("console/server>");
                String line = sc.nextLine();
                String[] parts = line.split(" ", 5);
                String command = parts[0];
                switch (command) {
                    case ("add"):
                        if (parts.length > 1){
                            if (!parts[1].equals("menu")) {
                                String soft = parts[1];
                                try (Connection con = Database.connect("download_links");
                                     PreparedStatement order = con.prepareStatement("INSERT INTO " + soft + "(version, build, link) VALUES (?, ?, ?)")) {
                                    if (parts.length > 2) {
                                        order.setString(1, parts[2]);

                                    } else {
                                        System.out.println("ingres number of version");
                                    }
                                    if (parts.length > 3) {
                                        order.setString(2, parts[3]);

                                    } else {
                                        System.out.println("ingres number of build");
                                    }
                                    if (parts.length > 4) {
                                        order.setString(3, parts[4]);
                                        order.executeUpdate();
                                    } else {
                                        System.out.println("ingres the link of archive");
                                    }
                                } catch (Exception e) {
                                    System.out.println("error with console conection ");
                                }
                            }else {
                                if (parts.length < 4){
                                    System.out.println("uso correcto 'add menu <software> <versionMc>");
                                    break;
                                }
                                String soft = parts[2];
                                String vers = parts[3];
                                while (true) {
                                    System.out.println("add/content>");
                                    String lineadd = sc.nextLine();
                                    String[] partsadd = lineadd.split(" ", 2);
                                    if (!partsadd[0].equals("exit")) {
                                        try (Connection con = Database.connect("download_links");
                                             PreparedStatement order = con.prepareStatement("INSERT INTO " + soft + "(version, build, link) VALUES (?, ?, ?)")) {
                                            order.setString(1, vers);
                                            order.setString(2, partsadd[0]);
                                            order.setString(3, partsadd[1]);
                                            order.executeUpdate();
                                            System.out.println("operacion concluida con exito");
                                        } catch (Exception e) {
                                            System.out.println("error with console conection ");
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }else {
                            System.out.println("please ingres the name of the software");

                        }
                }
            }
        });
        consola.start();
    }
}
