package com.arthur.learn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.dao.CustomerDaoDemo;
import com.arthur.learn.jdbc.dao.CustomerDaoImpl;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class CustomerQueryTest {
    
    private CustomerDaoImpl  customerDao = new CustomerDaoImpl();

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
        Customer customer = CustomerDaoDemo.getCustomer(sql, 3);
        System.out.println(customer);
    }

    @Test
    public void testInsert() throws ParseException{
        Connection connection = JDBCUtils.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dob = sdf.parse("1963-03-19");
        Customer customer = new Customer(null, "Fenghua Zhao", "zfh@gmail.com", new Date(dob.getTime()));
        customerDao.insert(connection, customer);
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testDeleteById(){
        Connection connection = JDBCUtils.getConnection();
        customerDao.deleteById(connection, 7);
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testUpdateCustomer() throws ParseException{
        Connection connection = JDBCUtils.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dob = sdf.parse("1993-03-19");
        Customer customer = new Customer(3, "Lin Qingqia", "lqx@126.com", new Date(dob.getTime()));
        customerDao.update(connection, customer);
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testGetCustomerById(){
        Connection connection = JDBCUtils.getConnection();
        Customer customer = customerDao.getCustomerbId(connection, 5);
        System.out.println(customer.toString());
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testGetAllCustomers(){
        Connection connection = JDBCUtils.getConnection();
        List<Customer> customers = customerDao.getAllCustomers(connection);
        customers.forEach(System.out::println);
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testGetCount(){
        Connection connection = JDBCUtils.getConnection();
        Long count = customerDao.getCount(connection);
        System.out.println(count);
        JDBCUtils.closeResource(connection, null);
    }

    @Test
    public void testGetMaxBirth(){
        Connection connection = JDBCUtils.getConnection();
        Date maxBirth = customerDao.getMaxBirth(connection);
        System.out.println(maxBirth);
        JDBCUtils.closeResource(connection, null);
    }
}
