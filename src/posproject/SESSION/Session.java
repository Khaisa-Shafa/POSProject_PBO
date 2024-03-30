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
    
    
    private  static String nama;
    private  static String id;
    private  static String level;
    
    public static String get_nama() {
        return nama;
    }
    public static void set_nama(String nama) {
        Session.nama = nama;
    }
    
    public static String get_id() {
        return id;
    }
    public static void set_id(String id) {
        Session.id = id;
    }
    
    public static String get_level() {
        return level;
    }
    public static void set_level(String level) {
        Session.level = level;
    }
    
    public static void main(String args[]) {
        System.out.println("session");
    }

}
