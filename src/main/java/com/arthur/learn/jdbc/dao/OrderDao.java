package com.arthur.learn.jdbc.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.arthur.learn.jdbc.bean.Order;
import com.arthur.learn.jdbc.utils.JDBCUtils;


public class OrderDao {
    
    public static Order getOrder(String sql, Object ... parameters)  {

        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Order order = null;

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
                order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, value);
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps, rs);
        }

        return order;
    }
}
