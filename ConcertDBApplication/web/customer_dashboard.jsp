<%-- 
    Document   : employee_dashboard
    Created on : Nov 15, 2024, 3:36:46 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Dashboard</title>
        <link rel = "stylesheet" href="style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/f8fd180dda.js" crossorigin="anonymous"></script>
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
            
            .container{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                max-width: 1000px;
                width: 100%;
                padding: 30px 30px;
                border-radius: 10px;
                margin: 0 auto;
            }
            
            .container .title{
                font-size: 80px;
                font-weight: 600;
                text-align: center;
                position: relative;
                color: #fff;
                opacity: 0;
                animation: fadeIn 1s forwards;
            }
            
            .container .description{
                font-size: 30px;
                font-weight: 500;
                text-align: center;
                color: #fff;
                opacity: 0;
                animation: fadeIn 1s 1s forwards;
            }
            
            .button-container{
                display: flex;
                gap: 40px;
            }
            
            .button{
                background-color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                flex-direction: column;
                cursor: pointer;
                display: inline-flex;
                justify-content: center;
                opacity: 0;
                animation: fadeIn 1s 2s forwards;
                transition: all 0.3s ease;
            }
            
            .button i{
                font-size: 30px;
            }
            
            .button-title{
                font-size: 16px;
                font-weight: bold;
                color: black;
            }
            
            .button-text{
                font-size: 12px;
                color: black;
            }
            
            .button:hover{
                transform: scale(1.05);
            }

            @keyframes fadeIn{
                to{
                    opacity: 1;
                }
            }
            
        </style>
    </head>
    <body>
        <nav>
            <label class="app-name">ConcertDB</label>
            <ul>
                <li><a class = "active" href = "customer_dashboard.jsp">Home</a></li>
                <li><a href = "customer_view_concerts.jsp"> Concerts </a></li>
                <li><a href = "buy_tickets.jsp"> Tickets </a></li>
                <li><a href = "buy_merchandise.jsp"> Merchandise </a></li>
                <li><a href = "view_history.html"> History </a></li>
                <li><a href ="account_settings.html"><i class="fa-solid fa-user" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <div class ="container">
            <br><br><div class="title">
                <jsp:useBean id = "U" class = "user_management.Users" scope = "session" />
                <% 
                    HttpSession curr_session = request.getSession(false);
                    Integer user_id = (Integer)curr_session.getAttribute("user_id");

                    if(user_id == null)
                        response.sendRedirect("log_in.jsp?error=1");
                    else
                    {
                        Users curr_user = U.get_user(user_id);
                        String first_name = curr_user.get_first_name();
                        String last_name = curr_user.get_last_name();

                %> Welcome, <%=first_name%> <%=last_name%>.</div><br><br>
                <%        
                    }
                %>
                <div class="description">What would you like to do next?</div><br><br><br><br>
            <div class="button-container">
                <button class="button" onclick="window.location.href='customer_view_concerts.jsp'">
                    <br><i class="fa-solid fa-music" style="color: #000000;"></i><br>
                    <span class="button-title">View Concerts</span><br>
                    <span class="button-text">View the upcoming concerts.</span><br>
                </button>
                <button class="button" onclick="window.location.href='buy_tickets.jsp'">
                    <br><i class="fa-solid fa-ticket" style="color: #000000;"></i><br>
                    <span class="button-title">Buy Tickets</span><br>
                    <span class="button-text">Buy tickets to a concert.</span><br>
                </button>
                <button class="button" onclick="window.location.href='buy_merchandise.jsp'">
                    <br><i class="fa-solid fa-credit-card" style="color: #000000;"></i><br>
                    <span class="button-title">Buy Merchandise</span><br>
                    <span class="button-text">Pre-order merchandise from concerts.</span><br>
                </button>
                <button class="button" onclick="window.location.href='view_history.jsp'">
                    <br><i class="fa-solid fa-shirt" style="color: #000000;"></i><br>
                    <span class="button-title">View History</span><br>
                    <span class="button-text">View your purchase history.</span><br>
                </button>                
            </div>
        </div>
    </body>
</html>
