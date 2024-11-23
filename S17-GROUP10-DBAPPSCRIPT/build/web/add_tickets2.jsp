<%-- 
    Document   : add_tickets2
    Created on : Nov 15, 2024, 6:44:44 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Tickets</title>
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
        <jsp:useBean id = "S" class = "venue_section_management.Venue_Sections" scope = "session" />
        <%
            int concert_ID = Integer.parseInt(request.getParameter("concerts"));
            S.unassigned_section_list(concert_ID);
            if(S.get_section_ID_list().size() == 0)
            {
                response.sendRedirect("add_tickets.jsp?error=1");
            }
        %>
        <nav>
            <label class="app-name">ConcertDB</label>
            <ul>
                <li><a href = "employee_dashboard.jsp">Home</a></li>
                <li><a class = "active" href = "manage_records.jsp"> Records </a></li>
                <li><a href = "view_reports.jsp"> Reports </a></li>
                <li><i class="fa-solid fa-user-tie" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <br><br><br><br>
        <div class="container-wrapper">
            <div class="container">
                <br><div class ="title">Add Tickets</div><br><br>
                <form action = "ticket_type_processing.jsp" method = "post">
                    <input type="hidden" name="check" id="check" value=""/>
                    <div class="input-user">
                        <input type="hidden" id ="concert_ID" name="concert_ID" value="<%=concert_ID%>">
                        <div class="input-details">
                            <span class="details">Assigned Section</span>
                            <select id = "venue_sections" name = "venue_sections">
                                <%
                                    S.unassigned_section_list(concert_ID);
                                    
                                    for(int i = 0; i < S.get_section_ID_list().size(); i++)
                                    {%>
                                        <option value ="<%=S.get_section_ID_list().get(i)%>"><%=S.get_section_name_list().get(i)+ " (Capacity: " + S.get_capacity_list().get(i) + ")"%></option>
                                    <%
                                        }
                                %>
                            </select><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Ticket Type</span>
                                <input type = "text" id = "type_name" name = "type_name" placeholder="Enter the ticket type" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Price</span>
                            <input type = "number" step = "any" id = "price" min = "1" name = "price" placeholder="Enter the ticket price" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Quantity</span>
                            <input type = "number" id = "quantity_in_stock" min = "1" name = "quantity_in_stock" placeholder="Enter the ticket quantity" required><br>
                        </div><br>
                        <div class ="button">
                            <input type = "submit" value = "Add Tickets">
                        </div><br>
                        <%
                                    String error = request.getParameter("error");

                                    if(error != null) 
                                    {
                                        if("1".equals(error)) { %>
                                            <span class = "err"> An unknown error occurred. </span> <% 
                                        } else if("2".equals(error)) { %>
                                            <span class = "err"> There is already a ticket type with this name. </span> <% 
                                        } else if("3".equals(error)) { %>
                                            <span class = "err"> The quantity can't exceed the section's capacity. </span> <% 
                                        }
                                    }
                                    
                                    String success = request.getParameter("status");

                                    if(success != null && success.equals("success"))
                                    {
                                        %> <span class = "success"> Tickets successfully added! </span> <%
                                    }
                                %>
                    </div>
                </form>
            </div>
        </div><br><br>
    </body>
</html>


