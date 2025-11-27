```java
import java.util.*;

class Solution {
    class Edge implements Comparable<Edge> {
        int v;
        int u; 
        int weight; 
        
        Edge(int v, int u, int weight) {
            this.v = v;
            this.u = u; 
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int totalCost = 0;
        
        int[] parents = new int[n];
        for (int node = 0; node < n; node++) {
            parents[node] = node;
        }
        
        // ArrayList<Edge> edges = new ArrayList<>(); 
        // for (int index = 0; index < costs.length; index++) {
        //     edges.add(new Edge(costs[index][0], costs[index][1], costs[index][2]));
        // }
        // Collections.sort(edges);
        
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int index = 0; index < costs.length; index++) {
            if (findParent(parents, costs[index][0]) != findParent(parents, costs[index][1])) {
                totalCost += costs[index][2];
                union(parents, costs[index][0], costs[index][1]);
            }
        }
        
        return totalCost;
    }
    
    public int findParent(int[] parents, int node) {
        if (parents[node] == node) return node;
        return findParent(parents, parents[node]);
    }
    
    public void union(int[] parents, int node1, int node2) {
        int p1 = findParent(parents, node1);
        int p2 = findParent(parents, node2);
        
        if (p1 < p2) parents[p2] = p1; 
        else parents[p1] = p2;
    }
}
```