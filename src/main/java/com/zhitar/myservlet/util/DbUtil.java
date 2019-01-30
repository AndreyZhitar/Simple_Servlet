package com.zhitar.myservlet.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream stream = DbUtil.class.getClassLoader().getResourceAsStream("db.properties")){
                Properties properties = new Properties();
                properties.load(stream);
                String driver = properties.getProperty("driver");
                String url = properties.getProperty("url");
                String userName = properties.getProperty("username");
                String password = properties.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, userName, password);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
