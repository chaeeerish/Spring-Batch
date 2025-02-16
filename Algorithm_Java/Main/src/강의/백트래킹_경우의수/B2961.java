package 강의.백트래킹_경우의수;

import java.io.*;
import java.util.StringTokenizer;

public class B2961 {
    public static long[][] ingredient;
    public static long answer = Long.MAX_VALUE;
    public static int N;

    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ingredient = new long[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            ingredient[i][0] = Long.parseLong(st.nextToken());
            ingredient[i][1] = Long.parseLong(st.nextToken());
        }

        recursion(0, 1, 0, 0);

        System.out.println(answer);
    }

    public static void recursion(int ingredient_index, long bitter, long sweetness, long use) {
        if (use != 0) {
            answer = Math.min(answer, Math.abs(bitter - sweetness));
        }

        if (ingredient_index == N) {
            return;
        }

//        System.out.println("bitter - sweetness = " + (bitter - sweetness));

        // 이 재료를 사용했을 때
        recursion(ingredient_index + 1, bitter * ingredient[ingredient_index][0], sweetness + ingredient[ingredient_index][1], use + 1);

        // 이 재료를 사용하지 않았을 때
        recursion(ingredient_index + 1, bitter, sweetness, use);
    }
}
