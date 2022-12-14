<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Sign in</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {
            border: 3px solid #A7C7E7;
            width: 30%;
            border-radius: 2px;
            text-align: center;
        }

        input {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #0096FF;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .container {
            padding: 16px;
        }
    </style>
</head>
<body>
    <h1>Sign in</h1>
    <div class="container">
    <form action="signIn" method="post">
        <div >
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
    </div>
</body>
</html>

