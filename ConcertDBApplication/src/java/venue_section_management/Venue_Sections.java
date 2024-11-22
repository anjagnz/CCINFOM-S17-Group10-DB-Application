/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venue_section_management;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Anja
 */

public class Venue_Sections {
    private int section_ID;
    private String section_name;
    private int capacity;
    private int venue_ID;
    
    private ArrayList<Integer> section_ID_list = new ArrayList<>();
    private ArrayList<String> section_name_list = new ArrayList<>();
    private ArrayList<Integer> capacity_list = new ArrayList<>();
    private ArrayList<Integer> venue_ID_list = new ArrayList<>();
        
    public int section_list(int venue_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT s.section_ID, s.section_name, s.capacity, s.venue_ID FROM Venue_Sections s JOIN Venues v ON s.venue_ID = v.venue_ID WHERE v.venue_ID = ?");
            pstmt.setInt(1, venue_ID);
            ResultSet rs = pstmt.executeQuery();
            
            section_ID_list.clear();
            section_name_list.clear();
            capacity_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                section_ID = rs.getInt("section_ID");
                section_name = rs.getString("section_name");
                capacity = rs.getInt("capacity");
                venue_ID = rs.getInt("venue_ID");
                
                section_ID_list.add(section_ID);
                section_name_list.add(section_name);
                capacity_list.add(capacity);
                venue_ID_list.add(venue_ID);
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
    
    public int unassigned_section_list(int concert_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT s.section_ID, s.section_name, s.capacity, s.venue_ID FROM Venue_Sections s JOIN Venues v ON s.venue_ID = v.VENUE_ID JOIN Concerts c on v.venue_ID = c.venue_ID LEFT JOIN Ticket_Types t ON (s.section_ID = t.section_ID AND c.concert_ID = t.concert_ID) WHERE c.concert_ID = ? AND t.ticket_type_ID IS NULL");
            pstmt.setInt(1, concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            section_ID_list.clear();
            section_name_list.clear();
            capacity_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                section_ID = rs.getInt("section_ID");
                section_name = rs.getString("section_name");
                capacity = rs.getInt("capacity");
                venue_ID = rs.getInt("venue_ID");
                
                section_ID_list.add(section_ID);
                section_name_list.add(section_name);
                capacity_list.add(capacity);
                venue_ID_list.add(venue_ID);
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
        
    public int add_venue_section() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Venue_Sections(section_name, capacity, venue_ID) VALUE(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, section_name);
            pstmt.setInt(2, capacity);
            pstmt.setInt(3, venue_ID);
            
            if(find_venue_section(section_name, venue_ID) > 0)
            {
                section_ID = find_venue_section(section_name, venue_ID);
                pstmt.close();
                conn.close();
                return 2;
            }
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys(); 
            
            if(rs.next())
                section_ID = rs.getInt(1);
            
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
    
    public ArrayList<Integer> get_section_ID_list()
    {
        return this.section_ID_list;
    }
    
    public ArrayList<String> get_section_name_list()
    {
        return this.section_name_list;
    }
    
    public ArrayList<Integer> get_capacity_list()
    {
        return this.capacity_list;
    }
    
    public int get_section_ID()
    {
        return this.section_ID;
    }
    
    public int get_capacity()
    {
        return this.capacity;
    }
    
    public void set_section_name(String section_name)
    {
        this.section_name = section_name;
    }
    
    public void set_capacity(int capacity)
    {
        this.capacity = capacity;
    }
    
    public void set_venue_ID(int venue_ID)
    {
        this.venue_ID = venue_ID;
    }
    
    private boolean assigned_to_ticket(int section_ID) throws ClassNotFoundException
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
    
    private int find_venue_section(String section_name, int venue_ID) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT section_ID FROM Venue_Sections WHERE section_name = ? AND venue_ID = ?");
            pstmt.setString(1, section_name);
            pstmt.setInt(2, venue_ID);
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
