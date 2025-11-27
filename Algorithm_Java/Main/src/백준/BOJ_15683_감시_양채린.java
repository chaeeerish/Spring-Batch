package 백준;

/*
    0 = 빈 칸

    1번 CCTV = 한 쪽 방향만
    2번 CCTV = 180도만 2방향
    3번 CCTV = 90도 2방향
    4번 CCTV = 270도 3방향
    5번 CCTV = 360도 4방향

    6 = 벽

    사무실 크기와 상태, 그리고 CCTV 정보가 주어졌을 때,
    CCTV 방향을 적절히 정해서 사각지대 최소 크기를 구해보자

    [Input]
    세로크기N 가로크기M
    N개의 줄에 각 사무실의 정보

    [Output]
    사각지대의 최소크기

    [Solution]
    CCTV로 최대한 많이 감시...
 */

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_15683_감시_양채린 {
    static int N;
    static int M;
    static ArrayList<Integer> CCTVRows;
    static ArrayList<Integer> CCTVCols;
    static ArrayList<Integer> CCTVNumbers;
    static char[][] office;
    static int[][] cancovers;

    static int minBlindSpot;

    public static void main(String[] args) {
        // 0. Input을 입력받는다
        Scanner scanner = new Scanner(System.in);
        String[] inputStrings = scanner.nextLine().split(" ");

        N = Integer.parseInt(inputStrings[0]);
        M = Integer.parseInt(inputStrings[1]);

        office = new char[N][M];
        for (int row = 0; row < N; row++) {
            inputStrings = scanner.nextLine().split(" ");
            for (int col = 0; col < M; col++) {
                office[row][col] = inputStrings[col].charAt(0);
            }
        }

        // 1. CCTV 정보를 추출한다
        CCTVRows = new ArrayList<>();
        CCTVCols = new ArrayList<>();
        CCTVNumbers = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (office[row][col] != '0' && office[row][col] != '6') {
                    CCTVRows.add(row);
                    CCTVCols.add(col);
                    CCTVNumbers.add(Character.getNumericValue(office[row][col])); // 숫자로 변환
                }
            }
        }

        // 2. 각 CCTV마다 가능한 방향은 모두 시도한다 DFS
        cancovers = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (office[row][col] != '0') {
                    cancovers[row][col] = 0;
                }
            }
        }

        minBlindSpot = N * M;
        dfs(0);
        System.out.println(minBlindSpot);
    }

    static void dfs(int cctvIndex) {
        if (cctvIndex == CCTVNumbers.size()) {
            // 최다 사각지대를 검사한다
            int blindSpot = 0;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (cancovers[row][col] == 0 && office[row][col] == '0') {
                        blindSpot += 1;
                    }
                }
            }

            minBlindSpot = Math.min(minBlindSpot, blindSpot);
            return;
        }

        int cctvRow = CCTVRows.get(cctvIndex);
        int cctvCol = CCTVCols.get(cctvIndex);
        int cctvType = CCTVNumbers.get(cctvIndex);

        // CCTV 종류에 따라 각 방향에 대해 감시 범위를 설정
        if (cctvType == 1) {
            // 1번 CCTV는 1방향만 감시
            for (int direction = 0; direction < 4; direction++) {
                cover(cctvRow, cctvCol, direction);
                dfs(cctvIndex + 1);
                uncover(cctvRow, cctvCol, direction);
            }
        } else if (cctvType == 2) {
            // 2번 CCTV는 2방향 감시
            for (int direction = 0; direction < 2; direction++) {
                cover(cctvRow, cctvCol, direction);
                cover(cctvRow, cctvCol, (direction + 2) % 4);
                dfs(cctvIndex + 1);
                uncover(cctvRow, cctvCol, direction);
                uncover(cctvRow, cctvCol, (direction + 2) % 4);
            }
        } else if (cctvType == 3) {
            // 3번 CCTV는 2방향 감시
            for (int direction = 0; direction < 4; direction++) {
                cover(cctvRow, cctvCol, direction);
                cover(cctvRow, cctvCol, (direction + 1) % 4);
                dfs(cctvIndex + 1);
                uncover(cctvRow, cctvCol, direction);
                uncover(cctvRow, cctvCol, (direction + 1) % 4);
            }
        } else if (cctvType == 4) {
            // 4번 CCTV는 3방향 감시
            for (int direction = 0; direction < 4; direction++) {
                cover(cctvRow, cctvCol, direction);
                cover(cctvRow, cctvCol, (direction + 1) % 4);
                cover(cctvRow, cctvCol, (direction + 2) % 4);
                dfs(cctvIndex + 1);
                uncover(cctvRow, cctvCol, direction);
                uncover(cctvRow, cctvCol, (direction + 1) % 4);
                uncover(cctvRow, cctvCol, (direction + 2) % 4);
            }
        } else if (cctvType == 5) {
            // 5번 CCTV는 4방향 모두 감시
            cover(cctvRow, cctvCol, 0);
            cover(cctvRow, cctvCol, 1);
            cover(cctvRow, cctvCol, 2);
            cover(cctvRow, cctvCol, 3);
            dfs(cctvIndex + 1);
            uncover(cctvRow, cctvCol, 0);
            uncover(cctvRow, cctvCol, 1);
            uncover(cctvRow, cctvCol, 2);
            uncover(cctvRow, cctvCol, 3);
        }
    }

    static void cover(int row, int col, int direction) {
        if (direction == 0) { // 위
            for (int r = row - 1; r >= 0; r--) {
                if (office[r][col] == '6') break;
                if (office[r][col] == '0') cancovers[r][col] += 1;
            }
        } else if (direction == 1) { // 오른쪽
            for (int c = col + 1; c < M; c++) {
                if (office[row][c] == '6') break;
                if (office[row][c] == '0') cancovers[row][c] += 1;
            }
        } else if (direction == 2) { // 아래
            for (int r = row + 1; r < N; r++) {
                if (office[r][col] == '6') break;
                if (office[r][col] == '0') cancovers[r][col] += 1;
            }
        } else if (direction == 3) { // 왼쪽
            for (int c = col - 1; c >= 0; c--) {
                if (office[row][c] == '6') break;
                if (office[row][c] == '0') cancovers[row][c] += 1;
            }
        }
    }

    static void uncover(int row, int col, int direction) {
        if (direction == 0) { // 위
            for (int r = row - 1; r >= 0; r--) {
                if (office[r][col] == '6') break;
                if (office[r][col] == '0') cancovers[r][col] -= 1;
            }
        } else if (direction == 1) { // 오른쪽
            for (int c = col + 1; c < M; c++) {
                if (office[row][c] == '6') break;
                if (office[row][c] == '0') cancovers[row][c] -= 1;
            }
        } else if (direction == 2) { // 아래
            for (int r = row + 1; r < N; r++) {
                if (office[r][col] == '6') break;
                if (office[r][col] == '0') cancovers[r][col] -= 1;
            }
        } else if (direction == 3) { // 왼쪽
            for (int c = col - 1; c >= 0; c--) {
                if (office[row][c] == '6') break;
                if (office[row][c] == '0') cancovers[row][c] -= 1;
            }
        }
    }
}