/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


final class GUI extends JFrame implements ActionListener {

    JButton ADD, MEANING, SIM, DEL, REF, CLEAR;
    JTextArea textarea1;JTextArea textarea2;
    JLabel label1;JLabel label2;
    String  getword, word, result;String arrwords = "";
    static BSTree bst = new BSTree();//constuct a nw binary tree

    public GUI() {
        addComponentsToPane(this);//constructor
    }

    public void addComponentsToPane(Container pane) {
        pane.setLayout(null);
        pane.setBackground(Color.red);

        textarea1 = new JTextArea();
        textarea2 = new JTextArea();
        
        ADD = new JButton("Add word");
        MEANING = new JButton("Meaning");
        SIM = new JButton("Similar words");
        DEL = new JButton("Delete");
        REF = new JButton("Add reference file name");
        CLEAR = new JButton("Clear");

        label1 = new JLabel();
        label2 = new JLabel();

        pane.add(textarea1);
        pane.add(textarea2);
        
        pane.add(ADD);
        pane.add(MEANING);
        pane.add(SIM);
        pane.add(DEL);
        pane.add(REF);
        pane.add(CLEAR);
        
        pane.add(label1);
        pane.add(label2);
        
        textarea1.setLineWrap(true);
        textarea1.setWrapStyleWord(true);
        textarea1.setEditable(true);
        textarea2.setLineWrap(true);
        textarea2.setWrapStyleWord(true);
        textarea2.setEditable(false);

        Insets insets = pane.getInsets();

        textarea1.setBounds(10 + insets.left, 30 + insets.top, 345, 60);
        textarea2.setBounds(10 + insets.left, 240 + insets.top, 345, 150);
        
        ADD.setBounds(10 + insets.left, 110 + insets.top, 105, 30);
        MEANING.setBounds(130 + insets.left, 110 + insets.top, 105, 30);
        SIM.setBounds(249 + insets.left, 110 + insets.top, 105, 30);
        DEL.setBounds(10 + insets.left, 145 + insets.top, 105, 30);
        REF.setBounds(130 + insets.left, 145 + insets.top, 224, 30);
        CLEAR.setBounds(249 + insets.left, 190 + insets.top, 105, 30);
        
        label1.setBounds(15 + insets.left, 10 + insets.top, 230, 20);
        label2.setBounds(15 + insets.left, 195 + insets.top, 230, 60);
        label1.setText("INPUT:");
        label2.setText("OUTPUT:");
        
        ADD.addActionListener(this);
        MEANING.addActionListener(this);
        SIM.addActionListener(this);
        DEL.addActionListener(this);
        REF.addActionListener(this);
        CLEAR.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
// add word in to the dictionary
        if (e.getSource().equals(ADD)) {
           textarea2.setText("");
           getword =textarea1.getText();
           Node tempnode = new Node(getword);
           String[] arr = getword.split("=");
           word = tempnode.getword();
           if(getword.equals("") && arr.length==1){
                JOptionPane.showMessageDialog(null,"PLEASE ENTER A WORD !!!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
           }
           else if(!getword.equals("") && arr.length==1){
                label2.setText("");
                JOptionPane.showMessageDialog(null,"PLEASE ENTER ' WORD = MEANING ' !!!","ERROR",JOptionPane.ERROR_MESSAGE);
           }
           else { 
               if(bst.search(word).equals("Word is not found!!!!!!")){
                //String[] arr = getword.split("=");
                word = getword.toLowerCase();
                Node newnode = new Node(word);
                bst.insert(newnode);
                textarea2.append("WORD '"+arr[0].trim().toUpperCase()+"' ADDED TO THE DICTIONARY!!!");
                ArrayList<String> wrd1 = new ArrayList<>();
            try {
                //ArrayList<String> wrd2 = new ArrayList<>();
                BSTree.writefile(bst.iotraversal(bst.getcurrentroot(), wrd1),"C:\\Users\\lakmal\\Desktop\\Dictionary\\write.txt");
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                                
               
               
               }
               else{
                   label2.setText("");
                   JOptionPane.showMessageDialog(null,"WORD '"+word.toUpperCase()+"' ALREADY IN THE DICTIONARY!!!");
                   
               }}
        
           
               
    }
// returns the meanigs  
        if (e.getSource().equals(MEANING)) {
            textarea2.setText("");
            getword=textarea1.getText();
            word = getword.toLowerCase();
            result = bst.search(word);
            if(result.equals("Word is not found!!!!!!") && getword.equals("")){
                label2.setText("");
                JOptionPane.showMessageDialog(null,"PLEASE ENTER A WORD TO GET MEANING !!!!!!","ERROR",JOptionPane.ERROR_MESSAGE); 
                
            }
            else if(result.equals("Word is not found!!!!!!")){
                label2.setText("");
                JOptionPane.showMessageDialog(null,"THE WORD '"+word.toUpperCase()+"' NOT IN THE DICTIONARY !!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               label2.setText("MEANING:");
               textarea2.append(" "+result);
            }
        }
// returns the similar words 
        if (e.getSource().equals(SIM)) {
            textarea2.setText("");
            getword = textarea1.getText();
            word = getword.toLowerCase().trim();
            String[] arr = getword.split("=");
            String search = bst.search(word);
            Node root = bst.getcurrentroot();
            ArrayList<String> out = new ArrayList<>();
            out = bst.similarwords(bst.getcurrentroot(),search,out);
            if(out.isEmpty()){
                JOptionPane.showMessageDialog(null,"THE WORD '"+arr[0].toUpperCase()+"' NOT IN THE DICTIONARY !!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else if(out.size()==2){
                label2.setText("SIMILAR WORDS:");
                textarea2.setText("THERE IS NO SIMILAR WORDS FOR WORD '"+getword+"' ");
            }
            else{
                label2.setText("SIMILAR WORDS:");
                for(String words:out){
                    if(!words.equals(word)){
                      textarea2.append(words+"\n");
                  }
              }  
        }
  }
 //deleting words from dictionary
        if (e.getSource().equals(DEL)) {
            textarea2.setText("");
            getword = textarea1.getText();
            word = getword.toLowerCase();
            if(bst.getcurrentroot()== null){
                
                JOptionPane.showMessageDialog(null,"DICTIONARY IS EMPTY !!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                    if(bst.delete(word)==true){
                    JOptionPane.showMessageDialog(null,"THE WORD '"+word.toUpperCase()+"' WAS DELETED !!!!!!");
                     ArrayList<String> wrd1 = new ArrayList<>();
            try {
                //ArrayList<String> wrd2 = new ArrayList<>();
                BSTree.writefile(bst.iotraversal(bst.getcurrentroot(), wrd1),"C:\\Users\\lakmal\\Desktop\\Dictionary\\write.txt");
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                                              }
                    else{
                    JOptionPane.showMessageDialog(null,"THE WORD '"+word.toUpperCase()+"' IS NOT FOUND IN THE DICTIONARY !!!!!");
                        }
            }
        }

//select reference file
        if (e.getSource().equals(REF)) {
            InputFile.chooseFile();
        }
 //clear text areas       
        if (e.getSource().equals(CLEAR)) {
            textarea1.setText("");
            textarea2.setText("");
        }
    }

   //set theme to the GUI
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

    

}
