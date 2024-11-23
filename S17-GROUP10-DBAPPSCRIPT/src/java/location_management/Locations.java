/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package location_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Locations {

    private int location_ID;
    private String street;
    private int city_ID;
    
    public int add_location() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Locations(street, city_ID) VALUE(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, street);
            pstmt.setInt(2, city_ID);
            
            if(find_location(street, city_ID) > 0)
            {
                location_ID = find_location(street, city_ID);
                pstmt.close();
                conn.close();
                
                if(assigned_to_venue(location_ID))
                    return 2; // location already exists and is assigned to a venue
                else
                    return 1; // location exists but not assigned to a venue
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                location_ID = rs.getInt(1);
            
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
    
    public void set_street(String street)
    {
        this.street = street;
    }
    
    public void set_city_ID(int city_ID)
    {
        this.city_ID = city_ID;
    }
    
    public int get_location_ID()
    {
        return this.location_ID;
    }
    
    private boolean assigned_to_venue(int location_ID) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Venues WHERE location_ID = ?");
            pstmt.setInt(1, location_ID);
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
        
    private int find_location(String street, int city_ID) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT location_ID FROM Locations WHERE street = ? AND city_ID = ?");
            pstmt.setString(1, street);
            pstmt.setInt(2, city_ID);
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
