package SWExpertAcademy;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 그래프가 주어졌을 때, 최소 스패닝 트리를 구하라.
 */

public class SWEA_3124_최소스패닝트리_프림_양채린 {
	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		long weight;
		
		public Edge(int a, int b, long weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static Scanner scanner;
	
	static int T;
	static int V;
	static int E;
	
    static ArrayList<Edge>[] linkedList;
	static boolean[] visited;
	static PriorityQueue<Edge> minDistance;
	static long result;
	
	static void input() {
		String[] inputStrings = scanner.nextLine().split(" ");
		V = Integer.parseInt(inputStrings[0]);
		E = Integer.parseInt(inputStrings[1]);
				
		linkedList = new ArrayList[V];

        for (int index = 0; index < V; index++) {
            linkedList[index] = new ArrayList<>();
        }
		
		for (int index = 0; index < E; index++) {
			inputStrings = scanner.nextLine().split(" ");
			
			int a = Integer.parseInt(inputStrings[0]) - 1;
			int b = Integer.parseInt(inputStrings[1]) - 1;
			long weight = Long.parseLong(inputStrings[2]);
			
            linkedList[a].add(new Edge(a, b, weight));
            linkedList[b].add(new Edge(b, a, weight));		
        }
		
		// Prim
		visited = new boolean[V];
		minDistance = new PriorityQueue<Edge>();
		result = 0;
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		T = Integer.parseInt(scanner.nextLine());

		StringBuilder answer = new StringBuilder();

        for (long tc = 0; tc < T; tc++) {
        	// 1. 간선의 정보를 입력받는다. 
        	input();
        	
        	// 2. 0번째 노드를 트리에 넣도록 유도한다.  	
    		minDistance.add(new Edge(0, 0, 0));
        	
    		// 3. 지금까지 트리와 가장 적은 가중치를 가지는 노드를 추출해서 합친다. 
    		 while (!minDistance.isEmpty()) {
        		// 3-1. 비트리 노드 중 트리에 속할 가장 유리한 정점을 찾는다.     		
    			 Edge edge = minDistance.poll();

                 if (visited[edge.b]) {
                     continue;
                 }
        		
        		result += edge.weight;
        		visited[edge.b] = true;
        		
        		// 3-2. 새롭게 확장된 트리 정점과 나머지 비트리 정점 간의 최소 간선 비용 비교 후 업데이트        		
        		for (Edge edge2 : linkedList[edge.b]) {
        			if (!visited[edge2.b]) {
                        minDistance.add(edge2);
                    }                
        		}
        	}
    		
            answer.append("#").append(tc + 1).append(" ").append(result).append("\n");
        }
        
        System.out.println(answer);
	}
}
