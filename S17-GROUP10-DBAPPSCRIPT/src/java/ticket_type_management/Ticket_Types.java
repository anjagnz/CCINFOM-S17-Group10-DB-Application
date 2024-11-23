/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ticket_type_management;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Anja
 */
public class Ticket_Types {
    private int ticket_type_ID;
    private String type_name;
    private double price;
    private int quantity_in_stock;
    private int concert_ID;
    private int section_ID;
    
    private ArrayList<Integer> ticket_type_ID_list = new ArrayList<>();
    private ArrayList<String> type_name_list = new ArrayList<>();
    private ArrayList<Double> price_list = new ArrayList<>();
    private ArrayList<Integer> quantity_in_stock_list = new ArrayList<>();
    private ArrayList<Integer> concert_ID_list = new ArrayList<>();
    private ArrayList<Integer> section_ID_list = new ArrayList<>();
    
    public int ticket_type_list(int passed_concert_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT ticket_type_ID, type_name, price, quantity_in_stock, concert_ID, section_ID FROM Ticket_Types WHERE concert_ID = ?");
            pstmt.setInt(1, passed_concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_type_ID_list.clear();
            type_name_list.clear();
            price_list.clear();
            quantity_in_stock_list.clear();
            concert_ID_list.clear();
            section_ID_list.clear();
            
            while(rs.next())
            {
                ticket_type_ID = rs.getInt("ticket_type_ID");
                type_name = rs.getString("type_name");
                price = rs.getDouble("price");
                quantity_in_stock = rs.getInt("quantity_in_stock");
                concert_ID = rs.getInt("concert_ID");
                section_ID = rs.getInt("section_ID");
                
                ticket_type_ID_list.add(ticket_type_ID);
                type_name_list.add(type_name);
                price_list.add(price);
                quantity_in_stock_list.add(quantity_in_stock);
                concert_ID_list.add(concert_ID);
                section_ID_list.add(section_ID);
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
    
    public int available_ticket_type_list(int passed_concert_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT ticket_type_ID, type_name, price, quantity_in_stock, concert_ID, section_ID FROM Ticket_Types WHERE concert_ID = ? AND quantity_in_stock > 0");
            pstmt.setInt(1, passed_concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_type_ID_list.clear();
            type_name_list.clear();
            price_list.clear();
            quantity_in_stock_list.clear();
            concert_ID_list.clear();
            section_ID_list.clear();
            
            while(rs.next())
            {
                ticket_type_ID = rs.getInt("ticket_type_ID");
                type_name = rs.getString("type_name");
                price = rs.getDouble("price");
                quantity_in_stock = rs.getInt("quantity_in_stock");
                concert_ID = rs.getInt("concert_ID");
                section_ID = rs.getInt("section_ID");
                
                ticket_type_ID_list.add(ticket_type_ID);
                type_name_list.add(type_name);
                price_list.add(price);
                quantity_in_stock_list.add(quantity_in_stock);
                concert_ID_list.add(concert_ID);
                section_ID_list.add(section_ID);
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
        
    public int add_ticket_type() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Ticket_Types(type_name, price, quantity_in_stock, concert_ID, section_ID) VALUE(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, type_name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity_in_stock);
            pstmt.setInt(4, concert_ID);
            pstmt.setInt(5, section_ID);
            
            if(find_ticket_type(type_name, concert_ID) > 0)
            {
                ticket_type_ID = find_ticket_type(type_name, concert_ID);
                pstmt.close();
                conn.close();
                return 2; // already a ticket in this concert with this name
            }
            
            if(!valid_quantity(quantity_in_stock, section_ID))
            {
                pstmt.close();
                conn.close();
                return 3; // qty exceeds section capacity
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys(); 
            
            if(rs.next())
                ticket_type_ID = rs.getInt(1);
            
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
    
    public ArrayList<Integer> get_ticket_type_ID_list()
    {
        return this.ticket_type_ID_list;
    }
    
    public ArrayList<String> get_type_name_list()
    {
        return this.type_name_list;
    }
    
    public ArrayList<Double> get_price_list()
    {
        return this.price_list;
    }
    
    public ArrayList<Integer> get_quantity_in_stock_list()
    {
        return this.quantity_in_stock_list;
    }
    
    public ArrayList<Integer> get_concert_ID_list()
    {
        return this.concert_ID_list;
    }
    
    public ArrayList<Integer> get_section_ID_list()
    {
        return this.section_ID_list;
    }
    
    public void set_type_name(String type_name)
    {
        this.type_name = type_name;
    }
    
    public void set_price(double price)
    {
        this.price = price;
    }
    
    public void set_quantity_in_stock(int quantity_in_stock)
    {
        this.quantity_in_stock = quantity_in_stock;
    }
    
    public void set_concert_ID(int concert_ID)
    {
        this.concert_ID = concert_ID;
    }
    
    public void set_section_ID(int section_ID)
    {
        this.section_ID = section_ID;
    }
    
    private boolean assigned_to_section(int section_ID) throws ClassNotFoundException
     {    
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Ticket_Types WHERE section_ID = ?");
            pstmt.setInt(1, section_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                rs.close();
                pstmt.close();
                conn.close();
                return true;
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            return false;
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
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
    
    public void update_quantity(int ticket_type_ID, int new_quantity) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Ticket_Types SET quantity_in_stock = ? WHERE ticket_type_ID = ?");
            pstmt.setInt(1, new_quantity);
            pstmt.setInt(2, ticket_type_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private boolean valid_quantity(int quantity_in_stock, int section_ID) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT capacity FROM Venue_Sections WHERE section_ID = ?");
            pstmt.setInt(1, section_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                if(quantity_in_stock > rs.getInt(1))
                {
                    rs.close();
                    pstmt.close();
                    conn.close();
                    return false;
                }
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
       
    }

    public String find_type_name(int ticket_type_ID) throws ClassNotFoundException
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
    
    private int find_ticket_type(String type_name, int concert_ID) throws ClassNotFoundException
    {    
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT ticket_type_ID FROM Ticket_Types WHERE type_name = ? AND concert_ID = ?");
            pstmt.setString(1, type_name);
            pstmt.setInt(2, concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                ID_found = rs.getInt(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return ID_found;
    }
}
