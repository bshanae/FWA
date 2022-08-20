<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <form action="sign-in" method="post">
        <div>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter email" required>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter password" required>
        </div>
        <div>
            <button type="submit">Sign in</button>
        </div>
    </form>
</body>
</html>
