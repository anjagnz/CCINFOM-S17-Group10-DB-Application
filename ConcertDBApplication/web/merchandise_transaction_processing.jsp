<%-- 
    Document   : ticket_transaction_processing
    Created on : Nov 21, 2024, 3:00:38 PM
    Author     : Anja
--%>

<%@page import = "java.util.*"%>
<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Transaction Processing</title>
    </head>
    <body>
        <jsp:useBean id = "M" class = "merchandise_management.Merchandise" scope = "session" />
        <jsp:useBean id = "MP" class = "merchandise_purchase_management.Merchandise_Purchases" scope = "session" />
        <jsp:useBean id = "Tr" class = "transaction_management.Transactions" scope = "session" />
        <jsp:useBean id = "Cu" class = "customer_management.Customers" scope = "session" />
        
        <%
            int customer_ID = 0;
            HttpSession curr_session = request.getSession(false);
            Integer user_id = (Integer)curr_session.getAttribute("user_id");
            
            if(curr_session != null)
            {
                try{
                    customer_ID = Cu.find_customer_ID(user_id);
                }catch(NumberFormatException e){}
                
                int concert_ID = Integer.parseInt(request.getParameter("concert_ID"));
                String url = "buy_merchandise2.jsp?concerts=" + concert_ID;
                double sum = 0;
                
                if(customer_ID == 0)
                {
                    response.sendRedirect(url + "&error=1");
                    return;
                }
                else
                {
                    int quantity_bought, status, new_quantity;
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    M.available_merchandise_list(concert_ID);
                    boolean bought = false;

                    Tr.set_date_of_transaction(timestamp);
                    Tr.set_customer_ID(customer_ID);
                    status = Tr.add_transaction();
                    int transaction_ID = Tr.get_transaction_ID();

                    if(status == 0)
                    {
                        response.sendRedirect(url + "&error=2");
                        return;
                    }
                    else
                    {
                        for(int i = 0; i < M.get_item_ID_list().size(); i++)
                        {
                            quantity_bought = Integer.parseInt(request.getParameter(String.valueOf(i)));

                            if(quantity_bought == 0)
                                continue;
                            else if(quantity_bought >M.get_quantity_in_stock_list().get(i))
                            {
                                response.sendRedirect(url + "&error=2");
                                return;
                            }
                            else
                            {
                                MP.set_quantity_bought(quantity_bought);
                                MP.set_item_ID(M.get_item_ID_list().get(i));
                                MP.set_transaction_ID(transaction_ID);
                                status = MP.add_merchandise_purchase();
                                    
                                sum += M.get_price_list().get(i)*quantity_bought;
                                new_quantity = M.get_quantity_in_stock_list().get(i) - quantity_bought;
                                M.update_quantity(M.get_item_ID_list().get(i), new_quantity);
                                M.get_quantity_in_stock_list().set(i, new_quantity);
                                bought = true;
                            }
                        }

                        if(bought == false)
                        {
                            response.sendRedirect(url + "&error=3");
                            return;
                        }    
                        else
                        {
                            response.sendRedirect(url + "&status=" + sum);
                            return;
                        }
                    }
                }
            }
            else
            {
                response.sendRedirect("log_in.jsp?error=1");
            }
            
        %>
    </body>
</html>
