package com.arthur.learn.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.arthur.learn.jdbc.bean.Customer;

public interface CustomerDao {

    void insert(Connection connection, Customer cust);

    void deleteById(Connection connection, int id);

    void update(Connection connection, Customer customer);

    Customer getCustomerbId(Connection connection, int id);

    List<Customer> getAllCustomers(Connection connection);

    Long getCount(Connection connection);

    Date getMaxBirth(Connection connection);

}
