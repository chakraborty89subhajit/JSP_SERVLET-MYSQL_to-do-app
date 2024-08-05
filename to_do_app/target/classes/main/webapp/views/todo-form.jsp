<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.demo.todo.model.ToDo" %>
<%
    ToDo todo = (ToDo) request.getAttribute("todo");
    String action = (todo == null) ? "insert" : "update?id=" + todo.getId();
%>
<!DOCTYPE html>
<html>
<head>
    <title>ToDo Form</title>
</head>
<body>
    <h2><%= (todo == null) ? "Create New ToDo" : "Edit ToDo" %></h2>
    <form action="<%= action %>" method="post">
        <label for="title">Title:</label>
        <input type="text" name="title" id="title" value="<%= (todo == null) ? "" : todo.getTitle() %>" required><br>
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" value="<%= (todo == null) ? "" : todo.getUsername() %>" required><br>
        <label for="description">Description:</label>
        <textarea name="description" id="description" required><%= (todo == null) ? "" : todo.getDescription() %></textarea><br>
        <label for="targetDate">Target Date:</label>
        <input type="date" name="targetDate" id="targetDate" value="<%= (todo == null) ? "" : todo.getTargetDate().toString() %>" required><br>
        <label for="status">Status:</label>
        <input type="checkbox" name="status" id="status" <%= (todo != null && todo.isStatus()) ? "checked" : "" %>><br>
        <input type="submit" value="Save">
    </form>
    <a href="list">Back to ToDo List</a>
</body>
</html>
