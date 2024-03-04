package com.arthur.learn.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;


public class JDBCUtils {

    public static Connection getConnection(){
        Connection connection = null;

        try{
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(IOException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeResource(Connection connection, PreparedStatement ps){
        try{
            if (ps != null)
                ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if (connection != null){
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, PreparedStatement ps, ResultSet rs){
        try{
            if (ps != null)
                ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if (rs != null)
                rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if (connection != null){
                connection.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
