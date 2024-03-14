package com.arthur.learn.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.arthur.learn.jdbc.bean.Customer;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customer where id = ?";
        executeUpdate(connection, sql, id);
    }

    @Override
    public List<Customer> getAllCustomers(Connection connection) {
        String sql = "SELECT name, email, dob FROM customer";
        return executeQueryForList(connection, sql, Customer.class);
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "SELECT COUNT(*) FROM customer";
        return getValue(connection, sql);
    }

    @Override
    public Customer getCustomerbId(Connection connection, int id) {
        String sql = "SELECT * FROM customer where id = ?";
        return executeQuery(connection, sql, Customer.class, id);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "SELECT max(dob) from customer";
        return getValue(connection, sql);
    }

    @Override
    public void insert(Connection connection, Customer cust) {
        String sql = "INSERT INTO customer (name, email, dob) VALUES (?, ?, ?)";
        executeUpdate(connection, sql, cust.getName(), cust.getEmail(), cust.getDob());
    }

    @Override
    public void update(Connection connection, Customer cust) {
        String sql = "UPDATE customer SET name = ?, email = ?, dob = ? where id = ?";
        executeUpdate(connection, sql, cust.getName(), cust.getEmail(), cust.getDob(), cust.getId());
    }
    
}
