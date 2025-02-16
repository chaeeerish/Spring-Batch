package 강의.LIS_LCS;

import java.io.*;
import java.util.*;

public class B2565 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<int[]> list = new ArrayList<>();

        int a, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            list.add(new int[]{a, b});
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] sorted_list = new int[N];
        for (int i = 0; i < N; i++) {
            sorted_list[i] = list.get(i)[1];
        }

        int[] dp = new int[N];
        int max = 1;
        for (int i = 0; i < N; i++) {
            max = 1;
            for (int j = 0; j < i; j++) {
                if (sorted_list[i] > sorted_list[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
//        System.out.println("^^");
//        for (int i = 0; i < N; i++) {
//            System.out.print(dp[i] + " ");
//        }

        System.out.println(N - max);
    }
}
