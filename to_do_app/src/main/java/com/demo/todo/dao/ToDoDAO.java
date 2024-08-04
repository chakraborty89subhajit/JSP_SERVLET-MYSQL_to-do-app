package com.demo.todo.dao;

import com.demo.todo.model.ToDo;

import java.sql.SQLException;
import java.util.List;

public interface ToDoDAO {

    void insertTodo( ToDo todo) throws SQLException;

    ToDo selectTodo(long todoId);
    List<ToDo> selectAllTodos();
    boolean deleteTodo(int id) throws SQLException;

    boolean updateTodo(ToDo todo) throws SQLException;


}
