package com.arthur.learn;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPTest {

    @Test
    public void testGetConnection(){
        BasicDataSource dpcpSource = new BasicDataSource();
        Connection connection = null;
        try {
            dpcpSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dpcpSource.setUrl("jdbc:mysql://localhost:3306/jdbctest?rewriteBatchedStatements=true");
            dpcpSource.setUsername("arthur");
            dpcpSource.setPassword("abc123");
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

}
