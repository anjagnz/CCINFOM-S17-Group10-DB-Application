/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package performer_management;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Anja
 */
public class Performers {
    private int performer_ID;
    private String performer_name;
    
    public int add_performer() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Performers(performer_name) VALUE(?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, performer_name);
            
            if(find_performer(performer_name) > 0)
            {
                    pstmt.close();
                    conn.close();
                    performer_ID = find_performer(performer_name);
                    return 1;
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                performer_ID = rs.getInt(1);
            
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
    
    public int get_performer_ID()
    {
        return this.performer_ID;
    }
    
    public void set_performer_name(String performer_name)
    {
        this.performer_name = performer_name;
    }
    
    public String find_performer_name(int performer_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT performer_name FROM Performers WHERE performer_ID = ?");
            pstmt.setInt(1, performer_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                name = rs.getString(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return name;
    }
    
    private int find_performer(String performer_name) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT performer_ID FROM Performers WHERE performer_name = ?");
            pstmt.setString(1, performer_name);
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
