package com.arthur.learn;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.bean.Order;
import com.arthur.learn.jdbc.bean.Student;
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

    @Test
    public void testGenericQuery(){
        String sql1 = "SELECT id, name, email FROM customer WHERE id = ?";
        Customer customer = BaseDao.executeQuery(sql1, Customer.class, 2);
        System.out.println("Customer: " + customer);
        String sql2 = "SELECT * from t_order WHERE order_id = ?";
        Order order = BaseDao.executeQuery(sql2, Order.class, 1);
        System.out.println("Order: " + order);
    }

    @Test
    public void testGenericQueryForList(){
        String sql = "SELECT id, name, email FROM customer";
        ArrayList<Customer> customers = (ArrayList<Customer>)BaseDao.executeQueryForList(sql, Customer.class);
        /* 
        for (Customer cust: customers){
            System.out.println("Customer: " + cust);
        }
        */
        customers.forEach(System.out::println);
    }

    // Exercise 1
    @Test
    public void testInsertCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter date of birth: ");
        String dob = scanner.next();

        String sql = "INSERT INTO customer (name, email, dob) VALUES (?, ?, ?)";
        int ret = BaseDao.executeUpdate(sql, name, email, dob);
        if (ret > 0){
            System.out.println("Insert succeeded.");
        }
        else
            System.out.println("Insert failed");
        scanner.close();
    }

    // Exercise 2-1
    @Test
    public void testInsertStudent(){
        String sql = "INSERT INTO student (Type, IDCard, ExamCard, Name, Location, Grade) VALUES (?, ?, ?, ?, ?, ?)";
        int ret = BaseDao.executeUpdate(sql, 6, "41010719685429615X", "200523164754006", "Ma Lin", "Luoyang", 87);
        System.err.println(ret);
    }

    // Exercise 2-2
    @Test
    public void testGetStudent(){
        String sql1 = "SELECT FlowID flowId, Type type, IDCard idCard, ExamCard examCard, Name name, Location location, Grade grade " +
        "FROM student WHERE IDCard = ?";
        Student student = BaseDao.executeQuery(sql1, Student.class, "454524195263214584");
        System.out.println("Studnet by ID Card #: " + student);
        String sql2 = "SELECT FlowID flowId, Type type, IDCard idCard, ExamCard examCard, Name name, Location location, Grade grade " +
        "FROM student WHERE ExamCard = ?";
        Student student2 = BaseDao.executeQuery(sql2, Student.class, "200523164754005");
        System.out.println("Studnet by Exam Card #: " + student2);
    }

    // Exercise 2-3
    @Test
    public void testDeleteStudent(){
        String sql = "DELETE FROM student WHERE ExamCard = ?";
        int ret = BaseDao.executeUpdate(sql, "200523164754003");
        System.out.println(ret);
    }
}
