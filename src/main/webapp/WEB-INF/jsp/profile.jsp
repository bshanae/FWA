<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>Profile</h1>
    <h3>First name: ${firstName}</h3>
    <h3>Last name: ${lastName}</h3>
    <h3>Phone number: ${phone}</h3>
    <br>
    <h3>File Upload:</h3>
    Select a file to upload: <br />
    <form method="post" action="fileuploadservlet" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="Upload" />
    </form>
</body>
</html>
