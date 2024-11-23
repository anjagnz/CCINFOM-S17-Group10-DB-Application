/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package city_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Cities {
    private int city_ID;
    private String city_name;
    private int country_ID;
    
    public int add_city() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
                    
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Cities(city_name, country_ID) VALUE(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, city_name);
            pstmt.setInt(2, country_ID);
            
            if(find_city(city_name, country_ID) == 0)
            {
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    city_ID = rs.getInt(1);
            }
            else
            {
                city_ID = find_city(city_name, country_ID);
            }
            
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
    
    public void set_city_name(String city_name)
    {
        this.city_name = city_name;
    }
    
    public void set_country_id(int country_ID)
    {
        this.country_ID = country_ID;
    }
    
    public int get_city_ID()
    {
        return this.city_ID;
    }
    
    private int find_city(String city_name, int country_ID) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT city_ID FROM Cities WHERE city_name = ? AND country_ID = ?");
            pstmt.setString(1, city_name);
            pstmt.setInt(2, country_ID);
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
