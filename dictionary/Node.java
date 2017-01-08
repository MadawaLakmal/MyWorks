/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author lakmal
 */
public class Node {
    private final String Key;
    public Node leftchild;
    public Node rightchild;
    String[] arr;
    
    Node(String key){
        this.Key = key.trim();
        this.arr = key.split("=");
        leftchild = null;
        rightchild = null;
 }  
    public String getkey(){
        return (Key);
    }
    public String getword(){
         return (arr[0].trim());
    }
    public String getmeaning(){
        return (arr[1].trim());
    }
    public void setrightchild(Node rchild){
        this.rightchild = rchild;
         }
    public void setleftchild(Node lchild){
        this.leftchild = lchild;
    }
    public Node getrightchild(){
        return(rightchild);
    }
    public Node getleftchild(){
        return(leftchild);
    }

}