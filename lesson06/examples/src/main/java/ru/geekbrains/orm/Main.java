package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/architecture", "root", "root");

        UserRepository repository = new UserRepository(con);

        User user2 = repository.findById(7).orElse(null);

        User user = new User(11, "User17", "qwerty");

        repository.beginTransaction();
        repository.insert(user);
        repository.commitTransaction();

        System.out.println(user);
    }
}