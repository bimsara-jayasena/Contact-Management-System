package org.example.Util;
import com.mysql.jdbc.Driver;

import  java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseUtil {
    private static final String URL="jdbc:mysql://localhost:3306/contacts";
    private static final String USER="root";
    private static final String PASSWORD="1234";
    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Successfully Connected!");

        }catch (ClassNotFoundException | SQLException exception){
            System.out.println("Connection Failed!");
            exception.printStackTrace();
        }
        return connection;
    }
}
