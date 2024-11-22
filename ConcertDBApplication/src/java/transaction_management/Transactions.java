/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transaction_management;

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Transactions {
    private int transaction_ID;
    private Timestamp date_of_transaction;
    int customer_ID;
    
    public int add_transaction() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Transactions(date_of_transaction, customer_ID) VALUE(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setTimestamp(1, date_of_transaction);
            pstmt.setInt(2, customer_ID);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                transaction_ID = rs.getInt(1);
            
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
    
    public int get_transaction_ID()
    {
        return this.transaction_ID;
    }
    
    public Timestamp get_date_of_transaction()
    {
        return this.date_of_transaction;
    }
    
    public int get_customer_ID()
    {
        return this.customer_ID;
    }
    
    public void set_date_of_transaction(Timestamp date_of_transaction)
    {
        this.date_of_transaction = date_of_transaction;
    }
    
    public void set_customer_ID(int customer_ID)
    {
        this.customer_ID = customer_ID;
    }
    
}
