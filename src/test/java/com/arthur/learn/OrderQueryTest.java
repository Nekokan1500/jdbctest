package com.arthur.learn;

import org.junit.Test;

import com.arthur.learn.jdbc.bean.Order;
import com.arthur.learn.jdbc.dao.OrderDao;

public class OrderQueryTest {
    @Test
    public void testCustomerQuery(){
        String sql = "select * from t_order where order_id = ?";
        Order customer = OrderDao.getOrder(sql, 3);
        System.out.println(customer);
    }
}
