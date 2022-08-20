<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <h1>Sign up</h1>
    <form action="sign-up" method="post">
        <div>
            <label for="first-name">First name</label>
            <input id="first-name" name="first-name" required>
        </div>
        <div>
            <label for="last-name">Last name</label>
            <input id="last-name" name="last-name" required>
        </div>
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
