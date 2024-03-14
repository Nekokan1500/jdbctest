package com.arthur.learn;

import java.sql.Connection;
import java.util.List;


import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.dao.CustomerDao;
import com.arthur.learn.jdbc.dao.GenericCustomerDaoImpl;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class GenericCustomerDaoTest {

    private CustomerDao  customerDao = new GenericCustomerDaoImpl();

    @Test
    public void testGetAllCustomers(){
        Connection connection = JDBCUtils.getC3p0Connection();
        List<Customer> customers = customerDao.getAllCustomers(connection);
        customers.forEach(System.out::println);
        JDBCUtils.closeResource(connection, null);
    }

}
