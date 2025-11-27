package 백준;

import java.util.Scanner;

/*
 * 파이프 옮기기
 * 
 * 새 집은 N X N이고, 1 X 1 정사각형 칸으로 나누어져있다. 
 * 각 칸은 (r, c)로 나타낼 수 있다. 
 * 
 * 파이프를 밀어서 옮긴다.
 * 가로 2
 * 세로 2
 * 대각선 3
 * 
 * 밀려면 각 경우에 따라서 특정 구역은 비어있어야 한다. 
 * (1, 1), (1, 2)를 차지하고 있는 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 개수를 구해야한다.
 * 
 * [Input]
 * N
 * N X N 집의 상태
 * (0은 빈 칸, 1은 벽)
 * 
 * [Output]
 * 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수
 * 이동시킬 수 없는 경우에는 0이다.
 * 
 * [Solution]
 * (N, N)에 이동시킬 수 있는 방법
 * 1) (N - 1, N) X 2
 * 2) (N - 1, N - 1) X 3
 * 3) (N, N - 1) X 2 
 * => 1) + 2) + 3)
 * 
 * 1. 입력받는다.
 * 2. dp ... 
 * 	2-1. 
 * 		0	1	2	3	4
 * 	0	1	1	1	1 ... 
 * 	1	0	0	1	2		
 *  2	0	0	1	
 *  3	0	0	1
 *  .	.
 *  .	.
 *  .	.
 */

public class BOJ_17070_파이프옮기기1_양채린 {
	static Scanner scanner;

	static int N;
	static int[][] map;
	static boolean[][][] direction; // 0 가로 1 세로 2 대각선
	static int[][][] dp;
	
	static void input() {
		N = scanner.nextInt();
		
		map = new int[N][N];
		dp = new int[N][N][3];

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
			}
		}
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		input();
		
//		 * 		0	1	2	3	4
//		 * 	0	1	1	1	1 ... 
//		 * 	1	0	0	1	2		
//		 *  2	0	0	1	
//		 *  3	0	0	1
//		 *  .	.
//		 *  .	.
//		 *  .	.
		
		// 초기값 설정하기
		for (int col = 1; col < N; col++) {
			if (map[0][col] == 1) break;
			dp[0][col][0] = 1;
		}

		for (int row = 1; row < N; row++) {
			for (int col = 1; col < N; col++) {
				if (map[row][col] != 1) {
					dp[row][col][0] = dp[row][col - 1][2] + dp[row][col - 1][0];
					dp[row][col][1] = dp[row - 1][col][2] + dp[row - 1][col][1];
					if (map[row - 1][col - 1] == 0
						&& map[row - 1][col] == 0
						&& map[row][col - 1] == 0) {
						dp[row][col][2] = dp[row - 1][col - 1][0] + dp[row - 1][col - 1][1] + dp[row - 1][col - 1][2];
					}
				}
			}
		}

		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
	
	public static void printDp() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				System.out.print(dp[row][col] + " ");
			}
			System.out.println();
		}
	}
}
