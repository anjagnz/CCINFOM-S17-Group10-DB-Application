/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticket_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Tickets {
    private int ticket_ID;
    private int ticket_type_ID;
    private int transaction_ID;
    
    public int add_ticket() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Tickets(ticket_type_ID, transaction_ID) VALUE(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, ticket_type_ID);
            pstmt.setInt(2, transaction_ID);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                ticket_ID = rs.getInt(1);
            
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
    
    public int get_ticket_ID()
    {
        return this.ticket_ID;
    }
    
    public void set_ticket_type_ID(int ticket_type_ID)
    {
        this.ticket_type_ID = ticket_type_ID;
    }
    
    public void set_transaction_ID(int transaction_ID)
    {
        this.transaction_ID = transaction_ID;
    }
}
