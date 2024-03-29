/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;

/**
 *
 * @author USER
 */
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import static posproject.DBConnector.connection;

public class ActivityLog_Component {
    private String username;
    private LocalDateTime waktu;
    private String aksi;
    
    public ActivityLog_Component(String username, LocalDateTime waktu, String aksi) {
        this.username = username;
        this.waktu = waktu;
        this.aksi = aksi;
    }

    static ArrayList<ActivityLog_Component> daftarActivity;
    public String getUser() {
        return username;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public String getAksi() {
        return aksi;
    }
    
    public static void insertActivityToDB(ActivityLog_Component activity)
    {
        daftarActivity= new ArrayList<ActivityLog_Component>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();
            //gimana semua yg sebaris terambil, gimana ngambil dll.
            
            String sql= "SELECT * FROM activity_log";
          
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String username = rs.getString("username");
                LocalDateTime waktu = rs.getTimestamp("waktu").toLocalDateTime();
                String aksi = rs.getString("aksi");
                
                String sql_in = "INSERT id FROM activity_log (username, waktu, aksi) VALUES (?, ?, ?)";
            }
        } catch (Exception ex)
        {
            
        }
    }
    public static void loadActivityFromDB(LocalDateTime mulai, LocalDateTime selesai)
    {
        daftarActivity= new ArrayList<ActivityLog_Component>();
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection();
            
            String sql = "SELECT * FROM activity_log WHERE waktu >= ? AND waktu <= ?";
          
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, Timestamp.valueOf(mulai));
            stm.setTimestamp(2, Timestamp.valueOf(selesai));
        
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                String username = rs.getString("username");
                LocalDateTime waktu = rs.getTimestamp("waktu").toLocalDateTime();
                String aksi = rs.getString("aksi");

                ActivityLog_Component activity = new ActivityLog_Component(username, waktu, aksi);
            daftarActivity.add(activity);
        }
        } catch (Exception ex)
        {
            System.out.print(ex);
        }
    }
}
