<%@ page import="view.IndexServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action</title>
    <meta charset="utf-8">
</head>
<body>
<center>
    <form action="/index"  method="post">
        <h1>Work with database</h1>
        <br><h7>Nickname:</h7> <input type="text" size="20" name="nickname" value="Nickname"><br>
        <br><h7>Password:</h7> <input type="text" size="20" name="password" value="Password"><br>
        <br><h7>First name:</h7> <input type="text" size="20" name="firstName" value="Firstname"><br>
        <br><h7>Last name:</h7> <input type="text" size="20" name="lastName" value="Lastname"><br>
        <br><h7>Birthday:</h7> <input type="text" size="20" name="birthday" value="20.10.1990"><br>
        <br><button type="submit" name="add">add</button>
        <button type="submit" name="update">update</button>
        <button type="submit" name="delete">delete</button>
        <button type="submit" name="select">select</button>
    </form>
</center>
</body>
</html>