package org.example.constraints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConstraintExample {
    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/JDBC";
//        String user = "alinaverkholomova";
//        String pass = "1Revan@loves8996";

        String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate";
        String user = "root";
        String pass = "kakosik1";

        try {
            System.out.println("Connecting to database" + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            Statement statement = connection.createStatement();
            statement.executeUpdate("alter table teacher add constraint uk_teacher_school_id UNIQUE(school_id)");

            statement.executeUpdate("insert into school(name, city) values ('school1', 'Riga')");

            statement.executeUpdate("insert into teacher(first_name, last_name, school_id) values ('name1', 'surname1', 1)");

            statement.executeUpdate("insert into teacher(first_name, last_name, school_id) values ('name2', 'surname2', 1)");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
