package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config cfg = new Config("app.properties");
        cfg.load();
        String driverClass = cfg.value("hibernate.connection.driver_class");
        Class.forName(driverClass);
        String url = cfg.value("hibernate.connection.url");
        String login = cfg.value("hibernate.connection.username");
        String password = cfg.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
