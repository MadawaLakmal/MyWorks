/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package dictionary;
//import static dictionary.GUI.setTheme;
import java.awt.Insets;
import javax.swing.JFrame;

/**
 *
 * @author lakmal
 */
public class Dictionary {    
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createGUI() {
        //Create and set up the window.
        GUI frame = new GUI();
        frame.setTitle("WELLCOME TO MY DICTIONARY !!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(375, 470);
        frame.setVisible(true);
        
    }
    //set theme to the  gui
      public static void setTheme() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }//System.out.println(info.getName());//Nimbus
            }
        } catch (Exception e) {
			e.printStackTrace();
            
        } //set themes to the gui(default themes "Nimbus","Windows Classic","Metal")
    }
    
    public static void main(String[] args) {
        setTheme();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
    
}
