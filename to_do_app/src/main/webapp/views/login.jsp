<html>
<head>login page</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">
<table border="1">
<tr>
<td>username:</td>
<td><input type="text" name="username"/></td>
</tr>
<tr>
<td>password:</td>
<td><input type="password" name="password"/></td>
</tr>
<tr><td><input type="submit" value="login"></td></tr>
</table>
</form>
</body>
</html>