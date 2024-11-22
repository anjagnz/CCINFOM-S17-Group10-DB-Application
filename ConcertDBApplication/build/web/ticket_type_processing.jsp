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
        <title>Ticket Type Processing</title>
    </head>
    <body>
        <jsp:useBean id = "T" class = "ticket_type_management.Ticket_Types" scope = "session" />
        
        <%
            String type_name = request.getParameter("type_name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity_in_stock = Integer.parseInt(request.getParameter("quantity_in_stock"));
            int concert_ID = Integer.parseInt(request.getParameter("concert_ID"));
            int section_ID = Integer.parseInt(request.getParameter("venue_sections"));
            
            T.set_type_name(type_name);
            T.set_price(price);
            T.set_quantity_in_stock(quantity_in_stock);
            T.set_concert_ID(concert_ID);
            T.set_section_ID(section_ID);
            
            int status = T.add_ticket_type();
            String url = "add_tickets2.jsp?concerts=" + concert_ID;
            
            if(status == 0)
            {
                response.sendRedirect(url+"&error=1");
            }
            else if(status == 2)
            {
                response.sendRedirect(url+"&error=2");
            }
            else if(status == 3)
            {
                response.sendRedirect(url+"&error=3");
            }
            else
            {
                response.sendRedirect(url+"&status=success");
            }
        %>
    </body>
</html>
