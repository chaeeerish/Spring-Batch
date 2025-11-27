package 백준;

/*
    Z

    2^n X 2^n인 2차원 배열을 Z 모양으로 탐색하려고 한다.
    0 1
    2 3
    순서대로 방문한다.

    N이 2이상이면,
    4등분을 해서 재귀적으로 방문한다.
    0 1 4 5
    2 3 6 7
    8 9 12 13
    10 11 14 15

    [Input]
    N r c

    [Output]
    r행 c열을 몇 번째로 방문하는가

    [Solution]
    row row+1
    col col+1 이 될때까지 쪼개서
    0 1
    2 3 을 더해주면 될 것 같은데

    2^3 = 8
    array [o, o, o]

    3행 4열이라고 치면 = 24

    8/4 = 4
    3행은 4보다 작으므로 1, 2 중에 하나이고
    4열은 4보다 크거나 같으므로 2, 4 중에 하나이다.

    겹치는건 2!!

    row1 ~ row2, col1 ~ col2 가 주어졌을 때,
    왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
    중 어디에 속하는지만 알면 되는데
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1074_Z_양채린 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, r, c;
    static int[] array;
    static int n;

    public static void inputTest() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        // 0. 초기화를 한다.
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 입력 받는다.
        inputTest();

        // 2. 재귀를 이용하여 몇 번째 숫자인지 알아가보자
        array = new int[N];
        findArea(0, (2 << (N - 1)) - 1, 0, (2 << (N - 1)) - 1, 0);
//        System.out.println(Arrays.toString(array));

        int number = 0;
        for (int index = N - 1; index >= 0; index--) {
            number += Math.pow(4, N - 1 - index) * (array[index] - 1);
        }
        System.out.println(number);
    }

    // 0 ~ 3, 0 ~ 3
    public static void findArea(int row1, int row2, int col1, int col2, int index) {
        // 기저조건
        if (index == N) {
            return;
        }

        // 0 1 2 3
        // 중에 1을 middle로 한다.
        int middleRow = (row1 + row2) / 2;
        int middleCol = (col1 + col2) / 2;

        if (r <= middleRow) { // 1, 2
            if (c <= middleCol) {
                array[index] = 1;
                findArea(row1, middleRow, col1, middleCol, index + 1);
            } else { // c > middleCol
                array[index] = 2;
                findArea(row1, middleRow, middleCol + 1, col2, index + 1);
            }
        } else { // r > middleRow // 3, 4
            if (c <= middleCol) {
                array[index] = 3;
                findArea(middleRow + 1, row2, col1, middleCol, index + 1);
            } else { // c > middleCol
                array[index] = 4;
                findArea(middleRow + 1, row2, middleCol + 1, col2, index + 1);
            }
        }
    }
}
