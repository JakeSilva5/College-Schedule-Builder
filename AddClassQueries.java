/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author jakesilva
 */
public class AddClassQueries {
    private static Connection connection;

    // Constructor to initiate connection
    public AddClassQueries() {
        connection = DBConnection.getConnection();
    }

    // Method to add a class
    public void addClass(String courseCode, String semester, int seats) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO classes (courseCode, semester, seats) VALUES (?, ?, ?)");
            ps.setString(1, courseCode);
            ps.setString(2, semester);
            ps.setInt(3, seats);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

