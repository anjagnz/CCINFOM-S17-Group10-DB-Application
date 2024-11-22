/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package merchandise_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Merchandise {
    private int item_ID;
    private String item_name;
    private double price;
    private int quantity_in_stock;
    private int concert_ID;
    
    private ArrayList<Integer> item_ID_list = new ArrayList<>();
    private ArrayList<String> item_name_list = new ArrayList<>();
    private ArrayList<Double> price_list = new ArrayList<>();
    private ArrayList<Integer> quantity_in_stock_list = new ArrayList<>();
    private ArrayList<Integer> concert_ID_list = new ArrayList<>();
    
    public int available_merchandise_list(int passed_concert_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT item_ID, item_name, price, quantity_in_stock, concert_ID FROM Merchandise WHERE quantity_in_stock > 0 AND concert_ID = ?");
            pstmt.setInt(1, passed_concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            item_ID_list.clear();
            item_name_list.clear();
            price_list.clear();
            quantity_in_stock_list.clear();
            concert_ID_list.clear();
            
            while(rs.next())
            {
                item_ID = rs.getInt("item_ID");
                item_name = rs.getString("item_name");
                price = rs.getDouble("price");
                quantity_in_stock = rs.getInt("quantity_in_stock");
                concert_ID = rs.getInt("concert_ID");
                
                item_ID_list.add(item_ID);
                item_name_list.add(item_name);
                price_list.add(price);
                quantity_in_stock_list.add(quantity_in_stock);
                concert_ID_list.add(concert_ID);
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
    
    public int add_merchandise() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Merchandise(item_name, price, quantity_in_stock, concert_ID) VALUE(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, item_name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity_in_stock);
            pstmt.setInt(4, concert_ID);
            
            if(find_item(item_name, concert_ID) > 0)
            {
                    pstmt.close();
                    conn.close();
                    item_ID = find_item(item_name, concert_ID);
                    return 2;
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                item_ID = rs.getInt(1);
            
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
    
    public void update_quantity(int item_ID, int new_quantity) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Merchandise SET quantity_in_stock = ? WHERE item_ID = ?");
            pstmt.setInt(1, new_quantity);
            pstmt.setInt(2, item_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public int get_item_ID()
    {
        return this.item_ID;
    }
    
    public int get_quantity_in_stock()
    {
        return this.quantity_in_stock;
    }
    
    public ArrayList<Integer> get_item_ID_list()
    {
        return this.item_ID_list;
    }
    
    public ArrayList<String> get_item_name_list()
    {
        return this.item_name_list;
    }
    
    public ArrayList<Double> get_price_list()
    {
        return this.price_list;
    }
    
    public ArrayList<Integer> get_quantity_in_stock_list()
    {
        return this.quantity_in_stock_list;
    }
    
    public void set_item_name(String item_name)
    {
        this.item_name = item_name;
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
    
    private int find_item(String item_name, int concert_ID) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT item_ID FROM Merchandise WHERE item_name = ? AND concert_ID = ?");
            pstmt.setString(1, item_name);
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
