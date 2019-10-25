<%--
  Created by IntelliJ IDEA.
  User: jgrenierb
  Date: 2019-10-25
  Time: 10:07 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JaveEE Test</title>
</head>
<body>
<p>Wonderful!</p>
<form action="backend" method="get">
    <input type="hidden" name="action" value="add"/>

    <label>
        Email:
        <input type="email" name="email" required/>
    </label>
    <br/>

    <label>
        First name:
        <input type="text" name="email" required/>
    </label>
    <br/>

    <label>
        Last name:
        <input type="text" name="email"/>
    </label>
    <br/>

    <label>
        <input type="submit" id="submit" value="Send"/>
    </label>
</form>
</body>
</html>
