<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/sign.css">
</head>
<body>
<center>
    <div class="container">
    <h1 class="form-signin-heading">Users list</h1>
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Nickname</th>
            <th>Password</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Birthday</th>
        </tr>
        <jsp:include page="/select"/>
    </table>
    </div>
</center>
</body>
</html>
