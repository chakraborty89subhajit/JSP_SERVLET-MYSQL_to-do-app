package com.demo.todo.controller;
import com.demo.todo.dao.ToDoDAO;
import com.demo.todo.dao.TodoDAOImpl;
import com.demo.todo.model.ToDo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list")
public class ToDoController extends HttpServlet {

    private ToDoDAO todoDAO;

    public void init(){
        todoDAO= new TodoDAOImpl();
    }

protected void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

}
protected void  doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String action= request.getServletPath();

        try{
            switch(action){
                case "/new":
                    showNewForm(request,response);
                    break;

                case "/insert":
                    insertToDO(request,response);
                    break;

                case "/delete":
                    deleteToDo(request,response);
                    break;

                case "/edit":
                    showEditForm(request,response);
                    break;

                case "/update":
                    updateTodo(request,response);
                    break;

                case "/list":
                    listTodo(request,response);
                    break;

                default:
                    RequestDispatcher dispatcher =request.getRequestDispatcher("views/login.jsp");
                     dispatcher.forward(request,response);
                      break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
}

}
