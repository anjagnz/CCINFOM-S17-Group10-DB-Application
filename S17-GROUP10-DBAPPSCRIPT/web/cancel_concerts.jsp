<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Concerts</title>
    </head>
    <body>
        <jsp:useBean id = "C" class = "concert_management.Concerts" scope = "session" />
        
        <%
            int concert_ID = Integer.parseInt(request.getParameter("concerts"));
            C.cancel_concert(concert_ID);
            response.sendRedirect("view_concerts.jsp?concerts=" + concert_ID);
        %>
    </body>
</html>
