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
import static posproject.DBConnector.connection;

public class ActivityLog_Component {
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
    public static void loadActivityFromDB(LocalDateTime mulai, LocalDateTime selesai)
    {
//        daftarActivity= new ArrayList<ActivityLog_Component>();
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
                LocalDateTime rawformat = rs.getTimestamp("waktu").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String waktu = rawformat.format(formatter); 
                String aksi = rs.getString("aksi");
//            daftarActivity.add(activity);
            }
            
        } catch (Exception ex){
            System.out.print(ex);
        }
    }
       
}
