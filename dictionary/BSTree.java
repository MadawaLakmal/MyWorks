/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author lakmal
 */
public class BSTree {
    private Node root;
    String temp = "";String simwords = "";String arrwords="";
    
    /**
     * Construct the tree.
     */
    BSTree(){
        this.root = null;
    }
    public String getroot(){
        if (this.root==null){
            return ("tree is empty");
        }
        else{
            return (root.getword());
        }
    }
    public Node getcurrentroot(){
        return root;
    }
    /*
    insert a node in to binary search tree
    */
    public void insert(Node newnode){   
        if(this.root==null){ //check :tree has only root node
            root = newnode;
        }
        else{
            Node currentnode = root;
            Node parentnode;
            int flag = 0;
            while(flag != 1){           //set new node as a leftchild
                parentnode = currentnode;
                if((currentnode.getword()).compareTo(newnode.getword())>0){
                    currentnode = currentnode.getleftchild();
                    if(currentnode==null){
                        parentnode.setleftchild(newnode);
                        flag+=1;                    }
                }
                else if((currentnode.getword()).compareTo(newnode.getword())<0){
                    currentnode = currentnode.getrightchild();
                    if(currentnode==null){           //set new node as a rightchild
                        parentnode.setrightchild(newnode);
                        flag+=1;
                    }
                }
                else{
                    flag+=1;
                    
                }
            }
        }
    }
    /*
     @a given string search from the tree
    */
    public String search(String search){
        Node currentnode = root;
        while(currentnode != null){
            if(currentnode.getword().compareTo(search.trim())==0){
                return (currentnode.getmeaning());
            }
            else if(currentnode.getword().compareTo(search.trim())>0){
                currentnode = currentnode.getleftchild();
            }
            else{
                currentnode = currentnode.getrightchild();
            }
        }return("Word is not found!!!!!!");
    }

    /**
     *
     * @param delword
     * @return
     */
    public boolean delete(String delword){
        Node currentnode = root;
        Node parentnode = root;
        boolean isleftchild = false;
        while(currentnode.getword().compareTo(delword.trim())!=0){
            parentnode = currentnode;
            if(currentnode.getword().compareTo(delword.trim())>0){
                isleftchild = true;
                currentnode = currentnode.getleftchild();
            }
            else{
                isleftchild = false;
                currentnode = currentnode.getrightchild();
            }
            if (currentnode == null){
                return false;
            }
        }
        //case1:node to be deleted has no children
        if(currentnode.getleftchild()==null && currentnode.getrightchild()==null){
            if(currentnode == root){
                root = null;
            }
            if(isleftchild == true){
                parentnode.setleftchild(null);
            }
            else{
                parentnode.setrightchild(null);
            }
        }
        //case2:node to be deleted have onli onechild
        else if(currentnode.getrightchild() == null){
            if(currentnode==root){
                root = currentnode.getleftchild();
            }
            else if(isleftchild){
                parentnode.setleftchild(currentnode.getleftchild());
            }
            else{
                parentnode.setrightchild(currentnode.getleftchild());
            }
        }
        else if(currentnode.getleftchild() == null){
			if(currentnode==root){
				root = currentnode.getrightchild();
			}else if(isleftchild){
				parentnode.setleftchild(currentnode.getrightchild());
			}else{
				parentnode.setrightchild(currentnode.getrightchild());
			} 
                }
         
        //case3:node to be deleted has two children   
        else if(currentnode.getleftchild()!=null && currentnode.getrightchild()!=null){
            Node successor = getSuccessor(currentnode);
            if(currentnode==root){
                root = successor;
            }
            else if(isleftchild){
                parentnode.setleftchild(successor);
            }
            else{
                parentnode.setrightchild(successor);
            }
            successor.setleftchild(currentnode.getleftchild());
        }
        return true;
    }
    public Node getSuccessor(Node delenode){
        Node successor = null;
        Node successorparent = null;
        Node current = delenode.getrightchild();
        while(current!=null){
            successorparent = successor;
            successor = current;
            current = current.getleftchild();
        }
        if(successor!=delenode.getrightchild()){
            successorparent.setleftchild(successor.getrightchild());
            successor.setrightchild(delenode.getrightchild());
        }
        return successor;
    }
    
    /*iotraversal returns the all data which in binary tree*/
    public ArrayList<String> iotraversal(Node root,ArrayList<String> arrayList) {
        if(root != null) {
            iotraversal(root.getleftchild(),arrayList);
            arrayList.add(root.getword()+" = "+root.getmeaning());
            iotraversal(root.getrightchild(),arrayList);
        }
        return arrayList;
    }
    
    //returns simillarwords
    public ArrayList<String> similarwords(Node root,String mean,ArrayList<String>  newarray){
        if(root!=null){
            similarwords(root.getleftchild(),mean,newarray);
            if(root.getmeaning().compareTo(mean.trim())==0){
                newarray.add(root.getword());
            }
            similarwords(root.getrightchild(),mean,newarray);
        } return newarray;
    }
    
    public static void writefile(ArrayList<String> wordlist,String path) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for(String in:wordlist){
                writer.write(in);
                writer.newLine();
                
            }
        }
    }
 
 
 
    
}
