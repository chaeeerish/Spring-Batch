```java
import java.util.*;

/*


 [Input]
 
 [Output]
 
 [Solution]

 [주의]

*/

class Solution {
    static int[] 전위순회;
    static int[] 후위순회;
    
    static int 전위순회Index;
    static int 후위순회Index;
    
    class Node implements Comparable<Node> {
        int x;
        int y; 
        int number;
        Node left; 
        Node right;
        
        Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
        
        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) return this.x - o.x;
            return o.y - this.y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        
        ArrayList<Node> nodeList = new ArrayList<>(); 
        for (int index = 0; index < nodeinfo.length; index++) {
            nodeList.add(new Node(nodeinfo[index][0], nodeinfo[index][1], index));
        }
        Collections.sort(nodeList);
    
        Node root = nodeList.get(0);   
        // System.out.println(root.x + ", " + root.y);
        
        for (int index = 1; index < nodeinfo.length; index++) {
            insert(root, nodeList.get(index));
        }
        
        전위순회 = new int[nodeinfo.length];
        전위순회Index = 0; 
        전위순회함수(root); 
        
        후위순회 = new int[nodeinfo.length];
        후위순회Index = 0; 
        후위순회함수(root);
        
        return new int[][] {전위순회, 후위순회};
    }
    
    public void insert(Node root, Node current) {
        if (current.x < root.x) {
            if (root.left != null) insert(root.left, current);
            else root.left = current;
        } else {
            if (root.right != null) insert(root.right, current);
            else root.right = current;
        }
    }
    
    public void 전위순회함수(Node root) {
        전위순회[전위순회Index++] = root.number + 1;
        
        if(root.left != null) 전위순회함수(root.left);
        if(root.right != null) 전위순회함수(root.right);
    }
    
    public void 후위순회함수(Node root) {        
        if(root.left != null) 후위순회함수(root.left);
        if(root.right != null) 후위순회함수(root.right);
        
        후위순회[후위순회Index++] = root.number + 1;
    }
}
```