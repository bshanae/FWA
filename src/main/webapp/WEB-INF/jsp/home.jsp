<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FWA</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}

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
            border: 3px solid #A7C7E7;
            width: 30%;
            border-radius: 2px;
            padding: 16px;
        }
    </style>
</head>
<body>
<h1>FWA</h1>
<div class="container">
    <form action="signIn" method="get">
        <div>
            <button type="submit">Sign in</button>
        </div>
    </form>
    <form action="signUp" method="get">
        <div>
            <button type="submit">Sign up</button>
        </div>
    </form>
</div>
</body>

</html>
