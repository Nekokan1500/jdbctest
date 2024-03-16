package com.arthur.learn;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.arthur.learn.jdbc.utils.JDBCUtils;

public class QueryRunnerTest {

    @Test
    public void testInsert(){
        QueryRunner runner = new QueryRunner();
        Connection connection = JDBCUtils.getDruidConnection();
        String sql = "INSERT INTO customer (name, email, dob) VALUES (? ,? , ?)";
        try {
            runner.update(connection, sql, "Marcia", "marcia@gmail.com", "1988-09-02");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
