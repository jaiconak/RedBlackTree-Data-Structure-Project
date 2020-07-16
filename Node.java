package redblacktree;
public class Node {
    private final int red = -1;
    private final int black = 1;
    int key = -1;
    int color = black;
    Node parent = null;
    Node left = null;
    Node right = null;
    
    Node(int key){
        this.key = key;
    }
}

