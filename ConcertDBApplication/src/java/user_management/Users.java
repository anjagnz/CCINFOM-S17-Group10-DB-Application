package user_management;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Anja
 */

import java.util.*;
import java.sql.*;

public class Users {
    
    private int user_id;
    private String first_name;
    private String last_name;
    private String user_email;
    private String user_password;
    
    public ArrayList<Integer> user_id_list = new ArrayList<>();
    public ArrayList<String> first_name_list = new ArrayList<>();
    public ArrayList<String> last_name_list = new ArrayList<>();
    public ArrayList<String> user_email_list = new ArrayList<>();
    public ArrayList<String> password_list = new ArrayList<>();
    
    public int sign_up() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users(first_name, last_name, user_email, user_password) VALUE(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, user_email);
            pstmt.setString(4, user_password);
            
            if(user_exists(user_email))
            {
                    pstmt.close();
                    conn.close();
                    return 2;
            }
            
                    
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                user_id = rs.getInt(1);
            
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
    
    public int log_in(String user_email, String user_password) throws ClassNotFoundException
    {
        try
        {
            if(!user_exists(user_email))
                return 2; // user not found
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT user_id FROM Users WHERE user_email = ? AND user_password = ?");
            pstmt.setString(1, user_email);
            pstmt.setString(2, user_password);
            ResultSet rs = pstmt.executeQuery();
            
            if(!rs.next())
            {
                rs.close();
                pstmt.close();
                conn.close();
                return 3; // wrong password
            }
            
            user_id = rs.getInt(1);
            
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            return 0; // unknown error
        }
    }
    
    private boolean user_exists(String user_email) throws ClassNotFoundException
    {
        boolean user_exists = false;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE user_email = ?");
            pstmt.setString(1, user_email);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next() && rs.getInt(1) > 0)
                user_exists = true;
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return user_exists;
    }
    
    public int get_user_id() throws ClassNotFoundException
    {
        return this.user_id;
    }
    
    public Users get_user(int user_id) throws ClassNotFoundException
    {
        Users user = new Users();
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT first_name, last_name, user_email, user_password FROM Users WHERE user_id = ?");
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                user.set_user_id(user_id);
                user.set_first_name(rs.getString(1));
                user.set_last_name(rs.getString(2));
                user.set_user_email(rs.getString(3));
                user.set_user_password(rs.getString(4));
            }
            else
            {
                rs.close();
                pstmt.close();
                conn.close();
                return null;
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error in get_user: " + e.getMessage());
            return null;
        }
        return user;
    }
    
    public boolean is_employee(int user_id) throws ClassNotFoundException
    {     
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Employees WHERE user_id = ?");
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            
            if(!rs.next())
            {
                rs.close();
                pstmt.close();
                conn.close();
                return false;
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error in get_user: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public String get_first_name()
    {
        return this.first_name;
    }
    
    public String get_last_name()
    {
        return this.last_name;
    }
    
    public String get_user_email()
    {
        return this.user_email;
    }
    
    public String get_user_password()
    {
        return this.user_password;
    }
    
    public void set_user_id(int user_id)
    {
        this.user_id = user_id;
    }
    
    public void set_first_name(String first_name)
    {
        this.first_name = first_name;
    }
    
    public void set_last_name(String last_name)
    {
        this.last_name = last_name;
    }
    
    public void set_user_email(String user_email)
    {
        this.user_email = user_email;
    }
    
    public void set_user_password(String user_password)
    {
        this.user_password = user_password;
    }
    
    public static void main (String args[])
    {
    
    }
}
