package 백준;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/*
 * 탈출
 * 
 * R행 C열로 이루어진 맵
 * . = 빈 곳
 * * = 물
 * X = 돌
 * D = 비버굴
 * S = 고슴도치위치
 * 
 * 매 분마다
 * 고슴도치는 인접한 칸으로 이동할 수 있다. 
 * 
 * 물도 매 분마다 비어있는 칸으로 확장한다. 
 * 인접하다는건 적어도 한 변을 공유한다는거임! 
 * 
 * 물과 고슴도치는 돌을 통과할 수 없고
 * 고슴도치는 물을 통과할 수 없고
 * 물은 소굴을 통과할 수 없다 
 * 
 * 고슴도치가 안전하게 비버 굴로 이동하기 위해 필요한 최소시간
 * 
 * [제약사항]
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 
 * 다음 시간에 물이 찰 예정! 
 * 
 * [Input]
 * R C
 * R X C 정보
 * 
 * [Output]
 * 비버의 굴로 이동할 수 있는 가장 빠른 시간
 * 만약, 비버의 굴로 이동할 수 없다면, KAKTUS 
 * 
 * [Solution]
 * BFS
 * 1. 입력받는다.
 * 2. Case Queue가 빌 때까지 반복한다.
 * 	2-1. source의 상 하 좌 우에 갈 수 있는 곳에 간다. (물이 차지 않을 곳)
 * 		2-1-1. 갈 수 있는 곳이 없다면 해당 경우는 버린다.
 * 		2-1-2. 갈 수 있는 곳에 destination이 있다면, minArrivalTime update하고 해당 경우도 버린다.
 * 	2-2. 물을 확장한다.
 * 	2-3. Case Queue에 넣는다.
 *
 * XX => DFS로 바꿀꺼다
 *
 * DFS
 * 1. 입력받는다.
 * 2. 물을 채운다.
 * 3. source의 상 하 좌 우 가 갈 수 있는 곳을 간다. (물이 찰 곳이 아니면서 && 돌이 아닌 곳)
 * 	2-1. source를 이동한다.
 * 		2-1-1. 갈 수 있는 곳에 destination이 있다면, minArrivalTime update하고 해당 경우도 버린다.
 *  2-2. 해당 경우를 기반으로 DFS 함수를 돌린다.
 * 3. 갈 수 있는 곳이 없다면 해당 경우는 버린다.
 */

public class BOJ_3055_탈출_양채린 {
	static class Case {
		int sourceRow;
		int sourceCol;
		int time;

		public Case(int sourceRow, int sourceCol, int time) {
			this.sourceRow = sourceRow;
			this.sourceCol = sourceCol;
			this.time = time;
		}
	}

	static Scanner sc;

	static int R;
	static int C;
	static char[][] map;
	static int[][] waterTime;
	static boolean[][] sourceIsVisited;

	static int startRow;
	static int startCol;
	static int minArrivalTime;

	static void input() {
		String[] strings = sc.nextLine().split(" ");
		R = Integer.parseInt(strings[0]);
		C = Integer.parseInt(strings[1]);

		map = new char[R][C];
		sourceIsVisited = new boolean[R][C];

		for (int row = 0; row < R; row++) {
			map[row] = sc.nextLine().toCharArray();
			for (int col = 0; col < C; col++) {
				if (map[row][col] == 'S') {
					startRow = row;
					startCol = col;

					sourceIsVisited[row][col] = true;
					map[row][col] = '.';
				}
			}
		}

		minArrivalTime = Integer.MAX_VALUE;

		waterTime = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				waterTime[i][j] = Integer.MAX_VALUE; // waterTime 초기화
			}
		}
	}
	
	public static void main(String[] args) {
		// 1. 입력받는다.
		sc = new Scanner(System.in);
		input();
 
		// 2. BFS
		int time = 0;
		Queue<Case> queue = new LinkedList<>();
		queue.add(new Case(startRow, startCol, time));
		sourceIsVisited[startRow][startCol] = true;

		waterSpread();
//		System.out.println("WATERSPREAD");
//		for (int row = 0; row < R; row++) {
//			for (int col = 0; col < C; col++) {
//				System.out.print(waterTime[row][col] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		while (! queue.isEmpty()) {
			Case now = queue.poll();

			if (now.time != time) {
				time = now.time;
			}

//			System.out.println("time = " + now.time);
//			printMap(now.sourceRow, now.sourceCol);

			for (int d = 0; d < 4; d++) {
				int newRow = now.sourceRow + dRow[d];
				int newCol = now.sourceCol + dCol[d];

				if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C) {
					if (map[newRow][newCol] == 'D') {
						System.out.println(now.time + 1);
						return;
					}

					if (map[newRow][newCol] != 'X'
							&& waterTime[newRow][newCol] > now.time + 1
							&& ! sourceIsVisited[newRow][newCol]) {
						sourceIsVisited[newRow][newCol] = true;
						queue.add(new Case(newRow, newCol, now.time + 1));
					}
				}
			}
		}

		// 4. 결과를 출력한다.
		System.out.println("KAKTUS");
	}

	static int[] dRow = {-1, 0, 1, 0};
	static int[] dCol = {0, 1, 0, -1};

	static class Water {
		int row;
		int col;
		int time;

		public Water(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}

	static void waterSpread() {
		Queue<Water> queue = new LinkedList<>();

		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] == '*') {
					queue.add(new Water(row, col, 0));
					waterTime[row][col] = 0;
				}
			}
		}

		while (! queue.isEmpty()) {
			Water water = queue.poll();

			for (int d = 0; d < 4; d++) {
				int newRow = water.row + dRow[d];
				int newCol = water.col + dCol[d];

				if (newRow >= 0 && newRow < R
						&& newCol >= 0 && newCol < C
						&& map[newRow][newCol] == '.'
						&& waterTime[newRow][newCol] == Integer.MAX_VALUE) {
					waterTime[newRow][newCol] = water.time + 1;
					queue.add(new Water(newRow, newCol, waterTime[newRow][newCol]));
				}
			}
		}
	}

	static void printMap(int sourceRow, int sourceCol) {
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				System.out.print(waterTime[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

