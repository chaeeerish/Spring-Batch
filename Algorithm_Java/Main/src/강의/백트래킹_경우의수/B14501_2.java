package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501_2 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[][] consultList;
    public static int N;

    public static int[] dp;
    public static int Profit = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        consultList = new int[N + 1][2];
        dp = new int[N + 1];

        dp[0] = -1;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            consultList[i][0] = Integer.parseInt(st.nextToken());
            consultList[i][1] = Integer.parseInt(st.nextToken());

            dp[i] = -1;
        }

        recursion(1);

        System.out.println(dp[1]);
//        for (int i = 0; i < N + 1; i++) {
//            System.out.print(dp[i] + " ");
//        }
    }

    public static int recursion(int consultIdx) {
        if (consultIdx >= N + 1) {
            return 0;
        }
        if (dp[consultIdx] != -1) {
            return dp[consultIdx];
        }

        if (consultIdx + consultList[consultIdx][0] <= N + 1) {
            dp[consultIdx] = Math.max(dp[consultIdx],  recursion(consultIdx + consultList[consultIdx][0]) + consultList[consultIdx][1]);
        }
        dp[consultIdx] = Math.max(dp[consultIdx], recursion(consultIdx + 1));

        return dp[consultIdx];
    }
}
