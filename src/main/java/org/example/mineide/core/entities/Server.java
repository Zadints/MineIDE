package org.example.mineide.core.entities;

import org.example.mineide.core.enums.ServerSoftware;

import java.nio.file.Path;

public class Server {
    private String id;
    private String serverName;
    private Path directory;
    private long ram;
    private long disco;
    private long cpu;
    private short puerto;
    private Path imageServer;
    private boolean updateLastVersion;
    private ServerSoftware sofware;

}
