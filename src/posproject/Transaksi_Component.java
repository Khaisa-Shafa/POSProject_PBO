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
    private int id_transaksi;
    private String total;
    private String waktu;

    // Constructor
    public Transaksi_Component(int id_transaksi, String total, String waktu) {
        this.id_transaksi = id_transaksi;
        this.total = total;
        this.waktu = waktu;
    }
    public int getId() {
        return id_transaksi;
    }
    
    public String getTotal() {
        return total;
    }

    public String getWaktu() {
        return waktu;
    }
    
    public static void addBarangtoTransaksi(int kode, String nama, int harga, int jumlah, int total) {
        System.out.println("Masuk ke addBarang");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();

            int id_sekarang = 0;
            
            String query = "INSERT INTO transaksi_barang (id_transaksi, kode, harga, jumlah, totalbarang) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id_sekarang);
            stm.setInt(2, kode);
            stm.setString(3, nama);
            stm.setInt(4, harga);
            stm.setInt(5, jumlah);
            stm.setInt(6, total);
            stm.executeUpdate();
            stm.close();
            System.out.println("Berhasilbarang");
        } catch (Exception ex) {
        }
    }
        
    public static void InsertTransaksitoDB(int total) {
        System.out.println(total);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();

            LocalDateTime waktu = LocalDateTime.now();  
            Timestamp waktuTimestamp = Timestamp.valueOf(waktu);
            
            String query = "INSERT INTO transaksi (total, waktu) VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, total);
            stm.setTimestamp(2,waktuTimestamp);
            int rowsInserted = stm.executeUpdate();         
            System.out.println("Berhasil transaksi");
        } catch (Exception ex) {
        }
    }
    
    public static List<Transaksi_Component> loadTransaksiFromDB(Date mulai, Date selesai) {
    List<Transaksi_Component> transaksiList = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DBConnector.initDBConnection();

        Timestamp mulaitime = new Timestamp(mulai.getTime());
        Timestamp selesaitime = new Timestamp(selesai.getTime());

        String sql = "SELECT * FROM transaksi WHERE waktu >= ? AND waktu <= ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setTimestamp(1, mulaitime);
        stm.setTimestamp(2, selesaitime);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id_transaksi = rs.getInt("id_transaksi");
            String total = Integer.toString(rs.getInt("total"));
            LocalDateTime rawformat = rs.getTimestamp("waktu").toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String waktu = rawformat.format(formatter);

            Transaksi_Component transaksi = new Transaksi_Component(id_transaksi, total, waktu);
            transaksiList.add(transaksi);
        }
    } catch (Exception ex) {
        System.out.print(ex);
    }

    return (transaksiList);}
}
