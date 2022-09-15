<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {
            border: 3px solid #A7C7E7;
            width: 50%;
            border-radius: 2px;

        }

        input {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        h3 {
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
            padding-left: 25%;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
    <h1>Profile</h1>
    <div class="container">
        <form method="post" action="upload-file" enctype="multipart/form-data">
            <img width="200px" height="200px" src="${profileImage}" alt="profile image">
            <h3>First name: ${firstName}</h3>
            <h3>Last name: ${lastName}</h3>
            <h3>Phone number: </h3><input type="text" name="phone" value="${phone}"/>
            <h3>email: ${email}</h3>
            <h3>Session Info: </h3>
            <table>
                <tr>
                    <th>DATE</th>
                    <th>TIME</th>
                    <th>IP</th></tr>

            <c:forEach items="${sessionInfo}" var="sessionInfo" varStatus="status">
                <tr>
                    <td>${sessionInfo.date}</td>
                    <td>${sessionInfo.time}</td>
                    <td>${sessionInfo.ip}</td>
                </tr>
            </c:forEach>
            </table>
        <br>
        <h3>File Upload:</h3>
        Select a file to upload: <br />
            <input type="file" name="file" />
            <input type="submit" value="Upload" />
        </form>

        <table>
            <tr>
                <th>File Name</th>
                <th>Size</th>
                <th>MIME</th></tr>

            <c:forEach items="${images}" var="images" varStatus="status">
                <tr>
                    <td><a href="images/${images.originalName}" target="_blank">${images.originalName}</a></td>
                    <td>${images.size}bytes</td>
                    <td>${images.mime}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
