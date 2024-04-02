/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;


import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static posproject.DBConnector.connection;
/**
 *
 * @author USER
 */
public class Transaksi_Component {
    
    
    public static void addBarangtoTransaksi(int kode, String nama, int harga, int jumlah, int total) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();
            //idtransaksi
            String id = "SELECT * FROM Transaksi ORDER BY id_transaksi DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(id);
            ResultSet resultSet = statement.executeQuery();

            int id_sekarang = 0;
            if (resultSet.next()) {
            id_sekarang = resultSet.getInt("id_transaksi");
            id_sekarang++;
            }
            
            String query = "INSERT INTO transakasibarang (id_transaksi, kode, harga, jumlah, totalbarang) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id_sekarang);
            stm.setInt(2, kode);
            stm.setString(3, nama);
            stm.setInt(4, harga);
            stm.setInt(5, jumlah);
            stm.setInt(6, total);
            stm.executeUpdate();
            stm.close();
            
        } catch (Exception ex) {
        }
    }
        
    public static void InsertTransaksitoDB(int total) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();
            
            //idtransaksi
            String idtrans = "SELECT * FROM Transaksi ORDER BY id_transaksi DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(idtrans);
            ResultSet resultSet = statement.executeQuery();

            int id_sekarang1 = 0;
            if (resultSet.next()) {
            id_sekarang1 = resultSet.getInt("id_transaksi");
            id_sekarang1++;
            };
            

            String query = "INSERT INTO transakasibarang (id_transaksi, total) VALUES (?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id_sekarang1);
            stm.setInt(2, total);
            stm.executeUpdate();
            stm.close();           
            
        } catch (Exception ex) {
        }
    }
}
