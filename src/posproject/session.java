/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author VIVOBOOK
 */
public class session {
   
    private Connection conn;
    
    public session() throws SQLException {
        // Membuat koneksi ke database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
    }

    public void setAttribute(String id, String username, String kasir) throws SQLException {
        String query = "INSERT INTO login (id, username, admin) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE username = ?, admin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, username);
            stmt.setString(3, kasir);
            stmt.setString(4, username);
            stmt.setString(5, kasir);
            
            stmt.executeUpdate();
        }
    }

    public String getAttribute(String id, String username) throws SQLException {
        String query = "SELECT attribute_value FROM login WHERE id = ? AND username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("kasir");
                }
            }
        }
        return null;
    }
    
        public static void main(String[] args) {
        
        }
}

