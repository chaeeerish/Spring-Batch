package SWExpertAcademy;

import java.util.Scanner;

/*
 * 사다리 게임의 사다리를 그린다. 
 * x = 0과 x = 9 사이에, 랜덤하게 세로, 가로, 막대들이 추가된다. 
 * 
 * 아래 방향으로 진행하면서, 
 * 좌우 방향으로 이동 가능한 통로가 나오면 방황 전환을 한다. 
 * 
 * [Input]
 * 사다리 구성을 입력받는다. 
 * 
 * [Output] 
 * 100X100 크기의 2차원 배열 사다리에서,,
 * 지정된 도착점에 대응되는 출발점 X를 반환하는 코드를 작성하라. 
 * 
 * [Solution]
 * startRow, startCol에서 위로 올라가면서
 * 꺽인거 나오면 꺽고...
 * 하다보면... 
 */

public class SWEA_1210_Ladder1_양채린 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = 10;
		StringBuilder answer = new StringBuilder();
				
		for (int tc = 0; tc < testCase; tc++) {
			// 초기화를 한다
			String[] temp = sc.nextLine().split(" ");
			
			int curRow = 0;
			int curCol = 0;
			
			int[][] map = new int[100][100];
			for (int row = 0; row < 100; row++) {
				temp = sc.nextLine().split(" ");
				for (int col = 0; col < 100; col++) {
					map[row][col] = Integer.parseInt(temp[col]);
					
					if (map[row][col] == 2) {
						curRow = row;
						curCol = col;
					}
				}
			}
			
			while (true) {			
				curRow--;
				
				if (curRow == 0) {
					break;
				}
				
				boolean flag = false;
				while (curRow >= 0 && curRow < 100 && (curCol - 1) >= 0 && (curCol - 1) < 100 && map[curRow][curCol - 1] == 1) {
					curCol = curCol - 1;
					flag = true;
				}
				
				if (flag) continue;
				
				while (curRow >= 0 && curRow < 100 && (curCol + 1) >= 0 && (curCol + 1) < 100 && map[curRow][curCol + 1] == 1) {
					curCol = curCol + 1;
				}
			}
			
			answer.append("#").append((tc + 1) + " ").append(curCol).append("\n");
		}
		System.out.println(answer);
	}
}
