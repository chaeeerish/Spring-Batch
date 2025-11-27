package SWExpertAcademy;

import java.util.Scanner;

/*
 * 프림 알고리즘
 */

public class SWEA_1251_하나로_프림_양채린 {
	static Scanner scanner;
	
	static long T;
	static long N;
	static double[] xList;
	static double[] yList;
	static int[][] matrix;
	static double E;
	
	static double[] minFee;
	static boolean[] visited;
	static double weightSum;

	static void input() {
		N = Long.parseLong(scanner.nextLine());
        
		String[] inputStrings;
		inputStrings = scanner.nextLine().split(" ");
		xList = new double[(int) N];
		for (long n = 0; n < N; n++) {
			xList[(int) n] = Double.parseDouble(inputStrings[(int) n]);
		}
		
		inputStrings = scanner.nextLine().split(" ");
		yList = new double[(int) N];
		for (long n = 0; n < N; n++) {
			yList[(int) n] = Double.parseDouble(inputStrings[(int) n]);
		}
		
		E = Double.parseDouble(scanner.nextLine());  // E도 double로 변경
		minFee = new double[(int) N];
		visited = new boolean[(int) N];
		
		weightSum = 0D;
	}
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		T = Long.parseLong(scanner.nextLine());

		StringBuilder answer = new StringBuilder();

        for (long tc = 0; tc < T; tc++) {
        	// 1. 입력받는다.
        	input();
        	
        	minFee = new double[(int) N];
        	for (int index = 0; index < N; index++) {
        		minFee[index] = Double.MAX_VALUE;
        	}
        	        	
        	// 2. 0번째 노드를 MST에 넣는다.
        	minFee[0] = 0D;
        	
        	// 3. 이후, 가장 가까운 노드에 대하여 MST에 합친다.
        	for (int nodeCount = 0; nodeCount < N; nodeCount++) {
        		// 3-1. 비트리 정점 중 트리에 속할 가장 유리한 정점을 찾는다. 
        		double minValue = Double.MAX_VALUE;
        		long minVertex = -1;
        		for (int findVertex = 0; findVertex < N; findVertex++) {
        			if (! visited[findVertex] && minValue > minFee[findVertex]) {
        				minValue = minFee[findVertex];
        				minVertex = findVertex;	
        			}
        		}
        		
        		if (minVertex == -1) break;
        		weightSum += minValue;
        		visited[(int) minVertex] = true;
        		
        		// 3-2. 찾았으면, 새롭게 확장된 트리와 나머지 비트리 정점의 거리를 갱신한다. 
        		for (int restVertex = 0; restVertex < N; restVertex++) {
        			if (! visited[restVertex] && getDistance((int) minVertex, (int)restVertex) < minFee[restVertex]) {
        				minFee[restVertex] = getDistance((int) minVertex, (int) restVertex);
        			}
        		}
        	}
        	
            answer.append("#").append(tc + 1).append(" ").append(Math.round(weightSum)).append("\n");
        }
        
        System.out.println(answer);
	}
	
	public static double getDistance(int v1, int v2) {
		double dx = Math.abs(xList[(int) v1] - xList[(int) v2]);
		double dy = Math.abs(yList[(int) v1] - yList[(int) v2]);
		
		return E * (dx * dx + dy * dy);
	}
}
