/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concert_management;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Anja
 */
public class Concerts {
    private int concert_ID;
    private String concert_name;
    private Timestamp transaction_date;
    private Date concert_date;
    private Time start_time;
    private Time end_time;
    private String concert_status;
    private int performer_ID;
    private int venue_ID;
    
    private ArrayList<Integer> concert_ID_list = new ArrayList<>();
    private ArrayList<String> concert_name_list = new ArrayList<>();
    private ArrayList<Timestamp> transaction_date_list = new ArrayList<>();
    private ArrayList<Date> concert_date_list = new ArrayList<>();
    private ArrayList<Time> start_time_list = new ArrayList<>();
    private ArrayList<Time> end_time_list = new ArrayList<>();
    private ArrayList<String> concert_status_list = new ArrayList<>();
    private ArrayList<Integer> performer_ID_list = new ArrayList<>();
    private ArrayList<Integer> venue_ID_list = new ArrayList<>();
    
    public int concert_list() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID, concert_name, transaction_date, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID FROM Concerts");
            ResultSet rs = pstmt.executeQuery();
            
            concert_ID_list.clear();
            concert_name_list.clear();
            transaction_date_list.clear();
            concert_date_list.clear();
            start_time_list.clear();
            end_time_list.clear();
            concert_status_list.clear();
            performer_ID_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                concert_ID = rs.getInt("concert_ID");
                concert_name = rs.getString("concert_name");
                transaction_date = rs.getTimestamp("transaction_date");
                concert_date = rs.getDate("concert_date");
                start_time = rs.getTime("start_time");
                end_time = rs.getTime("end_time");
                concert_status = rs.getString("concert_status");
                performer_ID = rs.getInt("performer_ID");
                venue_ID = rs.getInt("venue_ID");
                
                concert_ID_list.add(concert_ID);
                concert_name_list.add(concert_name);
                transaction_date_list.add(transaction_date);
                concert_date_list.add(concert_date);
                start_time_list.add(start_time);
                end_time_list.add(end_time);
                concert_status_list.add(concert_status);
                performer_ID_list.add(performer_ID);
                venue_ID_list.add(venue_ID);
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
    
    public int completed_concert_list() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID, concert_name, transaction_date, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID FROM Concerts WHERE concert_status = 'COMPLETED'");
            ResultSet rs = pstmt.executeQuery();
            
            concert_ID_list.clear();
            concert_name_list.clear();
            transaction_date_list.clear();
            concert_date_list.clear();
            start_time_list.clear();
            end_time_list.clear();
            concert_status_list.clear();
            performer_ID_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                concert_ID = rs.getInt("concert_ID");
                concert_name = rs.getString("concert_name");
                transaction_date = rs.getTimestamp("transaction_date");
                concert_date = rs.getDate("concert_date");
                start_time = rs.getTime("start_time");
                end_time = rs.getTime("end_time");
                concert_status = rs.getString("concert_status");
                performer_ID = rs.getInt("performer_ID");
                venue_ID = rs.getInt("venue_ID");
                
                concert_ID_list.add(concert_ID);
                concert_name_list.add(concert_name);
                transaction_date_list.add(transaction_date);
                concert_date_list.add(concert_date);
                start_time_list.add(start_time);
                end_time_list.add(end_time);
                concert_status_list.add(concert_status);
                performer_ID_list.add(performer_ID);
                venue_ID_list.add(venue_ID);
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
        
    public int cancelled_concert_list() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID, concert_name, transaction_date, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID FROM Concerts WHERE concert_status = 'CANCELLED'");
            ResultSet rs = pstmt.executeQuery();
            
            concert_ID_list.clear();
            concert_name_list.clear();
            transaction_date_list.clear();
            concert_date_list.clear();
            start_time_list.clear();
            end_time_list.clear();
            concert_status_list.clear();
            performer_ID_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                concert_ID = rs.getInt("concert_ID");
                concert_name = rs.getString("concert_name");
                transaction_date = rs.getTimestamp("transaction_date");
                concert_date = rs.getDate("concert_date");
                start_time = rs.getTime("start_time");
                end_time = rs.getTime("end_time");
                concert_status = rs.getString("concert_status");
                performer_ID = rs.getInt("performer_ID");
                venue_ID = rs.getInt("venue_ID");
                
                concert_ID_list.add(concert_ID);
                concert_name_list.add(concert_name);
                transaction_date_list.add(transaction_date);
                concert_date_list.add(concert_date);
                start_time_list.add(start_time);
                end_time_list.add(end_time);
                concert_status_list.add(concert_status);
                performer_ID_list.add(performer_ID);
                venue_ID_list.add(venue_ID);
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
        
    public int upcoming_concert_list() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID, concert_name, transaction_date, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID FROM Concerts WHERE concert_status = 'UPCOMING'");
            ResultSet rs = pstmt.executeQuery();
            
            concert_ID_list.clear();
            concert_name_list.clear();
            transaction_date_list.clear();
            concert_date_list.clear();
            start_time_list.clear();
            end_time_list.clear();
            concert_status_list.clear();
            performer_ID_list.clear();
            venue_ID_list.clear();
            
            while(rs.next())
            {
                concert_ID = rs.getInt("concert_ID");
                concert_name = rs.getString("concert_name");
                transaction_date = rs.getTimestamp("transaction_date");
                concert_date = rs.getDate("concert_date");
                start_time = rs.getTime("start_time");
                end_time = rs.getTime("end_time");
                concert_status = rs.getString("concert_status");
                performer_ID = rs.getInt("performer_ID");
                venue_ID = rs.getInt("venue_ID");
                
                concert_ID_list.add(concert_ID);
                concert_name_list.add(concert_name);
                transaction_date_list.add(transaction_date);
                concert_date_list.add(concert_date);
                start_time_list.add(start_time);
                end_time_list.add(end_time);
                concert_status_list.add(concert_status);
                performer_ID_list.add(performer_ID);
                venue_ID_list.add(venue_ID);
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
        
    public int add_concert() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Concerts(concert_name, transaction_date, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID) VALUE(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, concert_name);
            pstmt.setTimestamp(2, transaction_date);
            pstmt.setDate(3, concert_date);
            pstmt.setTime(4, start_time);
            pstmt.setTime(5, end_time);
            pstmt.setString(6, concert_status);
            pstmt.setInt(7, performer_ID);
            pstmt.setInt(8, venue_ID);
            
            if(find_concert(concert_name) > 0)
            {
                concert_ID = find_concert(concert_name);
                pstmt.close();
                conn.close();
                return 2; // already a concert with this name
            }
            
            if(concert_at_time(concert_date, start_time, end_time, venue_ID))
            {
                pstmt.close();
                conn.close();
                return 3; // already a concert around this time
            }
            
            if(!valid_time(start_time, end_time))
            {
                pstmt.close();
                conn.close();
                return 4; // invalid time slot
            }
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys(); 
            
            if(rs.next())
                concert_ID = rs.getInt(1);
            
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
   
    public ArrayList<Integer> get_concert_ID_list()
    {
        return this.concert_ID_list;
    }
    
    public ArrayList<String> get_concert_name_list()
    {
        return this.concert_name_list;
    }
    
    public ArrayList<Timestamp> get_transaction_date_list()
    {
        return this.transaction_date_list;
    }
    
    public ArrayList<Date> get_concert_date_list()
    {
        return this.concert_date_list;
    }
    
    public ArrayList<Time> get_start_time_list()
    {
        return this.start_time_list;
    }
    
    public ArrayList<Time> get_end_time_list()
    {
        return this.end_time_list;
    }
    
    public ArrayList<String> get_concert_status_list()
    {
        return this.concert_status_list;
    }
    
    public ArrayList<Integer> get_performer_ID_list()
    {
        return this.performer_ID_list;
    }
    
    public ArrayList<Integer> get_venue_ID_list()
    {
        return this.venue_ID_list;
    }
    
    public void set_concert_name(String concert_name)
    {
        this.concert_name = concert_name;
    }
    
    public void set_transaction_date(Timestamp transaction_date)
    {
        this.transaction_date = transaction_date;
    }
    
    public void set_concert_date(Date concert_date)
    {
        this.concert_date = concert_date;
    }
    
    public void set_start_time(Time start_time)
    {
        this.start_time = start_time;
    }
    
    public void set_end_time(Time end_time)
    {
        this.end_time = end_time;
    }
    
    public void set_concert_status(String concert_status)
    {
        this.concert_status = concert_status;
    }
    
    public void set_performer_ID(int performer_ID)
    {
        this.performer_ID = performer_ID;
    }
    
    public void set_venue_ID(int venue_ID)
    {
        this.venue_ID = venue_ID;
    }
        
    private boolean valid_time(Time start_time, Time end_time)
    {
        if(end_time.before(start_time) || end_time.equals(start_time))
            return false;
        
        return true;
    }

    private boolean concert_at_time(Date concert_date, Time start_time, Time end_time, int venue_ID) throws ClassNotFoundException
    {        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Concerts WHERE concert_date = ? AND NOT (? <= start_time OR ? >= end_time) AND venue_ID = ?");
            pstmt.setDate(1, concert_date);
            pstmt.setTime(2, end_time);
            pstmt.setTime(3, start_time);
            pstmt.setInt(4, venue_ID);
            
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
    
    public int ticket_types_sold(int ticket_type_ID, Date date) throws ClassNotFoundException
    {
        int sold = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS tickets_sold FROM tickets t JOIN transactions tr ON t.transaction_ID = tr.transaction_ID JOIN ticket_types tt ON t.ticket_type_ID = tt.ticket_type_ID WHERE DATE(tr.date_of_transaction) = ? AND t.ticket_type_ID = ?");
            pstmt.setDate(1, date);
            pstmt.setInt(2, ticket_type_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                sold = rs.getInt(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return sold;
    }
    
    // SELECT SUM(quantity_bought) AS merchandise_sold FROM merchandise_purchases mp JOIN transactions tr ON mp.transaction_ID = tr.transaction_ID WHERE DATE(tr.date_of_transaction) = ? AND mp.item_ID = ?;
    
    public int merchandise_sold(int item_ID, Date date) throws ClassNotFoundException
    {
        int sold = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(quantity_bought) AS merchandise_sold FROM merchandise m JOIN merchandise_purchases mp ON m.item_ID = mp.item_ID JOIN transactions tr ON mp.transaction_ID = tr.transaction_ID WHERE DATE(tr.date_of_transaction) = ? AND mp.item_ID = ?");
            pstmt.setDate(1, date);
            pstmt.setInt(2, item_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                sold = rs.getInt(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return sold;
    }
    
    public double find_merchandise_cut(int venue_ID) throws ClassNotFoundException
    {
        double cut = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT merchandise_cut_percentage FROM Venues WHERE venue_ID = ?");
            pstmt.setInt(1, venue_ID);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
                cut = rs.getDouble(1);
            
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return cut;
    }
    
    public void cancel_concert(int concert_ID) throws ClassNotFoundException
    {
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE concerts SET concert_status = 'CANCELLED' WHERE concert_ID = ?");
            pstmt.setInt(1, concert_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private int find_concert(String concert_name) throws ClassNotFoundException
    {
        int ID_found = 0;
        
        try
        {   
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID FROM Concerts WHERE concert_name = ?");
            pstmt.setString(1, concert_name);
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
