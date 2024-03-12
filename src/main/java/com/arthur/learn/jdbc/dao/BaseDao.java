package com.arthur.learn.jdbc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arthur.learn.jdbc.utils.JDBCUtils;

public class BaseDao {

    // No transaction support
    public static int executeUpdate(String sql, Object ... parameters){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
            //ps.execute();
            return ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps);
        }
        return 0;
    }

    public static int executeUpdate(Connection connection, String sql, Object ... parameters){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
            //ps.execute();
            return ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    public static <T> T executeQuery(String sql, Class<T> clazz, Object ... parameters){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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
                T t = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                return t;
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps, rs);
        }

        return null;
    }

    public static <T> List<T> executeQueryForList(String sql, Class<T> clazz, Object ... parameters){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<T> beans = new ArrayList<T>();
            while (rs.next()){
                T t = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i+1);
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                beans.add(t);
            }
            return beans;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps, rs);
        }

        return null;
    }

}
