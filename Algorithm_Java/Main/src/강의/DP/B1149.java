package 강의.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1149 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] array = new int[N + 1][3];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
            array[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] answer = new int[N + 1][3];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    answer[i][j] = array[i][0] + Math.min(answer[i - 1][1], answer[i - 1][2]);
                } else if (j == 1) {
                    answer[i][j] = array[i][1] + Math.min(answer[i - 1][0], answer[i - 1][2]);
                } else {
                    answer[i][j] = array[i][2] + Math.min(answer[i - 1][0], answer[i - 1][1]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            min = Math.min(min, answer[N][j]);
        }
        System.out.println(min);
    }
}
