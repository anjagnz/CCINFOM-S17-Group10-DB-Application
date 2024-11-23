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
        <title>Customer View Concerts</title>
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
                <li><a href = "customer_dashboard.jsp">Home</a></li>
                <li><a class = "active" href = "customer_view_concerts.jsp"> Concerts </a></li>
                <li><a href = "buy_tickets.jsp"> Tickets </a></li>
                <li><a href = "buy_merchandise.jsp"> Merchandise </a></li>
                <li><a href = "view_history.jsp"> History </a></li>
                <li><a href ="account_settings.jsp"><i class="fa-solid fa-user" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <br><br><br><br><br><br><br>
        <div class="container-wrapper">
            <div class="container">
                <br><div class ="title">View Concerts</div><br><br>
                <form action = "customer_view_concerts.jsp" method = "GET">
                    <jsp:useBean id = "C" class = "concert_management.Concerts" scope = "session" />
                    <jsp:useBean id = "V" class = "venue_management.Venues" scope = "session" />
                    <jsp:useBean id = "P" class = "performer_management.Performers" scope = "session" />
                    <div class="input-user">
                        <div class="input-details">
                            <span class="details">Concert</span>
                            <select id = "concerts" name = "concerts">
                                <option value="upcoming-concerts">(UPCOMING CONCERTS)</option>
                                <%
                                    C.upcoming_concert_list();
                                    for(int i = 0; i < C.get_concert_ID_list().size(); i++)
                                    {%>
                                        <option value ="<%=C.get_concert_ID_list().get(i)%>"><%=C.get_concert_name_list().get(i)%></option>
                                    <%  
                                        }
                                %>
                            </select><br>
                        </div><br>
                        <div class ="button">
                            <input type = "submit" value = "View">
                        </div><br>
                    </div>
                </form>
            </div><br>
            <%
                String selected = request.getParameter("concerts");
                
                if(selected != null)
                {
                    C.upcoming_concert_list();
                    
                    if("upcoming-concerts".equals(selected))
                    {
                        for(int i = 0; i < C.get_concert_ID_list().size(); i++)
                        {
                            %>
                            <div class="container">
                                <br><div class="subtitle"><%=C.get_concert_name_list().get(i)%></div><br><br>
                                
                                <span class="subdetails"><b>Performer:</b> <%=P.find_performer_name(C.get_performer_ID_list().get(i))%></span><br>
                                <span class="subdetails"><b>Venue:</b> <%=V.find_venue_name(C.get_venue_ID_list().get(i))%></span><br>
                                <span class="subdetails"><b>Location:</b> <%=V.full_location(C.get_venue_ID_list().get(i))%></span><br>
                                <span class="subdetails"><b>Date:</b> <%=C.get_concert_date_list().get(i)%></span><br>
                                <span class="subdetails"><b>Time:</b> <%=C.get_start_time_list().get(i) + "-" + C.get_end_time_list().get(i)%></span><br>
                                
                            </div>
                            <%
                        }
                    }
                    else
                    {
                        int selected_concert = Integer.parseInt(selected);
                        
                        C.concert_list();
                             %>
                            <div class="container">
                                
                                <%int index = C.get_concert_ID_list().indexOf(selected_concert);%>
                                
                                <br><div class="subtitle"><%=C.get_concert_name_list().get(index)%></div><br><br>
                                
                                <span class="subdetails"><b>Performer:</b> <%=P.find_performer_name(C.get_performer_ID_list().get(index))%></span><br>
                                <span class="subdetails"><b>Venue:</b> <%=V.find_venue_name(C.get_venue_ID_list().get(index))%></span><br>
                                <span class="subdetails"><b>Location:</b> <%=V.full_location(C.get_venue_ID_list().get(index))%></span><br>
                                <span class="subdetails"><b>Date:</b> <%=C.get_concert_date_list().get(index)%></span><br>
                                <span class="subdetails"><b>Time:</b> <%=C.get_start_time_list().get(index) + "-" + C.get_end_time_list().get(index)%></span><br>
                                
                            </div>
                            <%
                        
                    }
                    
                    
                    
                }
            %>
        </div><br><br>
    </body>
</html>


