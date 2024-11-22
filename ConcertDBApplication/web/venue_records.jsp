<%-- 
    Document   : venue_records
    Created on : Nov 15, 2024, 4:14:25 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Records</title>
    </head>
    <body>
        <jsp:useBean id = "U" class = "user_management.Users" scope = "session" />
        <% 
            HttpSession curr_session = request.getSession(false);
            Integer user_id = (Integer)curr_session.getAttribute("user_id");
            
            Users curr_user = U.get_user(user_id);
            String first_name = curr_user.get_first_name();
            String last_name = curr_user.get_last_name();
        
        %>
        <p> Welcome <%=first_name%> <%=last_name%></p>
        
        <a href = "concert_records.html"> Concerts </a><br>
        <a href = "venue_records.html"> Venues </a><br>
        <a href = "merchandise_records.html"> Merchandise </a><br>
    </body>
</html>
