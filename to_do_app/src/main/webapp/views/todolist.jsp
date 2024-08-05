<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.todo.model.ToDo" %>
<%
    List<ToDo> listToDo = (List<ToDo>) request.getAttribute("listToDo");
%>
<!DOCTYPE html>
<html>
<head>
    <title>ToDo List</title>
</head>
<body>
    <h2>ToDo List</h2>
    <a href="new">Create New ToDo</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Username</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (listToDo != null) {
                    for (ToDo todo : listToDo) {
            %>
            <tr>
                <td><%= todo.getId() %></td>
                <td><%= todo.getTitle() %></td>
                <td><%= todo.getUsername() %></td>
                <td><%= todo.getDescription() %></td>
                <td><%= todo.getTargetDate() %></td>
                <td><%= todo.isStatus() ? "Yes" : "No" %></td>
                <td>
                    <a href="edit?id=<%= todo.getId() %>">Edit</a>
                    <a href="delete?id=<%= todo.getId() %>">Delete</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
