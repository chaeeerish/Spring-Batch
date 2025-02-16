package SWExpertAcademy;

import java.util.Scanner;

public class P1959 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        int answer = -1;

        int[] answerList = new int[T];

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] NList = new int[N];
            int[] MList = new int[M];

            for (int i = 0; i < N; i++) {
                NList[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                MList[i] = sc.nextInt();
            }

            int max = Integer.MIN_VALUE;
            if (N < M) {
                int j = 0;
                while (j <= M - N) {
                    int tmp = 0;

                    for (int i = 0; i < N; i++) {
                        tmp += NList[i] * MList[i + j];
                    }
                    j++;

                    max = Math.max(max, tmp);
                }
                answerList[test_case - 1] = max;
            } else {
                int i = 0;
                while (i <= N - M) {
                    int tmp = 0;

                    for (int j = 0; j < M; j++) {
                        tmp += NList[i + j] * MList[j];
                    }
                    i++;

                    max = Math.max(max, tmp);
                }
                answerList[test_case - 1] = max;
            }
        }

        for (int a = 0; a < T; a++) {
            System.out.println("#" + (a + 1) + " " + answerList[a]);
        }
    }
}
