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
import java.util.ArrayList;

public class ActivityLog_Component {
    private String user;
    private LocalDateTime waktu;
    private String aksi;
    
    public ActivityLog_Component(String user, LocalDateTime waktu, String aksi) {
        this.user = user;
        this.waktu = waktu;
        this.aksi = aksi;
    }

    static ArrayList<ActivityLog_Component> daftarActivity;
    public String getUser() {
        return user;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public String getAksi() {
        return aksi;
    }
}
