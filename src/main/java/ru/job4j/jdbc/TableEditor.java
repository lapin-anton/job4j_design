package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("test");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.addColumn("test", "id", "serial primary key");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.addColumn("test", "name", "varchar(255)");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.addColumn("test", "age", "int");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.renameColumn("test", "name", "surname");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.dropColumn("test", "age");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
            tableEditor.dropTable("test");
        }
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.connection.driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("jdbc.connection.url"),
                properties.getProperty("jdbc.connection.username"),
                properties.getProperty("jdbc.connection.password"));
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("create table if not exists %s();", tableName);
        executeQuery(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table %s;", tableName);
        executeQuery(sql);
        System.out.printf("Table '%s' was dropped%n", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        executeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        executeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        executeQuery(sql);
    }

    private void executeQuery(String query) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(query);
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
