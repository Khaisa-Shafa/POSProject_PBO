/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posproject;


/**
 *
 * @author VIVOBOOK
 */
public class session {
    
    private static String username;
    private static boolean isAdmin;
    
    public static void startSession(String username, boolean isAdmin) {
        session.username = username;
        session.isAdmin = isAdmin;
    }
    
    public static void endSession() {
        session.username = null;
        session.isAdmin = false;
    }
    
    public static boolean isLoggedIn() {
        return username != null;
    }
    
    public static String getUsername() {
        return username;
    }
    
    public static boolean isAdmin() {
        return isAdmin;
    }
    
    public static void main(String args[]) {
        System.out.println("session");
    }
}
    

