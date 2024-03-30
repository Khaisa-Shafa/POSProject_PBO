/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject.SESSION;

/**
 *
 * @author VIVOBOOK
 */
public class Session {
    private  String nama;
    private  String id;
    private  String level;

    public Session(String nama, String id, String level) {
        this.nama = nama;
        this.id = id;
        this.level = level;
    }
    
    public String get_nama() {
        return nama;
    }
    public void set_nama(String nama) {
        this.nama = nama;
    }
    
    public String get_id() {
        return id;
    }
    public void set_id(String id) {
        this.id = id;
    }
    
    public String get_level() {
        return level;
    }
    public void set_lvl(String level) {
        this.level = level;
    }
    
    public void main(String args[]) {
        System.out.println("session");
    }

}
