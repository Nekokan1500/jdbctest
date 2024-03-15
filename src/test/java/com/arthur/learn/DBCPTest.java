package com.arthur.learn;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPTest {

    @Test
    public void testGetConnection(){
        BasicDataSource dpcpSource = new BasicDataSource();
        Connection connection = null;
        try {
            dpcpSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dpcpSource.setUrl("jdbc:mysql://localhost:3306/jdbctest?rewriteBatchedStatements=true");
            dpcpSource.setUsername("root");
            dpcpSource.setPassword("root");
            dpcpSource.setInitialSize(10);
            //dpcpSource.setMaxActive(20);
            connection = dpcpSource.getConnection();
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

    @Test
    public void testGetConnection1(){
        BasicDataSource dpcpSource = null;
        Connection connection = null;
        try {
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(is);
            dpcpSource = BasicDataSourceFactory.createDataSource(properties);
            connection = dpcpSource.getConnection();
            System.out.println(connection);
            is.close();
        } catch (Exception e) {
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
