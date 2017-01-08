/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author lakmal
 */
public class InputFile {
    public static String chooseFile() {
        JFileChooser fc = new JFileChooser();
        int ret = fc.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getAbsolutePath();

            try {

                String line = null;

                FileReader reader = new FileReader(file);
                BufferedReader bfreader = new BufferedReader(reader);

                while ((line = bfreader.readLine()) != null) {
                    String read = line.toLowerCase();
                    Node newnode = new Node(read);
                    GUI.bst.insert(newnode);
                }
                System.out.println("ok");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File not found!");
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cannot read from file!");
                e.printStackTrace();
            }

            return filename;
        } else {
            return null;
        }
    }

    
}
