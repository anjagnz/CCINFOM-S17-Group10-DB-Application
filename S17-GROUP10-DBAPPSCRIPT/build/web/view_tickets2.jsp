<%-- 
    Document   : add_concerts
    Created on : Nov 15, 2024, 6:44:44 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Concerts</title>
        <link rel = "stylesheet" href="style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            
             *{
                text-decoration: none;
                list-style: none;
                box-sizing: border-box;
                padding: 0;
                margin: 0;
                justify-content: center;
                align-items: center;
            }
            
            body{
                height: 100vh;
                background: linear-gradient(90deg, #6a0dad, #dda0dd);
                justify-content: center;
                align-items: center;
            }
            
            nav{
                background: #fff;
                height: 80px;
                width: 100%;
            }
            
            label.app-name{
                color: black;
                font-size: 30px;
                line-height: 80px;
                padding: 0 70px;
                font-weight: bold;
            }
            
            nav ul{
                float: right;
                margin-right: 20px;
            }
            
            nav ul li{
                display: inline-block;
                line-height: 80px;
                margin: 0 8px;
            }
            
            nav ul li a{
                color: black;
                font-weight: bold;
                font-size: 14px;
                text-transform: uppercase;
                padding: 8px 8px;
                border-radius: 5px;
            }
            
            a.active, a:hover{
                background: #dda0dd;
                transition: .3s ease;
            }
            
            .container-wrapper{
                display: flex;
                flex-direction: column;
                gap: 5px;
                width: 100%;
                justify-content: center;
                align-items: center;
            }
            
            .container{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                max-width: 550px;
                width: 100%;
                background: #fff;
                padding: 30px 30px;
                border-radius: 10px;
            }
            
            .container .title{
                font-size: 30px;
                font-weight: 600;
                text-align: center;
                position: relative;
            }
            
             .container .subtitle{
                font-size: 25px;
                font-weight: 600;
                text-align: center;
                position: relative;
            }
            
            .container .subdetails{
                font-size: 16px;
                text-align: center;
                position: relative;
            }
            
            .container .input-user{
                display: flex;
                flex-direction: column;
                justify-content: center;
                width: 100%;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            
            form .input-details{
                width: 100%;
            }
            
            .input-user .input-details .details{
                padding-left: 5px;
                font-weight: 400;
                font-size: 14px;
            }
            
            .input-user .input-details input{
                height: 30px;
                width: 100%;
                justify-content: center;
                outline: none;
                border-radius: 5px;
                border: 1px solid #C0C0C0;
                padding-left: 9px;
                font-size: 13px;
                transition: all 0.2s ease;
                box-sizing: border-box;
            }
            
            .input-user .input-details input:focus,
            .input-user .input-details input:valid{
                border-color: #dda0dd;
            }
            
            .input-user .err{
                color: darkred;
                font-size: 14px;
                text-align: center;
            }
            
            .input-user .success{
                color: green;
                font-size: 14px;
                text-align: center;
            }
            
            form .button{
                display: flex;
                justify-content: center;
                height: 30px;
                width: 100%;
                margin-top: 30px;
            }
            
            form .button input{
                height: 100%;
                width: 100%;
                outline: none;
                color: #fff;
                border: none;
                border-radius: 5px;
                background: linear-gradient(90deg, #6a0dad, #dda0dd);
                font-size: 16px;
                transition: all 0.3s ease;
                cursor: pointer;
            }
            
            form .button input:hover{
                transform: scale(1.05);
            }
        </style>
    </head>
    <body>
        <nav>
            <label class="app-name">ConcertDB</label>
            <ul>
                <li><a href = "employee_dashboard.jsp">Home</a></li>
                <li><a class = "active" href = "manage_records.jsp"> Records </a></li>
                <li><a href = "view_reports.html"> Reports </a></li>
                <li><a href = "view_transactions.html"> Transactions </a></li>
                <li><a href = "view_users.html"> Users </a></li>
                <li><a href ="account_settings.html"><i class="fa-solid fa-user-tie" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <br><br><br><br><br><br><br>
        <jsp:useBean id = "TT" class = "ticket_type_management.Ticket_Types" scope = "session" />
        
        <%
            int concert_ID = 0;
            
            if(request.getParameter("concerts") == null)
            {
                response.sendRedirect("view_tickets.jsp?error=2");
            }
            else
            {
                concert_ID = Integer.parseInt(request.getParameter("concerts"));

                TT.ticket_type_list(concert_ID);
               
                if(TT.get_ticket_type_ID_list().size() == 0)
                {
                    response.sendRedirect("view_tickets.jsp?error=1");
                }
            }
        %>
        <div class="container-wrapper">
            <div class="container">
                <br><div class ="title">View Tickets</div><br><br>
            </div>
                    <%
                        for(int i = 0; i < TT.get_ticket_type_ID_list().size(); i++)
                        {%>
                            <div class="container">
                                <br><div class="subtitle"><%=TT.get_type_name_list().get(i)%></div><br><br>
                                <span class="subdetails"><b>Ticket Type ID: </b> <%=TT.get_ticket_type_ID_list().get(i)%></span><br>
                                <span class="subdetails"><b>Price: </b> <%=TT.get_price_list().get(i)%>$</span><br>
                                <span class="subdetails"><b>Quantity in Stock: </b> <%=TT.get_quantity_in_stock_list().get(i)%></span><br>
                                <span class="subdetails"><b>Concert ID:</b> <%=TT.get_concert_ID_list().get(i) + " (" + TT.find_concert_from_ticket(TT.get_ticket_type_ID_list().get(i))+")"%></span><br>
                                <span class="subdetails"><b>Section ID: </b> <%=TT.get_section_ID_list().get(i) + " (" + TT.find_section_from_ticket(TT.get_ticket_type_ID_list().get(i)) + ")"%></span><br>
                            </div><%
                        }
                        %>
            </div><br>
        <br><br>
    </body>
</html>


