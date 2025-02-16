package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B12865 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[][] items;
    public static int N;
    public static int K;

    public static int[][] dp;
    public static int Value = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new int[N][2];
        dp = new int[N][K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            items[i][0] = Integer.parseInt(st.nextToken()); // 무게
            items[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        System.out.println(recursion(N - 1, K));
    }

    // Top Down 방식
    public static int recursion(int n, int k) {
        if (n < 0) {
            return 0;
        }

        if (dp[n][k] == 0) {
            if (items[n][0] > k) {
                dp[n][k] = recursion(n - 1, k);
            } else {
                dp[n][k] = Math.max(recursion(n - 1, k), recursion(n - 1, k - items[n][0]) + items[n][1]);
            }

        }
        return dp[n][k];
    }
}
