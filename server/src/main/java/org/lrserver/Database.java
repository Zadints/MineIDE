package org.lrserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.stream.Stream;

public class Database {
    static public Connection connect(String db) throws Exception{
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "Lore3008@");
    }
}
