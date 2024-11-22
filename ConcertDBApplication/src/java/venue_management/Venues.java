/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venue_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Venues {
    
    private int venue_ID;
    private String venue_name;
    private double rental_fee;
    private double merchandise_cut_percentage;
    private int location_ID;
    
    private ArrayList<Integer> venue_ID_list = new ArrayList<>();
    private ArrayList<String> venue_name_list = new ArrayList<>();
    private ArrayList<Double> rental_fee_list = new ArrayList<>();
    private ArrayList<Double> merchandise_cut_percentage_list = new ArrayList<>();
    private ArrayList<Integer> location_ID_list = new ArrayList<>();
    
    public int venue_list() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT venue_ID, venue_name, rental_fee, merchandise_cut_percentage, location_ID FROM Venues");
            ResultSet rs = pstmt.executeQuery();
            
            venue_ID_list.clear();
            venue_name_list.clear();
            rental_fee_list.clear();
            merchandise_cut_percentage_list.clear();
            location_ID_list.clear();
            
            while(rs.next())
            {
                venue_ID = rs.getInt("venue_ID");
                venue_name = rs.getString("venue_name");
                rental_fee = rs.getDouble("rental_fee");
                merchandise_cut_percentage = rs.getDouble("merchandise_cut_percentage");
                location_ID = rs.getInt("location_ID");
                venue_ID_list.add(venue_ID);
                venue_name_list.add(venue_name);
                rental_fee_list.add(rental_fee);
                merchandise_cut_percentage_list.add(merchandise_cut_percentage);
                location_ID_list.add(location_ID);
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
    
    public int add_venue() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Venues(venue_name, rental_fee, merchandise_cut_percentage, location_ID) VALUE(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, venue_name);
            pstmt.setDouble(2, rental_fee);
            pstmt.setDouble(3, merchandise_cut_percentage);
            pstmt.setInt(4, location_ID);
            
            if(find_venue(venue_name) > 0)
            {
                venue_ID = find_venue(venue_name);
                pstmt.close();
                conn.close();
                return 2;
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys(); 
            
            if(rs.next())
                venue_ID = rs.getInt(1);
            
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
    
    public ArrayList<Integer> get_venue_ID_list()
    {
        return this.venue_ID_list;
    }
    
    public ArrayList<String> get_venue_name_list()
    {
        return this.venue_name_list;
    }
    
    public ArrayList<Double> get_rental_fee_list()
    {
        return this.rental_fee_list;
    }
    
    public ArrayList<Double> get_merchandise_cut_percentage_list()
    {
        return this.merchandise_cut_percentage_list;
    }
    
    public ArrayList<Integer> get_location_ID_list()
    {
        return this.location_ID_list;
    }
    
    public void set_venue_name(String venue_name)
    {
        this.venue_name = venue_name;
    }
    
    public void set_rental_fee(double rental_fee)
    {
        this.rental_fee = rental_fee;
    }
    
    public void set_merchandise_cut_percentage(double merchandise_cut_percentage)
    {
        this.merchandise_cut_percentage = merchandise_cut_percentage;
    }
    
    public void set_location_ID(int location_ID)
    {
        this.location_ID = location_ID;
    }
    
    public String full_location(int venue_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT lo.street, ci.city_name, co.country_name FROM Venues ve JOIN Locations lo ON ve.location_ID = lo.location_ID JOIN Cities ci ON lo.city_ID = ci.city_ID JOIN countries co ON ci.country_ID = co.country_ID WHERE venue_ID = ?");
            pstmt.setInt(1, venue_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                name = name.concat(rs.getString("lo.street")).concat(", ");
                name = name.concat(rs.getString("ci.city_name")).concat(", ");
                name = name.concat(rs.getString("co.country_name"));
            }
            
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
    
    public String find_street(int location_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT street FROM Venues ve JOIN Locations lo ON ve.location_ID = lo.location_ID WHERE ve.location_ID = ?");
            pstmt.setInt(1, location_ID);
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
    
    public String find_city(int location_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT city_name FROM Locations lo JOIN Cities ci ON lo.city_ID = ci.city_ID WHERE location_ID = ?");
            pstmt.setInt(1, location_ID);
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
    
    public String find_country(int location_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT country_name FROM Locations lo JOIN Cities ci ON lo.city_ID = ci.city_ID JOIN Countries co ON ci.country_ID = co.country_ID WHERE location_ID = ?");
            pstmt.setInt(1, location_ID);
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
    
    public String find_venue_name(int venue_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT venue_name FROM Venues WHERE venue_ID = ?");
            pstmt.setInt(1, venue_ID);
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

    private int find_venue(String venue_name) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT venue_ID FROM Venues WHERE venue_name = ?");
            pstmt.setString(1, venue_name);
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
