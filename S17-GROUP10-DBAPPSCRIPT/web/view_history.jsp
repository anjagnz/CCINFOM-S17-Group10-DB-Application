<%-- 
    Document   : add_records
    Created on : Nov 15, 2024, 3:58:58 PM
    Author     : Anja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "user_management.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Records</title>
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
                flex-direction: row;
                justify-content: center;
                align-items: center;
                max-width: 1000px;
                width: 100%;
                padding: 30px 30px;
                border-radius: 10px;
                margin: 0 auto;
                gap: 20px;
            }
            
            
            .merchandise-button-container, .ticket-button-container{
                display: flex;
                gap: 40px;
                flex-direction: column;
            }
            
            .button{
                background-color: #fff;
                border: none;
                width: 200px;
                height: 120px;
                border-radius: 5px;
                padding: 10px 20px;
                flex-direction: column;
                cursor: pointer;
                display: inline-flex;
                justify-content: center;
                opacity: 0;
                animation: fadeIn 1s forwards;
                transition: all 0.3s ease;
            }
            
            .button i{
                font-size: 20px;
            }
            
            .icon{
                display: inline-flex;
                flex-direction: row;
                gap: 5px;
            }
            
            .button-title{
                font-size: 16px;
                font-weight: bold;
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
                <li><a href = "customer_dashboard.jsp">Home</a></li>
                <li><a href = "customer_view_concerts.jsp"> Concerts </a></li>
                <li><a href = "buy_tickets.jsp"> Tickets </a></li>
                <li><a href = "buy_merchandise.jsp"> Merchandise </a></li>
                <li><a class = "active" href = "view_history.jsp"> History </a></li>
                <li><a href ="account_settings.jsp"><i class="fa-solid fa-user" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <br><br><br><br><br><br><br><br><br>
        <div class ="container">
            <div class="ticket-button-container">
                <button class="button" onclick="window.location.href='ticket_history.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-ticket" style="color: #000000;"></i></span><br>
                    <span class="button-title">Ticket Purchases</span><br>
                </button>
            </div><br><br>
            <div class="merchandise-button-container">
                <button class="button" onclick="window.location.href='merchandise_history.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-shirt" style="color: #000000;"></i></span><br>
                    <span class="button-title">Merchandise Purchases</span><br>
                </button>
                </button>
            </div><br>
        </div>
    </body>
</html>

