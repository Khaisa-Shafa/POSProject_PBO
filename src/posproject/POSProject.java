/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package posproject;
import posproject.SESSION.Session;
/**
 *
 * @author VIVOBOOK
 */
public class POSProject {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      if (Session.get_id()== null) {
        LoginPage.showLoginPage();
      }
      else if (Session.get_id().equals(1)) {
          AdminFrame admin = new AdminFrame();
          admin.setVisible(true);
      }
      else {
      POSFrame frame = new POSFrame();
      frame.setVisible(true);
    }
    }
}
