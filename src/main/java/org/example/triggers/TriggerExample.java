package org.example.triggers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TriggerExample {

    public static void main(String[] args) {


        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate";
        String user = "root";
        String pass = "password";


        try {
            System.out.println("Connecting to database" + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("create trigger sal_sum before insert on employee for each row set @sum = @sum + NEW.salary");

            stmt.executeUpdate("set @sum = 0");
            stmt.executeUpdate("insert into employee (name, salary) values ('Maria', 2000.00)");
            stmt.executeUpdate("insert into employee (name, salary) values ('Michael', 2000.00)");

            ResultSet rs = stmt.executeQuery("select @sum");
            if (rs.next()) {
                int salary = rs.getInt(1);
                System.out.println("Sum salary: " + salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
