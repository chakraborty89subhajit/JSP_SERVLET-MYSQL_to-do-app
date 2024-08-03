package com.demo.todo.controller;

import com.demo.todo.dao.LoginDAO;
import com.demo.todo.model.LoginBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private LoginDAO loginDAO;

    public void init(){
        loginDAO= new LoginDAO();
    }


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("views/login.jsp");
}

protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        authenticate(request,response);
}

private void authenticate(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
String username=request.getParameter("username");
String password=request.getParameter("password");

LoginBean loginBean = new LoginBean();

loginBean.setUsername(username);
loginBean.setPassword(password);
try{

    if(loginDAO.validate(loginBean)){
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/todolist.jsp");
        dispatcher.forward(request,response);
    }else{
        HttpSession session = request.getSession();

    }

}catch(Exception e){
    e.printStackTrace();
}

}
}
