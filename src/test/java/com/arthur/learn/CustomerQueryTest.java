package com.arthur.learn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.dao.CustomerDao;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class CustomerQueryTest {
    
    @Test
    public void testQuery1(){

        Connection connection = JDBCUtils.getConnection();
        String sql = "select id, name, email, dob from customer where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 2);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date dob = rs.getDate(4);
                //System.out.println("id = " + id + ", name = " + name + ", email = " + email + ", dob = " + dob);
                //Object[] data = new Object[]{id, name, email, dob};
                Customer customer = new Customer(id, name, email, dob);
                System.out.println(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.closeResource(connection, ps, rs);
        }
    }

    @Test
    public void testCustomerQuery(){
        String sql = "select id, name, email, dob from customer where id = ?";
        Customer customer = CustomerDao.getCustomer(sql, 3);
        System.out.println(customer);
    }
}
