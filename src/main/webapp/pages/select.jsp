<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select</title>
</head>
<body>
<center>
    <h1>Users list</h1>
    <table border="1" width="50%" cellpadding="5">
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
    <br>
</center>
</body>
</html>
