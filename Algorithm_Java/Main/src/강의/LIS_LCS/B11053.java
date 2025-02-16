package 강의.LIS_LCS;

import java.io.*;
import java.util.StringTokenizer;

public class B11053 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] array = new int[N];
        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            max = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
//            System.out.print(dp[i] + " ");
        }
//        System.out.println();
        System.out.println(max);
    }
}