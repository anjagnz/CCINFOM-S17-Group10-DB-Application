/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee_management;

/**
 *
 * @author Anja
 */

import java.util.ArrayList;
import java.sql.*;

public class Employees {
    
    private int employee_ID;
    private double weekly_salary;
    private Date hire_date;
    private int user_ID;
    private int role_ID;
    
    public int add_employee() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s17_group10_concertdb", "root", "password");
            System.out.println("Connection successful.");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Employees(weekly_salary, hire_date, user_ID, role_ID) VALUE(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, weekly_salary);
            pstmt.setDate(2, hire_date);
            pstmt.setInt(3, user_ID);
            pstmt.setInt(4, role_ID);

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if(rs.next())
                employee_ID = rs.getInt(1);
            
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
}
