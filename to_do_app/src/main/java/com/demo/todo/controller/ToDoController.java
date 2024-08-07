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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/new", "/insert", "/delete", "/edit", "/update", "/list"})
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
        }catch(SQLException e){
            throw new ServletException(e);
        }
}

private void listTodo(HttpServletRequest request,HttpServletResponse response)
throws SQLException,IOException,ServletException{
        List<ToDo> listTodo = todoDAO.selectAllTodos();
        request.setAttribute("listTodo",listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/todolist.jsp");
        dispatcher.forward(request,response);

}

private void showNewForm(HttpServletRequest request, HttpServletResponse response)
throws ServletException,IOException{

        RequestDispatcher dispatcher=request.getRequestDispatcher("views/todo-form.jsp");
        dispatcher.forward(request,response);

}



private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException
{
int id= Integer.parseInt(request.getParameter("id"));
ToDo existingToDo= todoDAO.selectTodo(id);
RequestDispatcher dispatcher=request.getRequestDispatcher("views/todo-form.jsp");
request.setAttribute("todo",existingToDo);
dispatcher.forward(request,response);
}

private void insertToDO(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        String title=request.getParameter("title");
        String username=request.getParameter("username");
        String description= request.getParameter("description");

        boolean isDone= Boolean.valueOf(request.getParameter("isDone"));
        ToDo newToDo=new ToDo(title,username,description, LocalDate.now(),isDone);
        todoDAO.insertTodo(newToDo);
        response.sendRedirect("list");

}

private void updateTodo(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        int id= Integer.parseInt(request.getParameter("id"));
String title=request.getParameter("title");
String username= request.getParameter("username");
String description= request.getParameter("description");
LocalDate targetDate=LocalDate.parse(request.getParameter("targetDate"));
boolean isDone= request.getParameter("status")!=null;

ToDo updateToDo = new ToDo(id,title,username,description,targetDate,isDone);

todoDAO.updateTodo(updateToDo);
response.sendRedirect("list");

}

private void deleteToDo(HttpServletRequest  request, HttpServletResponse response)
        throws SQLException,IOException{
int id= Integer.parseInt(request.getParameter("id"));
todoDAO.deleteTodo(id);
response.sendRedirect("list");
}

}
