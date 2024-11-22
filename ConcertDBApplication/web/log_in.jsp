<%-- 
    Document   : log_in
    Created on : Nov 14, 2024, 11:57:07 AM
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
            body{
                display: flex;
                height: 100vh;
                justify-content: center;
                align-items: center;
                background: linear-gradient(90deg, #6a0dad, #dda0dd);
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
                opacity: 0;
                animation: fadeIn 1s forwards;
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
            
            @keyframes fadeIn{
                to{
                    opacity: 1;
                }
            }
        </style>
    </head>
    <body>
        <div class ="container">
            <br><div class ="title">ConcertDB Account Log In</div><br><br>
            <form action = "log_in_processing.jsp">
                <div class ="input-user">
                    <div class="input-details">
                        <span class="details">Email</span>
                            <input type = "email" id = "user_email" name = "user_email" placeholder="Enter your email" required><br>
                    </div><br>
                    <div class="input-details">
                        <span class="details">Password</span>
                            <input type = "password" id = "user_password" name = "user_password" placeholder="Enter your password" required><br>
                    </div><br>
                    <div class ="button">
                        <input type = "submit" value = "Log In">
                    </div><br>
                            <%
                                String error = request.getParameter("error");

                                if(error != null) 
                                {
                                    if("1".equals(error)) { %>
                                        <span class = "err"> An unknown error occurred. </span> <% 
                                    } else if("2".equals(error)) { %>
                                        <span class = "err"> User not found. </span> <% 
                                    } else if("3".equals(error)) { %>
                                        <span class = "err"> Password does not match. </span> <% 
                                    }
                                }      

                            %>
                </div>
            </form>
        </div>
    </body>
</html>
