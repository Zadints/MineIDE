package org.lrserver;

import java.awt.desktop.SystemSleepEvent;
import java.sql.ResultSet;
import java.util.Random;
import java.io.File;
import com.google.gson.*;
import java.sql.Connection;
import java.sql.Statement;

public class apikey {
    String key;
    public static String createt() throws Exception {
        Random rdm = new Random();
        while (true) {
            String temptok = "srv-lr-token:" + rdm.nextInt(100000000, 1000000000);
            try (Connection con = Database.connect("users");
                 Statement order = con.createStatement()) {
                try (ResultSet rs = order.executeQuery("SELECT * FROM user_data WHERE token='" + temptok +"'")) {
                    if (rs.next()) {
                        System.out.println("token:" + temptok + "is already in use, autoselectioned another ");
                    }else {
                        System.out.println(temptok);
                        return temptok;
                    }
                }
            }
        }
    }
    public static String createK() throws Exception{
        Random rdm = new Random();
        String chars = "qwertyuiopasdfghjklzxcvbnm";
        String charsA = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String tempkey = "srv-api-key:" + (chars.charAt(rdm.nextInt(chars.length()))) + rdm.nextInt(10000 ,99999) + rdm.nextInt(100 ,999) + chars.charAt(rdm.nextInt(chars.length())) +"";
    return tempkey;
    }
}
class user{
    int id;
    String user;
    String email;
    String token;
    String api;
    user(int id,String user, String email,String token, String api){
        this.id = id;
        this.user = user;
        this.email = email;
        this.token = token;
        this.api = api;
    }
}
