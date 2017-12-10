<%@ page import="view.IndexServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/sign.css">
</head>
<body>
<center>
    <div class="container">
        <h1 class="form-signin-heading">Work with database</h1>
        <form action="/index"  method="post" class="form-signin">
        <input type="text" size="10" name="nickname" value="Nickname" class="form-control" required><br>
        <input type="text" size="20" name="password" value="Password" class="form-control" required><br>
        <input type="text" size="20" name="firstName" value="Firstname" class="form-control" required><br>
        <input type="text" size="20" name="lastName" value="Lastname" class="form-control" required><br>
        <input type="text" size="20" name="birthday" value="20.10.1990" class="form-control" required><br>
        <br><button type="submit" name="add" class="btn btn-success">Add</button>
        <button type="submit" name="update" class="btn btn-warning">Update</button>
        <button type="submit" name="delete" class="btn btn-danger">Delete</button>
        <button type="submit" name="select" class="btn btn-success">Select</button>
    </form>
    <%if(request.getAttribute("completed") == null){%>
    <% }else{%>
        <h4>
    <%=request.getAttribute("completed")%>
        </h4>
    <%}%>
    </div>
</center>
</body>
</html>