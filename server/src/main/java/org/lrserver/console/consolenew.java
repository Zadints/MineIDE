package org.lrserver.console;

import org.lrserver.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class consolenew {
    public static void start() throws Exception{
        Thread consola = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("console/server>");
                String line = sc.nextLine();
                String[] parts = line.split(" ", 3);
                String command = parts[0];
                switch (command) {
                    case ("add"):
                        if (parts.length > 1){
                            String soft = parts[1];
                            String vers = parts[2];
                            while (true){
                                System.out.println("add/content>");
                                String lineadd = sc.nextLine();
                                String[] partsadd = lineadd.split(" ", 2);
                                try (Connection con = Database.connect("download_links");
                                     PreparedStatement order = con.prepareStatement("INSERT INTO " + soft + "(version, build, link) VALUES (vers, ?, ?)")) {
                                    order.setString(1, partsadd[1]);
                                    order.setString(2, partsadd[2]);
                                }catch (Exception e) {
                                    System.out.println("error with console conection ");
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
