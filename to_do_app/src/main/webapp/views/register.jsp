<html>
<head>employee registration</head>
<body>
<form  method="post" action="<%= request.getContextPath() %>/register">
<table border="1">
first name:
<input type="text" name="firstName"/>
last name:
<input type="text" name="lastName"/>
username:
<input type="text" name="username"/>
password:
<input type="password" name="password"/>
<input type="submit" value="register">
</table>
</form>

    <%
        String notification = (String) request.getAttribute("NOTIFICATION");
        if (notification != null) {
            out.println("<p>" + notification + "</p>");
        }
    %>


</body>
</html>