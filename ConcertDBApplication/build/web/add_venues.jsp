<%-- 
    Document   : add_venues
    Created on : Nov 16, 2024, 6:33:33 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
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
                height: 150vh;
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
        <br><br><br>
        <div class="container-wrapper">
            <div class ="container">
                <br><div class ="title">Add Venue</div><br><br>
                <form action = "venue_processing.jsp">
                    <div class ="input-user">
                        <div class="input-details">
                            <span class="details">Venue Name</span>
                                <input type = "text" id = "venue_name" name = "venue_name" placeholder="Enter the venue name" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Rental Fee</span>
                                <input type = "number" step = "any" id = "rental_fee" name = "rental_fee" placeholder="Enter the rental fee ($)" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Merchandise Cut Percentage</span>
                                <input type = "number" id = "merchandise_cut_percentage" min = "0" max = "100" name = "merchandise_cut_percentage" placeholder="Enter the merchandise cut percentage" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Country</span>
                            <input type = "text" id = "country_name" name = "country_name" placeholder="Enter the country" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">City</span>
                            <input type = "text" id = "city_name" name = "city_name" placeholder="Enter the city" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Street</span>
                            <input type = "text" id = "street" name = "street" placeholder="Enter the street" required><br>
                        </div><br>
                        <div class ="button">
                            <input type = "submit" value = "Add Venue">
                        </div><br>
                                <%
                                    String error = request.getParameter("error");

                                    if(error != null) 
                                    {
                                        if("1".equals(error)) { %>
                                            <span class = "err"> An unknown error occurred. </span> <% 
                                        } else if("2".equals(error)) { %>
                                            <span class = "err"> There is already a venue in this location. </span> <% 
                                        } else if("3".equals(error)) { %>
                                            <span class = "err"> There is already a venue with this name. </span> <% 
                                          }
                                    }
                                    
                                    String success = request.getParameter("status");

                                    if(success != null && success.equals("success"))
                                    {
                                        %> <span class = "success"> Venue successfully added! </span> <%
                                    }
                                    
                                    
                                %>
                    </div>
                </form>
            </div><br><br>
            
            <div class="container">
                <br><div class ="title">Add Section</div><br><br>
                <form action = "venue_section_processing.jsp">
                    <jsp:useBean id = "V" class = "venue_management.Venues" scope = "session" />
                    <div class="input-user">
                        <div class="input-details">
                            <span class="details">Venue</span>
                            <select id = "venues" name = "venues">
                                <%
                                    V.venue_list();
                                    for(int i = 0; i < V.get_venue_ID_list().size(); i++)
                                    {%>
                                        <option value ="<%=V.get_venue_ID_list().get(i)%>"><%=V.get_venue_name_list().get(i)%></option>
                                    <%}
                                %>
                            </select><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Section Name</span>
                            <input type = "text" id = "section_name" name = "section_name" placeholder="Enter the section name" required><br>
                        </div><br>
                        <div class="input-details">
                            <span class="details">Capacity</span>
                            <input type = "number" id = "capacity" min = "1" name = "capacity" placeholder="Enter the section capacity" required><br>
                        </div><br>
                        <div class ="button">
                            <input type = "submit" value = "Add Section">
                        </div><br>
                                <%
                                    if(error != null) 
                                    {
                                        if("4".equals(error)) { %>
                                            <span class = "err"> An unknown error occurred. </span> <% 
                                        } else if("5".equals(error)) { %>
                                            <span class = "err"> There is already a section in this venue with this name. </span> <% 
                                        } 
                                    }
                                    
                                    if(success != null && "success2".equals(success))
                                    {
                                        %> <span class = "success"> Venue section successfully added! </span> <%
                                    }
                                   
                                %>
                    </div>
                </form>
            </div>
        </div><br><br>
    </body>
</html>
