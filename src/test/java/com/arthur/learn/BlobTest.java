package com.arthur.learn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.arthur.learn.jdbc.bean.Customer;
import com.arthur.learn.jdbc.dao.CustomerDaoDemo;
import com.arthur.learn.jdbc.utils.JDBCUtils;

public class BlobTest {

    @Test
    public void testInsertBlob(){
        CustomerDaoDemo.insertCustomerWithPicture();
    }

    @Test
    public void testReadBlob(){
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "SELECT id, name, email, dob, photo FROM customer WHERE id = ?";
        InputStream is = null;
        FileOutputStream fos = null;
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 4);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                Customer customer = new Customer(id, name, email, dob);
                System.out.println("Customer is " + customer);
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                byte[] buffer = new byte[1024];
                int length;
                fos = new FileOutputStream("London.jpg");
                while((length = is.read(buffer)) != -1){
                    fos.write(buffer, 0, length);
                }
            }
        }
        catch(Exception e){e.printStackTrace();}
        finally{
            JDBCUtils.closeResource(connection, ps);
            try{
                if (is != null){
                    is.close();
                }
            }
            catch(IOException e) {e.printStackTrace();}
            try{
                if (fos != null){
                    fos.close();
                }
            }
            catch(IOException e){e.printStackTrace();}
        }
    }
}
