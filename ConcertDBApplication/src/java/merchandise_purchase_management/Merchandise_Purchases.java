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
