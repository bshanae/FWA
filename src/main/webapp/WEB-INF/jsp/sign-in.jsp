<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <h1>Sign in</h1>
    <form action="sign-in" method="post">
        <div>
            <label for="phone">Phone number</label>
            <input id="phone" name="phone" required>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Sign in</button>
        </div>
    </form>
</body>
</html>
