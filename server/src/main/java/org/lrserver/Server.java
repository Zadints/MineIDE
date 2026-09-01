package org.lrserver;

import io.javalin.Javalin;
import com.google.gson.*;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.net.http.*;
import java.io.OutputStream;
import java.io.InputStream;
import org.lrserver.console.console;


public class Server {
    //root Lore3008@

    public static void main(String[] args) throws Exception{
        Javalin server = Javalin.create().start(8080);
        System.out.println("servidor onlin en localhost con el puerto" + server.port());
        console.start();
        Gson js = new Gson();
        server.post("/mcDownload/add", ctx ->{
           String software = ctx.header("software");
           String version = ctx.header("version");
           String build = ctx.header("build");
           String link = ctx.header("link");
           try (Connection con = Database.connect("download_links");
                PreparedStatement order = con.prepareStatement("INSERT INTO " + software +" (version, build, link) VALUES (?, ?, ?)")){
               order.setString(1, version);
               order.setString(2, build);
               order.setString(3, link);
               order.executeUpdate();
           }
        });
        server.get("/mcDownload", ctx ->{
            String software = ctx.header("software");
            String build = ctx.header("build");
            String version = ctx.header("version");
            String link = null;
            //sistema mysql
            try (Connection con = Database.connect("download_links");
                PreparedStatement order = con.prepareStatement("SELECT link FROM " + software + " WHERE build = ? AND version = ?")){
                order.setString(1, build);
                order.setString(2, version);

                ResultSet rs = order.executeQuery();
                if (rs.next()){
                    link = rs.getString("link");
                }
            }

            if (link != null) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(link))
                        .build();
                HttpResponse<InputStream> respuesta = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
                ctx.contentType("application/octet-stream");
                try (InputStream in = respuesta.body(); OutputStream salida = ctx.res().getOutputStream()){
                    in.transferTo(salida);
                }
            } else{
                System.out.println("link no encontrado intenta otra version");
                ctx.status(404).result("link no encontrado, intenta otra version");
            }
        });
    }

}

