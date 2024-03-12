package com.arthur.learn;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.arthur.learn.jdbc.dao.BaseDao;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class TransactionTest {

    @Test
    public void testUpdate(){
        String sql1 = "UPDATE t_user SET balance = balance - 100 WHERE id = ?";
        String sql2 = "UPDATE t_user SET balance = balance + 100 WHERE id = ?";
        BaseDao.executeUpdate(sql1, 1);
        BaseDao.executeUpdate(sql2, 2);
    }

    @Test
    public void testUpdateWithTransaction(){
        Connection connection = null;
        String sql1 = "UPDATE t_user SET balance = balance - 100 WHERE id = ?";
        String sql2 = "UPDATE t_user SET balance = balance + 100 WHERE id = ?";
        try{
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            BaseDao.executeUpdate(connection, sql1, 1);
            //System.out.println(10/0);
            BaseDao.executeUpdate(connection, sql2, 2);
            connection.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

}
