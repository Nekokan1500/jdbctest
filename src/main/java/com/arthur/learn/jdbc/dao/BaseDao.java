package com.arthur.learn.jdbc.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.arthur.learn.jdbc.utils.JDBCUtils;

public class BaseDao {

    public static void executeUpdate(String sql, Object ... parameters){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i+1, parameters[i]);
            }
            ps.execute();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps);
        }
    }
    
}
