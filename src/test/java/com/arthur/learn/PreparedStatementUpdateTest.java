package com.arthur.learn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;

import org.junit.Test;

import com.arthur.learn.jdbc.utils.JDBCUtils;

public class PreparedStatementUpdateTest {

    @Test
    public void testInsert(){

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            InputStream resourceAsStream = PreparedStatementUpdateTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO customer (name, email, dob) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "Arthur Cheng");
            ps.setString(2, "czy@hotmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dob = sdf.parse("1992-10-01");
            ps.setDate(3, new Date(dob.getTime()));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
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
    }

    @Test
    public void testUpdate(){
        Connection connection = JDBCUtils.getConnection();
        String sql = "UPDATE customer SET name = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "Marcia Dong");
            ps.setInt(2, 2);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps);
        }
    }
}

    

