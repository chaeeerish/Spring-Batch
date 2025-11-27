package SWExpertAcademy;

import java.util.Scanner;

/*
 * N X N 도시에 홈방범 서비스를 제공한다.
 * 마름모 모양이다.
 * 
 * 서비스 영역 K
 * 운영비용 = K * K + (K - 1) * (K - 1)
 * 
 * K = 1 | 1
 * K = 2 | 5
 * K = 3 | 13
 * K = 4 | 25
 * 
 * 벗어나도 운영비용은 변하지 않는다. 
 * 최대한 많은 집에 홈방법 서비스 제공하고 싶다.
 * 홈방법 서비스를 제공받는 집들은 각각 M의 비용을 지불한다. 
 * 
 * 보안회사이익 = 집개수 X M - 운영비용
 * 
 * [Input]
 * T
 * N M 
 * N X N 크기의 도시 정보
 * 
 * [Output]
 * 손해를 보지 않으면서 (== 이익이 0 이상이면서)
 * 홈 방법 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾았을 때
 * 서비스를 제공받는 집들의 수 
 * 
 * [Solution]
 * 최대 K = n / 2 (n: 짝수), (n + 1) / 2 (n: 홀수)
 * 1부터 최대 K까지 검증
 * 		그 속에서, 맨끝에 걸칠 때부터 맨끝에 걸칠 때까지 검증한다.
 * 		뭘? 이익을... 
 * 			그 이익이 0 이상이다. 
 * 				이때, 최대 집의 수를 MAX로 계산한다. 
 */

public class SWEA_2117_홈방범서비스_양채린 {
	static Scanner scanner;
	static StringBuilder stringBuilder;
	
	static int T;
	static int N;
	static int M;
	static int[][] map;

	static int count;
	static int answer;
	
	static void input() {
		N = scanner.nextInt();
		M = scanner.nextInt();
		
		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col  = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
			}
		}

		answer = 0;
	}
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		stringBuilder = new StringBuilder();
		T = scanner.nextInt();
		
        for (int tc = 0; tc < T; tc++) {
        	// 1. 입력받는다
        	input();
        	
        	// 2.
			for (int startRow = 0; startRow < N; startRow++) {
				for (int startCol = 0; startCol < N; startCol++) {
					for (int k = 1; k <= 2 * N; k++) {
						count = 0;

//						System.out.println("startRow = " + startRow);
//						System.out.println("startCol = " + startCol);

						for (int row = startRow - (k - 1); row <= startRow + (k - 1); row++) {
							for (int col = startCol - (k - 1) + Math.abs(row - startRow); col <= startCol + (k - 1) -
									Math.abs(row - startRow); col++) {
								if (row >= 0 && row < N && col >= 0 && col < N && map[row][col] == 1) {
								   	count += 1;
								}
//								System.out.println(count);
							}
						}

						int cost = k * k + (k - 1) * (k - 1);
						if (cost <= count * M) {
							answer = Math.max(answer, count);
						}
					}
				}
			}

			stringBuilder.append("#").append((tc + 1) + " ").append(answer).append("\n");
		}
		System.out.println(stringBuilder);
	}
}
