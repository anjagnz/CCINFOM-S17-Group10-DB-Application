<%-- 
    Document   : ticket_type_processing
    Created on : Nov 19, 2024, 12:02:32 PM
    Author     : Anja
--%>

<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Merchandise Processing</title>
    </head>
    <body>
        <jsp:useBean id = "M" class = "merchandise_management.Merchandise" scope = "session" />
        
        <%
            String item_name = request.getParameter("item_name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity_in_stock = Integer.parseInt(request.getParameter("quantity_in_stock"));
            int concert_ID = Integer.parseInt(request.getParameter("concerts"));
            
            M.set_item_name(item_name);
            M.set_price(price);
            M.set_quantity_in_stock(quantity_in_stock);
            M.set_concert_ID(concert_ID);
            
            int status = M.add_merchandise();
            
            if(status == 0)
            {
                response.sendRedirect("add_merchandise.jsp?error=1");
            }
            else if(status == 2)
            {
                response.sendRedirect("add_merchandise.jsp?error=2");
            }
            else
            {
                response.sendRedirect("add_merchandise.jsp?status=success");
            }
        %>
    </body>
</html>
