package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicaDB
{
    Connection conn;

    public MusicaDB(String host, String databasename, String user, String password)
    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql:" + host + ":3306/musica" + user + password);
            Statement st = conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
