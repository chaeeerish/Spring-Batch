package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_2 {
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

        items = new int[N + 1][2];
        dp = new int[N + 1][K + 1]; // 물건, 무게
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            items[i][0] = Integer.parseInt(st.nextToken()); // 무게
            items[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        for (int idx = 1; idx < N + 1; idx++) {
            for (int weight = 0; weight < K + 1; weight++) {
                if (weight - items[idx][0] >= 0 && weight - items[idx][0] <= K) {
                    dp[idx][weight] = Math.max(dp[idx - 1][weight - items[idx][0]] + items[idx][1], dp[idx - 1][weight]);
                } else {
                    dp[idx][weight] = dp[idx - 1][weight];
                }
            }
        }

//        for (int idx = 0; idx < N + 1; idx++) {
//            for (int weight = 0; weight < K + 1; weight++) {
//                System.out.print(dp[idx][weight] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[N][K]);
    }
}
