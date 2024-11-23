/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package merchandise_purchase_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Merchandise_Purchases {
    private int merchandise_purchase_ID;
    private int quantity_bought;
    private int item_ID;
    private int transaction_ID;
    
    private ArrayList<Integer> merchandise_purchase_ID_list = new ArrayList<Integer>();
    private ArrayList<Integer> quantity_bought_list = new ArrayList<Integer>();
    private ArrayList<Integer> item_ID_list = new ArrayList<Integer>();
    private ArrayList<Integer> transaction_ID_list = new ArrayList<Integer>();
    
    public int customer_purchase_list(int customer_ID) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT merchandise_purchase_ID, quantity_bought, mp.item_ID, tr.transaction_ID FROM Merchandise_Purchases mp JOIN Transactions tr ON mp.transaction_ID = tr.transaction_ID WHERE tr.customer_ID = ?");
            pstmt.setInt(1, customer_ID);
            ResultSet rs = pstmt.executeQuery();
            
            merchandise_purchase_ID_list.clear();
            quantity_bought_list.clear();
            item_ID_list.clear();
            transaction_ID_list.clear();
            
            while(rs.next())
            {
                merchandise_purchase_ID = rs.getInt("merchandise_purchase_ID");
                quantity_bought = rs.getInt("quantity_bought");
                item_ID = rs.getInt("mp.item_ID");
                transaction_ID = rs.getInt("tr.transaction_ID");
                
                merchandise_purchase_ID_list.add(merchandise_purchase_ID);
                quantity_bought_list.add(quantity_bought);
                item_ID_list.add(item_ID);
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
    
    public int add_merchandise_purchase() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Merchandise_Purchases(quantity_bought, item_ID, transaction_ID) VALUE(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, quantity_bought);
            pstmt.setInt(2, item_ID);
            pstmt.setInt(3, transaction_ID);
            
            if(find_purchase(item_ID, transaction_ID) > 0)
            {
                    pstmt.close();
                    conn.close();
                    merchandise_purchase_ID = find_purchase(item_ID, transaction_ID);
                    add_quantity(quantity_bought, item_ID, transaction_ID);
                    return 1;
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                merchandise_purchase_ID = rs.getInt(1);
            
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
    
    private void add_quantity(int quantity, int item_ID, int transaction_ID) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT quantity_bought FROM Merchandise_Purchases WHERE item_ID = ? AND transaction_ID = ?");
            pstmt.setInt(1, item_ID);
            pstmt.setInt(2, transaction_ID);
            ResultSet rs = pstmt.executeQuery();
            int q = 0;
            
            if(rs.next())
                q = rs.getInt(1);
            
            pstmt = conn.prepareStatement("UPDATE Merchandise_Purchases SET quantity_bought = ? WHERE item_ID = ? AND transaction_ID = ?");
            pstmt.setInt(1, quantity+q);
            pstmt.setInt(2, item_ID);
            pstmt.setInt(3, transaction_ID);
            pstmt.executeUpdate();
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public ArrayList<Integer> get_merchandise_purchase_ID_list()
    {
        return this.merchandise_purchase_ID_list;
    }
    
    public ArrayList<Integer> get_quantity_bought_list()
    {
        return this.quantity_bought_list;
    }
    
    public ArrayList<Integer> get_item_ID_list()
    {
        return this.item_ID_list;
    }
    
    public ArrayList<Integer> get_transaction_ID_list()
    {
        return this.transaction_ID_list;
    }
    
    public void set_quantity_bought(int quantity_bought)
    {
        this.quantity_bought = quantity_bought;
    }
    
    public void set_item_ID(int item_ID)
    {
        this.item_ID = item_ID;
    }
    
    public void set_transaction_ID(int transaction_ID)
    {
        this.transaction_ID = transaction_ID;
    }
    
    public String find_concert_from_merchandise(int merchandise_purchase_ID) throws ClassNotFoundException
    {
        String name = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT c.concert_name FROM Merchandise_Purchases mp JOIN Merchandise m ON mp.item_ID = m.item_ID JOIN Concerts c ON m.concert_ID = c.concert_ID WHERE merchandise_purchase_ID = ?");
            pstmt.setInt(1, merchandise_purchase_ID);
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
    
    public String find_item_name(int merchandise_purchase_ID) throws ClassNotFoundException
    {
        String name_found = "";
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT item_name FROM Merchandise_Purchases mp JOIN Merchandise m ON mp.item_ID = m.item_ID WHERE merchandise_purchase_ID = ?");
            pstmt.setInt(1, merchandise_purchase_ID);
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
    
    public Double purchase_total(int merchandise_purchase_ID) throws ClassNotFoundException
    {
        Double total = 0.0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT mp.quantity_bought*m.price FROM Merchandise_Purchases mp JOIN Merchandise m ON mp.item_ID = m.item_ID WHERE merchandise_purchase_ID = ?");
            pstmt.setInt(1, merchandise_purchase_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                total = rs.getDouble(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return total;
    }
    
    public Timestamp find_purchase_time(int transaction_ID) throws ClassNotFoundException
    {
        Timestamp time = null;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT date_of_transaction FROM Transactions WHERE transaction_ID = ?");
            pstmt.setInt(1, transaction_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                time = rs.getTimestamp(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return time;
    }
        
    private int find_purchase(int item_ID, int transaction_ID) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT merchandise_purchase_ID FROM Merchandise_Purchases WHERE item_ID = ? AND transaction_ID = ?");
            pstmt.setInt(1, item_ID);
            pstmt.setInt(2, transaction_ID);
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
