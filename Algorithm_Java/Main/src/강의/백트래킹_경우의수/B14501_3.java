package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501_3 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[][] consultList;
    public static int N;

    public static int[] dp;
    public static int Profit = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        consultList = new int[N + 1][2];
        dp = new int[N + 2];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            consultList[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 일자
            consultList[i][1] = Integer.parseInt(st.nextToken()); // 비용
        }

        if (consultList[N][0] <= 1) {
            dp[N] = consultList[N][1];
        } else{
            dp[N] = 0;
        }

        for (int i = N - 1; i >= 1; i--) {
            if (i + consultList[i][0] <= N + 1) {
                dp[i] = Math.max(dp[i + 1], dp[i + consultList[i][0]] + consultList[i][1]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
//        for (int i = 0; i < N + 1; i++) {
//            System.out.print(dp[i] + " ");
//        }
    }
}
