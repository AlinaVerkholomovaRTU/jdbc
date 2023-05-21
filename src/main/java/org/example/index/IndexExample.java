package org.example.index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class IndexExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate";
        String user = "root";
        String pass = "password";

        try {
            System.out.println("Connecting to database" + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("create index idx_bank_account_number on bank_account(account_number)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
