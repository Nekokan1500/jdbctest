package com.arthur.learn;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Test {

    @Test
    public void testGetConnection(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        Connection connection = null;
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbctest?rewriteBatchedStatements=true");
            cpds.setUser("arthur");
            cpds.setPassword("abc123");
            cpds.setInitialPoolSize(10);
            connection = cpds.getConnection();
            System.out.println(connection);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testGetConnectionWithConfig(){
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection connection = null;
        try {
            connection = cpds.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
