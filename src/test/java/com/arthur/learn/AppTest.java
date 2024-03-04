package com.arthur.learn;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.arthur.learn.jdbc.dao.BaseDao;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testExecuteQueryForInsert(){

        String sql = "INSERT INTO customer (name, email, dob) VALUES (?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date date = sdf.parse("1962-10-30");
            BaseDao.executeUpdate(sql, "Fenghua Zhao", "zfh@qq.com", new Date(date.getTime()));
        }
        catch(ParseException e){e.printStackTrace();}

    }

    @Test
    public void testExecuteQueryForUpdate(){

        String sql = "UPDATE customer SET email = ? WHERE name = ?";
        BaseDao.executeUpdate(sql, "zfh@gmail.com", "Fenghua Zhao");
    }

    @Test
    public void testExecuteQueryForDelete(){

        String sql = "DELETE FROM customer WHERE name = ?";
        BaseDao.executeUpdate(sql, "Wang Feng");
    }
}
