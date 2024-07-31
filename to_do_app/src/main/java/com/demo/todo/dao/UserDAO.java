package com.demo.todo.dao;

import com.demo.todo.model.User;
import com.demo.todo.utility.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public int registerEmployee(User employee) throws ClassNotFoundException{
        String INSERT_USER_SQL="insert into userRegistarion "+
                                "(first_name,last_name,username,password) values "+
                                 "(?,?,?,?);";

        int result =0;
        try(Connection connection= JDBCUtil.getConnection();
            PreparedStatement pstmt= connection.prepareStatement(INSERT_USER_SQL)){
            pstmt.setString(1,employee.getFirstName());
            pstmt.setString(2,employee.getLastName());
            pstmt.setString(3,employee.getUserName());
            pstmt.setString(4,employee.getPassword());


            System.out.println(pstmt);
            result= pstmt.executeUpdate();

        }catch(SQLException e ){
          //  e.printStackTrace();
            JDBCUtil.printSQLException(e);
        }
        return result;
    }
}
