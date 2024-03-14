package com.arthur.learn.jdbc.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.File;
import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class CustomerDaoDemo {

    public static Customer getCustomer(String sql, Object ... parameters)  {

        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Customer customer = null;

        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()){
                customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(customer, value);
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps, rs);
        }

        return customer;
    }

    public static void insertCustomerWithPicture(){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO customer (name, email, dob, photo) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, "Arthur");
            ps.setObject(2, "arthur@gmail.com");
            ps.setObject(3, "1985-09-06");
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ArthurCheng\\Documents\\IMG_0432.jpg"));
            ps.setBlob(4, inputStream);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps);
        }

    }
    
}
