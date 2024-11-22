<%-- 
    Document   : concert_processing
    Created on : Nov 19, 2024, 12:02:32 PM
    Author     : Anja
--%>

<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Concert Processing</title>
    </head>
    <body>
        <jsp:useBean id = "C" class = "concert_management.Concerts" scope = "session" />
        <jsp:useBean id = "P" class = "performer_management.Performers" scope = "session" />
        
        <%
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            String concert_name = request.getParameter("concert_name");
            String performer_name = request.getParameter("performer_name");
            int venue_ID = Integer.parseInt(request.getParameter("venues"));
            Date concert_date = Date.valueOf(request.getParameter("concert_date"));
            Time start_time = Time.valueOf(request.getParameter("start_time") + ":00");
            Time end_time = Time.valueOf(request.getParameter("end_time") + ":00");

            P.set_performer_name(performer_name);
            int status = P.add_performer();
            int performer_ID = P.get_performer_ID();

            if(status == 0)
            {
                response.sendRedirect("add_concerts.jsp?error=1");
            }
            else
            {
                C.set_concert_name(concert_name);
                C.set_transaction_date(timestamp);
                C.set_concert_date(concert_date);
                C.set_start_time(start_time);
                C.set_end_time(end_time);
                C.set_concert_status("UPCOMING");
                C.set_performer_ID(performer_ID);
                C.set_venue_ID(venue_ID);
                status = C.add_concert();

                if(status == 0)
                {
                    response.sendRedirect("add_concerts.jsp?error=1");
                }
                else if(status == 2)
                {
                    response.sendRedirect("add_concerts.jsp?error=2");
                }
                else if(status == 3)
                {
                    response.sendRedirect("add_concerts.jsp?error=3");
                }
                else if(status == 4)
                {
                    response.sendRedirect("add_concerts.jsp?error=4");
                }
                else
                {
                    response.sendRedirect("add_concerts.jsp?status=success");
                }
            }
        %>
    </body>
</html>
