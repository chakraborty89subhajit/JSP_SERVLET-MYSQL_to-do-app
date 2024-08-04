package com.demo.todo.utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtil {

    private static String jdbcURL="jdbc:mysql://localhost:3306/mydb";
    private static String jdbcUserName="root";
    private static String jdbcPassword="project";

    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
        }catch(Exception e){
            e.printStackTrace();
        }
    return connection;
    }

    public static void printSQLException(SQLException ex){
        for(Throwable e:ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+((SQLException) e).getSQLState());
                System.err.println("Error code : "+((SQLException) e).getErrorCode());
                System.err.println("Message: "+ e.getMessage());

                Throwable t=ex.getCause();
                while(t!=null){
                   System.out.println("cause" +t);
                   t=t.getCause();
                }
            }
        }
    }

    public static LocalDate getUtilDate(Date sqlDate){
        return sqlDate.toLocalDate();
    }
    public static Date getSQLDate(LocalDate localDate){
        return Date.valueOf(localDate);
    }

}
