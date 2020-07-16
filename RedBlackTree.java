/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author jaiconakpil
 */

import java.util.*;
import java.io.*;

public class RedBlackTree {
        // INDICATE COLOR OF NODE
        private final int red = -1;
        private final int black = 1;
        
        private final Node nil = new Node(-1);
        private Node root = nil;
        
        // INSERT METHOD
        private void insert(Node node)
        {
            Node temp = root;
            if (root == nil){
                root = node;
                node.color = black;
                node.parent = nil;
            }
            else{
                node.color = red;
                while (true){
                    if (node.key < temp.key){
                        if (temp.left == nil){
                            temp.left = node;
                            node.parent = temp;
                            break;
                        }
                        else{
                            temp = temp.left;
                        }
                    }
                    else {
                        if (node.key >= temp.key){
                            if (temp.right == nil){
                                temp.right = node;
                                node.parent = temp;
                                break;
                            }
                            else{
                                temp = temp.right;
                            }
                        }
                    }
                }
                fix(node);
            }
        }
        // MAIN
        public static void main(String[] args) throws FileNotFoundException{
            // TO READ FROM FILE
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter file name (with .txt): ");
            String fileName = keyboard.nextLine();
            createTreeFromFile(fileName);
            
//            Scanner keyboard = new Scanner(System.in);
//            Node node;
//            RedBlackTree tree = new RedBlackTree();
//            for(int i = 0; i <= 10; i++){
//                System.out.print("\nEnter a number: ");
//                int nodeVal = keyboard.nextInt();
//                node = new Node(nodeVal);
//                tree.insert(node);
//                tree.print(node);
//            }
        }
        
        // METHOD TO FIX TREE
        private void fix(Node node){
            while (node.parent.color == red){
                Node uncle = nil;
                if (node.parent == node. parent.parent.left){
                    uncle = node.parent.parent.right;
                    if (uncle != nil && uncle.color == red){
                        node.parent.color = black;
                        uncle.color = black;
                        node.parent.parent.color = red;
                        node = node.parent.parent;
                        continue;
                    }
                    if (node == node.parent.right){
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = black;
                    node.parent.parent.color = red;
                    rotateRight(node.parent.parent);
                }
                else{
                    uncle = node.parent.parent.left;
                    if (uncle != nil && uncle.color == red){
                        node.parent.color = black;
                        uncle.color = black;
                        node.parent.parent.color = red;
                        node = node.parent.parent;
                        continue;
                    }
                    if (node == node.parent.left){
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = black;
                    node.parent.parent.color = red;
                    rotateLeft(node.parent.parent);
                }
            }
            root.color = black;
        }
        
        // ROTATE LEFT METHOD
        void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
            } else {//Need to rotate root
                Node right = root.right;
                root.right = right.left;
                right.left.parent = root;
                root.parent = right;
                right.left = root;
                right.parent = nil;
                root = right;
            }
        }

        // ROTATE RIGHT METHOD
        void rotateRight(Node node) {
            if (node.parent != nil) {
                if (node == node.parent.left) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }

                node.left.parent = node.parent;
                node.parent = node.left;
                if (node.left.right != nil) {
                    node.left.right.parent = node;
                }
                node.left = node.left.right;
                node.parent.right = node;
            } else {//Need to rotate root
                Node left = root.left;
                root.left = root.left.right;
                left.right.parent = root;
                root.parent = left;
                left.right = root;
                left.parent = nil;
                root = left;
            }
        }
        
        public int getKey(Node node){
            return node.key;
        }
        // PRINT TREE
        public void print(Node node){
            if (node == nil){
                return;
            }
//            if (node == root){
//                System.out.println("(" + node + ", " + node.parent + ")");
//            }
//            if (node.parent != null){
//                
//                System.out.print("(" + node + ", " + node.parent + ") ");
//            }
            if (node == root)
                System.out.println("(" + getKey(node) + ", null" + ")");
            else if (node.left.parent == null)
                System.out.print("(" + getKey(node) + ", " + getKey(node.parent) + ") ");
            else if (node.parent == node.left.parent)
                System.out.print("(" + getKey(node) + ", " + getKey(node.parent) + ") ");
            else
                System.out.print("\n(" + getKey(node) + ", " + getKey(node.parent) + ") ");
            
        }
        
        // CREATE TREE METHOD
        public static void createTreeFromFile(String fileName) throws FileNotFoundException{
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            int nodeValue;
            Node node;
            RedBlackTree tree = new RedBlackTree();
            while (input.hasNext())
            {
                nodeValue = input.nextInt();
                node = new Node(nodeValue);
                tree.insert(node);
                tree.print(node);
            }
        }
        
        
}
