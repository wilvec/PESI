<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR EN LA APLICACION</title>
    </head>
    <body>
        <%
            out.print(exception.getMessage());
            %>
    </body>
</html>
