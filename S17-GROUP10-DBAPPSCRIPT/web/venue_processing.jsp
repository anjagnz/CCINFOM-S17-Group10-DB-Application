<%-- 
    Document   : venue_processing
    Created on : Nov 16, 2024, 6:40:03 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "country_management.*" %>
<%@page import = "city_management.*" %>
<%@page import = "location_management.*" %>
<%@page import = "venue_management.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venue Processing</title>
    </head>
    <body>
        <jsp:useBean id = "Co" class = "country_management.Countries" scope = "session" />
        <jsp:useBean id = "Ci" class = "city_management.Cities" scope = "session" />
        <jsp:useBean id = "L" class = "location_management.Locations" scope = "session" />
        <jsp:useBean id = "V" class = "venue_management.Venues" scope = "session" />
        
        <%
            String venue_name = request.getParameter("venue_name");
            double rental_fee = Double.parseDouble(request.getParameter("rental_fee"));
            double merchandise_cut_percentage = Double.parseDouble(request.getParameter("merchandise_cut_percentage"));
            String country_name = request.getParameter("country_name");
            String city_name = request.getParameter("city_name");
            String street = request.getParameter("street");
            
            int status;
            
            Co.set_country_name(country_name);
            status = Co.add_country();
            int country_ID = Co.get_country_ID();
            
            if(status == 0)
            {
                response.sendRedirect("add_venues.jsp?error=1");
            }
            else
            {
                System.out.println("Country: " + Co.get_country_ID());

                Ci.set_city_name(city_name);
                Ci.set_country_id(country_ID);
                status = Ci.add_city();
                int city_ID = Ci.get_city_ID();

                if(status == 0)
                {
                    response.sendRedirect("add_venues.jsp?error=1");
                }
                else
                {
                    System.out.println("City: " + Ci.get_city_ID());

                    L.set_street(street);
                    L.set_city_ID(city_ID);
                    status = L.add_location();
                    int location_ID = L.get_location_ID();

                    if(status == 0)
                    {
                        response.sendRedirect("add_venues.jsp?error=1");
                    }
                    else if(status == 2)
                    {
                        response.sendRedirect("add_venues.jsp?error=2");
                    }
                    else
                    {
                        System.out.println("Location: " + L.get_location_ID());

                        V.set_venue_name(venue_name);
                        V.set_rental_fee(rental_fee);
                        V.set_merchandise_cut_percentage(merchandise_cut_percentage);
                        V.set_location_ID(location_ID);
                        status = V.add_venue();

                        if(status == 0)
                        {
                            response.sendRedirect("add_venues.jsp?error=1");
                        }
                        else if(status == 2)
                        {
                            response.sendRedirect("add_venues.jsp?error=3");
                        }
                        else
                        {
                            response.sendRedirect("add_venues.jsp?status=success");
                        }
                    }
                }
            }
        %>
    </body>
</html>
