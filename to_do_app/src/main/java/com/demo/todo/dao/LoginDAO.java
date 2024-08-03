package com.demo.todo.dao;

import com.demo.todo.model.LoginBean;
import com.demo.todo.utility.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException{
        boolean status= false;

        Class.forName("com.mysql.jdbc.Driver");

        try(Connection connection= JDBCUtil.getConnection();
            PreparedStatement pstmt= connection.prepareStatement("select * from userRegistarion where username= ? and password= ?")){
            pstmt.setString(1,loginBean.getUsername());
            pstmt.setString(2,loginBean.getPassword());

            System.out.println(pstmt);

            ResultSet rs= pstmt.executeQuery();
             status=rs.next();

        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

}
