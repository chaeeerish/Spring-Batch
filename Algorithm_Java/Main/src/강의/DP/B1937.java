package 강의.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1937 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int n;
    public static int[][] array;
    public static int[][] dp;

    public static int dx[] = {0, 0, -1, 1};
    public static int dy[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = recursion(i, j);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dp[i][j]);
//                System.out.print(dp[i][j] + " ");
            }
//            System.out.println();
        }

        System.out.println(answer + 1);
    }

    public static int recursion(int i, int j) {
//        System.out.println(i + ", " + j + " = " + array[i][j] + "가 방문을 시작한다.");

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int temp = -1;
        for (int k = 0; k < 4; k++) {
            if (0 <= i + dx[k] && i + dx[k] < n && 0 <= j + dy[k] && j + dy[k] < n) {
                if (array[i][j] < array[i + dx[k]][j + dy[k]]) {
//                    System.out.println(i + ", " + j + " = " + array[i][j] + "로 향했다.");
                    temp = Math.max(temp, recursion(i + dx[k], j + dy[k]) + 1);
                } else {
                    temp = Math.max(temp, 0);
                }
            }
        }

        dp[i][j] = temp;
        return dp[i][j];
    }
}
