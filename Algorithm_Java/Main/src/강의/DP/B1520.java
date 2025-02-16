package 강의.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1520 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int dx[] = {0, 0, -1, 1};
    public static int dy[] = {-1, 1, 0, 0};

    public static int N;
    public static int M;
    public static int[][] map;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(recursion(0, 0));

//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static int recursion(int i, int j) {
        if (i == M - 1 && j == N - 1) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int temp = 0;
        for (int k = 0; k < 4; k++) {
            if (0 <= i + dx[k] && i + dx[k] < M && 0 <= j + dy[k] && j + dy[k] < N) {
                if (map[i][j] > map[i + dx[k]][j + dy[k]]) {
                    temp += recursion(i + dx[k], j + dy[k]);
                }
            }
        }

        dp[i][j] = temp;
        return dp[i][j];
    }
}
