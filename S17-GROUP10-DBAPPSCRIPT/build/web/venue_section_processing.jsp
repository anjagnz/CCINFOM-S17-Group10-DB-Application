<%-- 
    Document   : venue_section_processing
    Created on : Nov 16, 2024, 10:30:37 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "venue_management.*" %>
<%@page import = "venue_section_management.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Section Processing</title>
    </head>
    <body>
        <jsp:useBean id = "V" class = "venue_management.Venues" scope = "session" />
        <jsp:useBean id = "S" class = "venue_section_management.Venue_Sections" scope = "session" />
        
        <%
            int venue_ID = Integer.parseInt(request.getParameter("venues"));
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String section_name = request.getParameter("section_name");
            
            S.set_venue_ID(venue_ID);
            S.set_capacity(capacity);
            S.set_section_name(section_name);
            
            int status = S.add_venue_section();
            
            if(status == 0)
            {
                response.sendRedirect("add_venues.jsp?error=4");
            }
            else if(status == 2)
            {
                response.sendRedirect("add_venues.jsp?error=5");
            }
            else
            {
                response.sendRedirect("add_venues.jsp?status=success2");
            }
        %>
    </body>
</html>
