package com.arthur.learn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
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
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testSelect(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select id, name, email, dob from customer where id = ?";
            BeanHandler<Customer> beanHandler = new BeanHandler(Customer.class);
            Customer customer = runner.query(connection, sql, beanHandler, 5);
            System.out.println(customer);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testSelectList(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select id, name, email, dob from customer where id < ?";
            BeanListHandler<Customer> beanHandler = new BeanListHandler(Customer.class);
            List<Customer> customers = runner.query(connection, sql, beanHandler, 5);
            customers.forEach(System.out::println);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testCount(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select count(*) from customer";
            ScalarHandler<Long> handler = new ScalarHandler<>();
            Long count = runner.query(connection, sql, handler);
            System.out.println(count);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetMap(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select id, name, email, dob from customer where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(connection, sql, handler, 5);
            System.out.println(map);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetMapList(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select id, name, email, dob from customer where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> map = runner.query(connection, sql, handler, 5);
            map.forEach(System.out::println);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetDate(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select max(dob) from customer";
            ScalarHandler<Date> handler = new ScalarHandler<>();
            Date maxDate = runner.query(connection, sql, handler);
            System.out.println(maxDate);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testCustomResultSetHandler(){
        Connection connection = null;
        try{
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select id, name, email, dob from customer where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<>(){
                @Override
                public Customer handle(ResultSet rs) throws SQLException{
                    return null;
                }
            };
            Customer customer = runner.query(connection, sql, handler, 5);
            System.out.println(customer);
        } catch(SQLException e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, null);
        }
    }
}
