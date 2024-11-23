<%-- 
    Document   : log_in_processing
    Created on : Nov 14, 2024, 11:56:53 AM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In Processing</title>
    </head>
    <body>
        <jsp:useBean id = "U" class = "user_management.Users" scope = "session" />
        
        <%
            String user_email = request.getParameter("user_email");
            String user_password = request.getParameter("user_password");

            int status = U.log_in(user_email, user_password);
            
            if(status == 1)
            {
                HttpSession curr_session = request.getSession();
                curr_session.setAttribute("user_id", U.get_user_id());
                System.out.println(U.get_user_id());
                
                if(U.is_employee(U.get_user_id()))
                {
                    response.sendRedirect("employee_dashboard.jsp");
                }
                else
                {
                    response.sendRedirect("customer_dashboard.jsp");
                }
            } 
            else if(status == 2) 
            {
                response.sendRedirect("log_in.jsp?error=2");
            }
            else if (status == 3)
            {
                response.sendRedirect("log_in.jsp?error=3");
            }
            else
            {
                response.sendRedirect("log_in.jsp?error=1");
            }
         %>
    </body>
</html>
