package com.arthur.learn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.arthur.learn.jdbc.utils.JDBCUtils;

public class BatchInsertTest {

    @Test
    public void testBatchInsert1(){

        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO products (name) VALUES (?)";

        try{
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                ps.setString(1, "product_"+i);
                ps.execute();
            }
            System.out.println("Time spent: " + (System.currentTimeMillis() - start));
        }
        catch(SQLException e) {e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testBatchInsert2(){

        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO products (name) VALUES (?)";

        try{
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setString(1, "product_"+i);
                ps.addBatch();
                if(i % 500 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            System.out.println("Time spent: " + (System.currentTimeMillis() - start));
        }
        catch(SQLException e) {e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, ps);
        }
    }

    @Test
    public void testBatchInsert3(){

        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO products (name) VALUES (?)";

        try{
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setString(1, "product_"+i);
                ps.addBatch();
                if(i % 500 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            connection.commit();
            System.out.println("Time spent: " + (System.currentTimeMillis() - start));
        }
        catch(SQLException e) {e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, ps);
        }
    }
}
