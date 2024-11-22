<%-- 
    Document   : sign_up_processing
    Created on : Nov 13, 2024, 3:58:34 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Processing</title>
    </head>
    <body>
        <jsp:useBean id = "U" class = "user_management.Users" scope = "session" />
        <jsp:useBean id = "Cu" class = "customer_management.Customers" scope = "session" />
         
        <%
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String user_email = request.getParameter("user_email");
            String user_password = request.getParameter("user_password");
            
            U.set_first_name(first_name);
            U.set_last_name(last_name);
            U.set_user_email(user_email);
            U.set_user_password(user_password);
            
            int status = U.sign_up();
            
            if(status == 1)
            {
                Cu.set_user_ID(U.get_user_id());
                status = Cu.add_customer();
                HttpSession curr_session = request.getSession();
                curr_session.setAttribute("user_id", U.get_user_id());
                response.sendRedirect("customer_dashboard.jsp");
            }
            else if(status == 2)
            {
                response.sendRedirect("sign_up.jsp?error=2");
            }
            else
            {
                response.sendRedirect("sign_up.jsp?error=1");
            }
        %>
    </body>
</html>
