<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<form method="post" action="UsersServlet">
    <table>
        <tr>
            <th>用户名： </th>
            <td><input type="text" name="user" /></td>
        </tr><tr>
            <th>密码：</th>
            <td>
                <label>
                <input type="text" name="password" />
                </label>
            </td>
        </tr>
        <tr>
            <td><input type="reset" value="reset" /></td>
            <td><input type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
</body>
</html>
