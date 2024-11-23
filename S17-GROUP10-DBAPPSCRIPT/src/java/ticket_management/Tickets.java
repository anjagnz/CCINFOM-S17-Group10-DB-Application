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
    
    private ArrayList<Integer> ticket_ID_list = new ArrayList<Integer>();
    private ArrayList<Integer> ticket_type_ID_list = new ArrayList<Integer>();
    private ArrayList<Integer> transaction_ID_list = new ArrayList<Integer>();
    
    public int customer_ticket_list(int customer_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT ti.ticket_ID, ti.ticket_type_ID, ti.transaction_ID FROM Tickets ti JOIN Transactions tr ON ti.transaction_ID = tr.transaction_ID WHERE tr.customer_ID = ?");
            pstmt.setInt(1, customer_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_ID_list.clear();
            ticket_type_ID_list.clear();
            transaction_ID_list.clear();
            
            while(rs.next())
            {
                ticket_ID = rs.getInt("ti.ticket_ID");
                ticket_type_ID = rs.getInt("ti.ticket_type_ID");
                transaction_ID = rs.getInt("ti.transaction_ID");
                
                ticket_ID_list.add(ticket_ID);
                ticket_type_ID_list.add(ticket_type_ID);
                transaction_ID_list.add(transaction_ID);
            }
            
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
    
    public ArrayList<Integer> get_ticket_ID_list()
    {
        return this.ticket_ID_list;
    }
    
    public ArrayList<Integer> get_ticket_type_ID_list()
    {
        return this.ticket_type_ID_list;
    }
    
    public ArrayList<Integer> get_transaction_ID_list()
    {
        return this.transaction_ID_list;
    }
    
    public String find_customer_name(int transaction_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT last_name, first_name FROM Transactions tr JOIN Customers c ON tr.customer_ID = c.customer_ID JOIN Users u ON c.user_id = u.user_ID WHERE transaction_ID = ?");
            pstmt.setInt(1, transaction_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString("last_name").concat(", ").concat(rs.getString("first_name"));
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
    
    public String find_concert_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT c.concert_name FROM Ticket_Types tt JOIN Concerts c on tt.concert_ID = c.concert_ID WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
    
    public String find_venue_name_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT v.venue_name FROM Ticket_Types tt JOIN Concerts c on tt.concert_ID = c.concert_ID JOIN Venues v ON c.venue_ID = v.venue_ID WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
    
    public String find_section_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT s.section_name FROM Ticket_Types tt JOIN Venue_Sections s on tt.section_ID = s.section_ID WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
    
    public String find_type_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT type_name FROM Ticket_Types WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
        
    public String find_performer_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT p.performer_name FROM Ticket_Types tt JOIN Concerts c on tt.concert_ID = c.concert_ID JOIN Performers p on c.performer_ID = p.performer_ID WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name_found = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
    }
        
    public String find_time_from_ticket(int ticket_type_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT c.concert_date, c.start_time, c.end_time FROM Ticket_Types tt JOIN Concerts c ON tt.concert_ID = c.concert_ID WHERE ticket_type_ID = ?");
            pstmt.setInt(1, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                name_found = rs.getString("c.concert_date").concat(", ").concat(rs.getString("c.start_time")).concat("-").concat(rs.getString("c.end_time"));
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name_found;
    
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
