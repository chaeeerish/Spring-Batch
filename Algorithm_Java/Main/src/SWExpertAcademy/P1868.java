package SWExpertAcademy;

/*
    표의 각 칸에는 지뢰가 있을 수도 있고 없을 수도 있다.
    1. 각 칸을 클릭했을 때, 지뢰가 있으면 파핑 파핑! 이라는 소리를 낸다.
    2. 지뢰가 없으면, 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지, 클릭한 칸에 숫자로 표시한다.
        근데! 0이라면,, 근처 8 방향에 지뢰가 없다는 것이므로..

    가장 적게 클릭해서, 지뢰 제외 모든 칸 오픈하기

    ‼️ 아 헐 연속적으로 ㅍ파파파팡 터지는 거였어..
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P1868 {
    static int count = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P1868_input.txt"));
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());
            char[][] map = new char[N][N];
            int[][] openMap = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            count = 0;

            for (int row = 0; row < N; row++) {
                String[] split = sc.nextLine().split("");
                for (int col = 0; col < N; col++) {
                    map[row][col] = split[col].charAt(0);
                    // . => 지뢰 없다.
                    // * => 지뢰 있다.
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (map[row][col] == '*') {
                        openMap[row][col] = -1;
                        open(map, openMap, row, col);
                    }
                }
            }

//            printOpenMap(openMap);

            // 코드 파파팡 짜기
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (openMap[row][col] == 0) {
                        openMap[row][col] = -1;
                        count++;
                        change(openMap, row, col);
                    }
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (openMap[row][col] != -1) {
                        count++;
                    }
                }
            }

            answer.append("#").append((tc + 1) + " ").append(count + "\n");
        }

        System.out.println(answer);
    }

    public static void open(char[][] map, int[][] openMap, int row, int col) {
        int[] dRow = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dCol = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int index = 0; index < 8; index++) {
            int newRow = row + dRow[index];
            int newCol = col + dCol[index];

            if (newRow >= 0 && newRow < openMap.length && newCol >= 0 && newCol < openMap[0].length && map[newRow][newCol] == '.') {
                openMap[newRow][newCol]++;
            }
        }
    }

    public static void change(int[][] openMap, int row, int col) {
        int[] dRow = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dCol = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int index = 0; index < 8; index++) {
            int newRow = row + dRow[index];
            int newCol = col + dCol[index];

            if (newRow >= 0 && newRow < openMap.length && newCol >= 0 && newCol < openMap[0].length) {
                if (openMap[newRow][newCol] == 0) {
                    openMap[newRow][newCol] = -1;
                    change(openMap, newRow, newCol);
                } else {
                    openMap[newRow][newCol] = -1;
                }
            }
        }
    }

    public static void printOpenMap(int[][] openMap) {
        for (int row = 0; row < openMap.length; row++) {
            for (int col = 0; col < openMap[0].length; col++) {
                System.out.print(openMap[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
