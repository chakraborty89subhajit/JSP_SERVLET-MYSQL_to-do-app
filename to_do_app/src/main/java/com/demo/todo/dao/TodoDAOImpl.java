package com.demo.todo.dao;

import com.demo.todo.model.ToDo;
import com.demo.todo.utility.JDBCUtil;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOImpl  implements ToDoDAO{
    public static final String INSERT_TODOS_SQL="insert into todo"+
                                                 "(title,username,description,target_date,is_done) values"+
                                                  "(?,?,?,?,?);";

    public static final String SELECT_TODO_BY_ID="select id,title,username,description,target_date,is_done from todo where id=?";

    public static final String SELECT_ALL_TODOS="select * from todo";

    public static final String DELETE_TODO_BY_ID="delete from todo where id=?";

    public static final String UPDATE_TODO="update todo set title=?,username=?,description=?,target_date=?,is_done=?  where id= ?";


    public TodoDAOImpl(){

    }

    public void insertTodo(ToDo todo) throws SQLException{
        System.out.println(INSERT_TODOS_SQL);


        try(Connection connection= JDBCUtil.getConnection();
             PreparedStatement pstmt= connection.prepareStatement(INSERT_TODOS_SQL)){
            pstmt.setString(1,todo.getTitle());
            pstmt.setString(2,todo.getUsername());
            pstmt.setString(3,todo.getDescription());
            pstmt.setString(4, String.valueOf(JDBCUtil.getSQLDate(todo.getTargetDate())));
            pstmt.setBoolean(5,todo.isStatus());

            System.out.println(pstmt);

            pstmt.executeUpdate();

        }catch(SQLException e){
          JDBCUtil.printSQLException(e);
        }
    }

    public ToDo selectTodo(long todoId)
    {
        ToDo todo= null;
        try(Connection connection= JDBCUtil.getConnection();
             PreparedStatement pstmt=connection.prepareStatement(SELECT_TODO_BY_ID);){
            pstmt.setLong(1,todoId);
            System.out.println(pstmt);
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                long id= rs.getLong("id");
                String  title= rs.getString("title");
                String  username= rs.getString("username");
                String  description= rs.getString("description");
                LocalDate targetDate=rs.getDate("target_date").toLocalDate();
                boolean isDone=rs.getBoolean("is_done");

                todo= new ToDo(id,title,username,description,targetDate,isDone);
            }

        }catch(SQLException e){
            JDBCUtil.printSQLException(e);
        }
       return todo;
    }

    public List<ToDo> selectAllTodos(){
        List<ToDo> todos = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_TODOS);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new ToDo(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return todos;
    }

    public boolean deleteTodo(int id) throws SQLException{
        boolean rowDeleted= false;
        try(Connection connection= JDBCUtil.getConnection();
             PreparedStatement pstmt=connection.prepareStatement(DELETE_TODO_BY_ID);){
            pstmt.setInt(1,id);
            rowDeleted=pstmt.executeUpdate()>0;

        }catch(Exception e){
e.printStackTrace();
        }
return rowDeleted;
    }

public boolean updateTodo(ToDo todo) throws SQLException{

        boolean rowUpdated= false;
        try(Connection connection=JDBCUtil.getConnection();
             PreparedStatement pstmt= connection.prepareStatement(UPDATE_TODO);){
              pstmt.setString(1,todo.getTitle());
              pstmt.setString(2,todo.getUsername());
              pstmt.setString(3,todo.getDescription());
              pstmt.setDate(4,JDBCUtil.getSQLDate(todo.getTargetDate()));
              pstmt.setBoolean(5,todo.isStatus());
              pstmt.setLong(6,todo.getId());

              rowUpdated=pstmt.executeUpdate()>0;

        }catch(Exception e ){
            e.printStackTrace();
        }
        return rowUpdated;
}

}
