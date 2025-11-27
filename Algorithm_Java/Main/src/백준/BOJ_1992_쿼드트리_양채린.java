package 백준;

import java.io.IOException;
import java.util.Scanner;

/*
 * 흑백 영상을 압추갛ㄴ다.
 * 모두 0이면 0
 * 모두 1이면 1
 *
 * 섞여있으면, 전체를 한번에 나타내지못하므로
 * 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
 * 이렇게 4개의 영역을 압축한 결과로 나타내야 한다.
 *
 * [input]
 * N
 * N * N
 *
 * [Output]
 * 압축한 결과
 *
 * [Solution]
 * 전체가 0인지 1인지 검사
 * 아니면
 * (을 열고
 * 왼쪽 위를 검사
 * 0인지 1인지 검사
 * 아니면 (을 열고
 * 왼쪽 위를 검사
 *
 * ...
 *
 * 반복 언젠가 0 또는 1로만 이루어진게 나온다면
 * 1을 적음
 *
 * 그리고 닫음 )로
 *
 * 4개가 남을까지 내려가는거임
 */

public class BOJ_1992_쿼드트리_양채린 {
    public static int N;
    public static int[][] map;
    public static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        // 0. 초기화를 한다.
        Scanner scanner = new Scanner(System.in);
        answer = new StringBuilder();

        N = Integer.parseInt(scanner.nextLine());
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            String[] temp = scanner.nextLine().split("");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(temp[col]);
            }
        }

        // 1. 계속해서 나눠서 0인지 1인지를 검사한다.
        // startRow, endRow, startCol, endCol
        validate(0, N - 1, 0, N - 1);

        System.out.println(answer.toString());
    }

    public static void validate(int startRow, int endRow, int startCol, int endCol) {
//        System.out.println("startRow = " + startRow);
//        System.out.println("endRow = " + endRow);
//        System.out.println("startCol = " + startCol);
//        System.out.println("endCol = " + endCol);

        if (isAll0(startRow, endRow, startCol, endCol)) {
            answer.append("0");
        } else if (isAll1(startRow, endRow, startCol, endCol)) {
            answer.append("1");
        } else { // 나눠야함
            answer.append("(");

            int middleRow = (startRow + endRow) / 2;
            int middleCol = (startCol + endCol) / 2;

            validate(startRow, middleRow, startCol, middleCol); // 왼쪽 위
            validate(startRow, middleRow, middleCol + 1, endCol); // 오른쪽 위
            validate(middleRow + 1, endRow, startCol, middleCol); // 왼쪽 아래
            validate(middleRow + 1, endRow, middleCol + 1, endCol); // 오른쪽 아래

            answer.append(")");
        }
    }

    public static boolean isAll0(int startRow, int endRow, int startCol, int endCol) {
        for (int row = startRow; row < endRow + 1; row++) {
            for (int col = startCol; col < endCol + 1; col++) {
                if (map[row][col] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isAll1(int startRow, int endRow, int startCol, int endCol) {
        for (int row = startRow; row < endRow + 1; row++) {
            for (int col = startCol; col < endCol + 1; col++) {
                if (map[row][col] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
