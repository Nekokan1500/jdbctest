package com.arthur.learn;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class ConnectionTest {

    @Test
    public void testConnection1() throws SQLException{

        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/jdbctest";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }
    
    @Test
    public void testConnection2() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
                                            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException 
    {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();
        String url = "jdbc:mysql://localhost:3306/jdbctest";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    @Test
    public void testConnection3() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, 
    IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();
        DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/jdbctest";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbctest";
        String user = "root";
        String password = "root";
        // Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection5() throws SQLException{
        
        try {
            InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
