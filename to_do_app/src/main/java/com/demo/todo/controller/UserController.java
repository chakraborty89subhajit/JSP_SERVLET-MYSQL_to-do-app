package com.demo.todo.controller;


import com.demo.todo.dao.UserDAO;
import com.demo.todo.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserController extends HttpServlet {

private UserDAO userDAO;

public void init(){
    userDAO= new UserDAO();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
register(request,response);

}

protected void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
    response.sendRedirect("views/register.jsp");
}


private void register(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{

    String firstName=request.getParameter("firstName");
    String lastName=request.getParameter("lastName");
    String username=request.getParameter("username");
    String password=request.getParameter("password");

    User employee= new User();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setUserName(username);
    employee.setPassword(password);

try{
    int result= userDAO.registerEmployee(employee);
    if(result ==1){
        request.setAttribute("NOTIFICATION","user registered successfully");
       // response.sendRedirect("views/error.jsp");


    }else{
      //  response.sendRedirect("views/error.jsp");
        request.setAttribute("NOTIFICATION", "Registration failed. Please try again.");
    }
}catch(Exception e ){
    e.printStackTrace();
    request.setAttribute("NOTIFICATION", "An error occurred. Please try again.");
  //  response.sendRedirect("views/error.jsp");
}
    RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
    dispatcher.forward(request, response);

}

}
