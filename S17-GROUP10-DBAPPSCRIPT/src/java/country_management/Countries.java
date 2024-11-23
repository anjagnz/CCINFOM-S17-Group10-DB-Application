/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package country_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Countries {
    private int country_ID;
    private String country_name;
    
    public int add_country() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Countries(country_name) VALUE(?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, country_name);
            
            if(find_country(country_name) == 0)
            {
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    country_ID = rs.getInt(1);
                rs.close();
            }
            else
            {
                country_ID = find_country(country_name);
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
    
    public void set_country_name(String country_name)
    {
        this.country_name = country_name;
    }
    
    public int get_country_ID()
    {
        return this.country_ID;
    }
    
    private int find_country(String country_name) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT country_ID FROM Countries WHERE country_name = ?");
            pstmt.setString(1, country_name);
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
