package SWExpertAcademy;

import java.util.Arrays;
import java.util.Scanner;

public class P1961 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        StringBuilder outputText = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            int[][][] answer = new int[4][N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer[0][i][j] = sc.nextInt();
                }
            }

            for (int k = 1; k < 4; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        answer[k][j][N - i - 1] = answer[k - 1][i][j];
                    }
                }
            }

//            for (int k = 1; k < 4; k++) {
//                for (int i = 0; i < N; i++) {
//                    for (int j = 0; j < N; j++) {
//                        System.out.print(answer[k][i][j] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//            }

            outputText.append(new String("#" + test_case + "\n"));
            for (int i = 0; i < N; i++) {
                outputText.append(intArraytoString(answer[1][i]));
                outputText.append(" ");
                outputText.append(intArraytoString(answer[2][i]));
                outputText.append(" ");
                outputText.append(intArraytoString(answer[3][i]));

                if (i != N - 1) outputText.append("\n");
            }
        }

        System.out.print(outputText.toString());
    }

    public static String intArraytoString(int[] arr) {
        String result = "";
        for (int num: arr) {
            result += num;
        }
        return result;
    }
}
