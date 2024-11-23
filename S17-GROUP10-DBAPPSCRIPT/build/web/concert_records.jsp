<%-- 
    Document   : concert_records
    Created on : Nov 15, 2024, 4:14:07 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Concert Records</title>
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
        
        <a href = "add_concerts.jsp"> Add Concerts </a><br>
        <a href = "view_concerts.jsp"> View Concerts </a><br>
        <a href = "modify_concerts.jsp"> Modify Concerts </a><br>
    </body>
</html>
