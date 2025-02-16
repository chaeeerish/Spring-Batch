package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[][] consultList;
    public static int N;
    public static int Profit = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        consultList = new int[N + 1][2];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            consultList[i][0] = Integer.parseInt(st.nextToken());
            consultList[i][1] = Integer.parseInt(st.nextToken());
        }

        recursion(1, 0);

        System.out.println(Profit);
    }

    public static void recursion(int consultIdx, int profit) {
        Profit = Math.max(Profit, profit);

        if (consultIdx >= N + 1) {
            return;
        }

        // 진행
        if (consultIdx + consultList[consultIdx][0] <= N + 1) {
            recursion(consultIdx + consultList[consultIdx][0], profit + consultList[consultIdx][1]);
        }

        // 안진행
        recursion(consultIdx + 1, profit);
    }
}
