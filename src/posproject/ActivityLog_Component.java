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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static posproject.DBConnector.connection;

public class ActivityLog_Component {
    private String id;
    private String username;
    private String waktu;
    private String aksi;

    // Constructor
    public ActivityLog_Component(String id, String username, String waktu, String aksi) {
        this.id = id;
        this.username = username;
        this.waktu = waktu;
        this.aksi = aksi;
    }
    public String getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getAksi() {
        return aksi;
    }
    
    public static void insertActivityToDB(String user, String aksi)
    {
        System.out.println("Masuk ke insertActivityToDB");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnector.initDBConnection(); 
            
            LocalDateTime waktu = LocalDateTime.now();  
            Timestamp waktuTimestamp = Timestamp.valueOf(waktu);
            
            String sql_in = "INSERT INTO activity_log (username, waktu, aksi) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql_in);
            stm.setString(1, user);
            stm.setTimestamp(2, waktuTimestamp);
            stm.setString(3, aksi);
            int rowsInserted = stm.executeUpdate();       
        } catch (Exception ex)
        {
            
        }
    }
    public static List<ActivityLog_Component> loadActivityFromDB(Date mulai, Date selesai) {
    List<ActivityLog_Component> activityList = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DBConnector.initDBConnection();

        Timestamp mulaitime = new Timestamp(mulai.getTime());
        Timestamp selesaitime = new Timestamp(selesai.getTime());

        String sql = "SELECT * FROM activity_log WHERE waktu >= ? AND waktu <= ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setTimestamp(1, mulaitime);
        stm.setTimestamp(2, selesaitime);

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            String id = Integer.toString(rs.getInt("id"));
            String username = rs.getString("username");
            LocalDateTime rawformat = rs.getTimestamp("waktu").toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String waktu = rawformat.format(formatter);
            String aksi = rs.getString("aksi");

            ActivityLog_Component activity = new ActivityLog_Component(id, username, waktu, aksi);
            activityList.add(activity);
        }
    } catch (Exception ex) {
        System.out.print(ex);
    }

    return activityList;
}
}