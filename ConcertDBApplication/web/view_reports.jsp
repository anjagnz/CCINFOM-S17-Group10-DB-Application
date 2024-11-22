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
        <title>View Reports</title>
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
            
            
            .concert-button-container, .venue-button-container, .merchandise-button-container, .ticket-button-container{
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
                <li><a href = "employee_dashboard.jsp">Home</a></li>
                <li><a href = "manage_records.jsp"> Records </a></li>
                <li><a class = "active" href = "view_reports.jsp"> Reports </a></li>
                <li><a href = "view_transactions.html"> Transactions </a></li>
                <li><a href = "view_users.html"> Users </a></li>
                <li><a href ="account_settings.html"><i class="fa-solid fa-user-tie" style="color: #000000;"></i></a></li>
            </ul>
        </nav>
        <br><br><br>
        <div class ="container">
            <br><br><br><div class="concert-button-container">
                <button class="button" onclick="window.location.href='ticket_sales_report.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-plus" style="color: #000000;"></i><i class="fa-solid fa-music" style="color: #000000;"></i></span><br>
                    <span class="button-title">Concert Ticket Sales Report</span><br>
                </button>
                <button class="button" onclick="window.location.href='view_concerts.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i><i class="fa-solid fa-music" style="color: #000000;"></i></span><br>
                    <span class="button-title">Concert Merchandise Sales Report</span><br>
                </button>
                <button class="button" onclick="window.location.href='view_concerts.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i><i class="fa-solid fa-music" style="color: #000000;"></i></span><br>
                    <span class="button-title">Concert Total Sales Report</span><br>
                </button>
            </div><br><br>
            <div class="venue-button-container">
                <button class="button" onclick="window.location.href='add_venues.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-plus" style="color: #000000;"></i><i class="fa-solid fa-building" style="color: #000000;"></i></span><br>
                    <span class="button-title">Venue Rental Sales Report</span><br>
                </button>
                <button class="button" onclick="window.location.href='view_venues.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i><i class="fa-solid fa-building" style="color: #000000;"></i></span><br>
                    <span class="button-title">Venue Merchandise Sales Report</span><br>
                </button>
                <button class="button" onclick="window.location.href='view_venues.jsp'">
                    <br><span class="icon"><i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i><i class="fa-solid fa-building" style="color: #000000;"></i></span><br>
                    <span class="button-title">Venue Total Sales Report</span><br>
                </button>
            </div><br><br>
        </div>
    </body>
</html>

</html>
