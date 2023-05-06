package main.java.org.example.performance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static java.lang.System.currentTimeMillis;

public class ReadRecords {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "alinaverkholomova";
        String pass = "password";


        try {
            System.out.println("Connecting to database" + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            Statement stmt = connection.createStatement();
            String query = "select * from passport";

            long startTime = currentTimeMillis();

            int i = 1;
            while (i < 1001) {
                stmt.executeQuery(query);
                i++;
            }

            long stopTime = currentTimeMillis();
            long time = stopTime - startTime;

            System.out.println("Required time for reading 1000 rows is: " + time);
            System.out.println("Connection successful!");

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
