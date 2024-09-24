package week03.classicmodels.persistence;

import week03.classicmodels.business.Employee;

import java.sql.*;
import java.time.LocalDateTime;

public class MySQLDao {
    private String databaseName = "classicmodels";

    public MySQLDao(){
    }

    public MySQLDao(String databaseName){
        this.databaseName = databaseName;
    }

    public Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;
        String username = "root";
        String password = "";
        try{
            Class.forName(driver);

            try{
                Connection conn = DriverManager.getConnection(url, username, password);
                return conn;
            }catch(SQLException e){
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while trying to connect to the " + url +
                        "database.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e){
            System.out.println(LocalDateTime.now() + ": A ClassNotFoundException occurred while trying to load the MySQL driver.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
