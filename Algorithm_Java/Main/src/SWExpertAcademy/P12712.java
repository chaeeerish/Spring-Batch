package SWExpertAcademy;

import java.util.Scanner;

public class P12712 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        int[] outputList = new int[T];

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 1 +
                    int sum1 = map[i][j]; // 중심값 포함
                    for (int k = 1; k < M; k++) { // k는 1부터 M-1까지
                        if (i - k >= 0) sum1 += map[i - k][j]; // 위쪽
                        if (i + k < N) sum1 += map[i + k][j];  // 아래쪽
                        if (j - k >= 0) sum1 += map[i][j - k]; // 왼쪽
                        if (j + k < N) sum1 += map[i][j + k];  // 오른쪽
                    }
                    answer = Math.max(answer, sum1);

                    // 2 x
                    int sum2 = map[i][j]; // 중심값 포함
                    for (int k = 1; k < M; k++) { // k는 1부터 M-1까지
                        if (i - k >= 0 && j - k >= 0) sum2 += map[i - k][j - k]; // 왼쪽 위
                        if (i - k >= 0 && j + k < N) sum2 += map[i - k][j + k];  // 오른쪽 위
                        if (i + k < N && j - k >= 0) sum2 += map[i + k][j - k];  // 왼쪽 아래
                        if (i + k < N && j + k < N) sum2 += map[i + k][j + k];   // 오른쪽 아래
                    }
                    answer = Math.max(answer, sum2);
                }
            }

            outputList[test_case - 1] = answer;
        }

        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.println("#" + test_case + " " + outputList[test_case - 1]);
        }
    }
}
