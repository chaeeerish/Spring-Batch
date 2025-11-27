package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 빵집
 *
 * 근처 빵집에 몰래... 파이프를 설치해서 가스를 훔쳐서 사용할꺼다.
 * 빵집이 있는 곳은 R X C로 표현할 수 있다.
 * 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집
 *
 * 가스관과 빵집을 연결하는 파이프를 설치한다
 * 모든 파이프라인은 첫째 열에서 설치해야하고, 마지막 열에서 끝나야 한다.
 * 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있다.
 *
 * 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다.
 * 경로는 겹칠 수 없다. 서로 접할 수도 없다.
 * 즉!! 칸을 지나는 파이프는 하나이다.!!
 *
 * + 건물도 피해서!
 *
 * [Input]
 * R C
 * . : 빈칸
 * x : 건물
 *
 * (처음과 마지막 열은 항상 비어있다.)
 *
 * [Output]
 * 원웅이 빵집의 모습이 주어졌을 때, 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수~?
 *
 * [Solution]
 * Backtracking..?
 *
 * 0에서 출발 -> 1에서 출발 -> ... 5에서 출발
 * 각 출발지 경우에 따라서)
 * 		가다가 안되면 X 돌아오기 => backtracking
 * 		가다가 되면 끝까지 경로 찍고, 다음 출발지 시작!
 */

public class BOJ_3109_빵집_양채린 {
    public static boolean[][] visited;
    public static char[][] map;
    public static int ROW;
    public static int COL;
    public static int maxPipelineCount;

    public static void main(String[] args) throws IOException {
        // 0. 초기화를 한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        map = new char[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            map[row] = br.readLine().toCharArray();
        }

        // 1. 백트래킹을 시작한다.
        visited = new boolean[ROW][COL];
        maxPipelineCount = 0;
        for (int row = 0; row < ROW; row++) {
            backtracking(row, 0);
        }

        System.out.println(maxPipelineCount);
    }

    public static boolean backtracking(int currentRow, int currentCol) {
        int[] dRow = {-1, 0, 1};

        for (int index = 0; index < dRow.length; index++) {
            int newRow = currentRow + dRow[index];
            int newCol = currentCol + 1;

            if (newRow >= 0 && newRow < ROW && newCol >= 0 && newCol < COL
                    && map[newRow][newCol] == '.'
                    && visited[newRow][newCol] == false) {
                if (newCol == COL - 1) {
                    maxPipelineCount++;
                    return true;
                }

                visited[newRow][newCol] = true;

                if (backtracking(newRow, newCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printVisited() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                System.out.print(visited[row][col] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
