/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import static dictionary.GUI.setTheme;
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
        frame.setTitle("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(375, 470);
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
    //writing to a file all words in the tree
    
    public static void main(String[] args) {
        setTheme();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
    
}
