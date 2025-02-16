package 강의.백트래킹_경우의수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B19942 {
    public static int[][] ingredient;
    public static int N;
    public static int P;
    public static int F;
    public static int S;
    public static int V;
    public static int Price = Integer.MAX_VALUE;
    public static boolean[] Choice_Candidate;
    public static boolean[] Choice_Answer;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ingredient = new int[N][5];

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        Choice_Candidate = new boolean[N];
        Choice_Answer = new boolean[N];
        for (int i = 0; i < N; i++) {
            Choice_Candidate[i] = false;
            Choice_Answer[i] = false;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            ingredient[i][0] = Integer.parseInt(st.nextToken()); // 단백질
            ingredient[i][1] = Integer.parseInt(st.nextToken()); // 지방
            ingredient[i][2] = Integer.parseInt(st.nextToken()); // 탄수화물
            ingredient[i][3] = Integer.parseInt(st.nextToken()); // 비타민
            ingredient[i][4] = Integer.parseInt(st.nextToken()); // 가격
        }

        recursion(0, 0, 0, 0, 0, 0);

        if (Price == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Price);
            for (int i = 0; i < Choice_Answer.length; i++) {
                if (Choice_Answer[i]) {
                    System.out.print((i + 1) + " ");
                }
            }
        }
    }

    public static void recursion(int ingredient_idx, int p, int f, int s, int v, int price) {
        if (p >= P && f >= F && s >= S && v >= V && price < Price) {
            Price = price;
            Choice_Answer = Choice_Candidate.clone();
        }

        if (ingredient_idx >= N) {
            return;
        }

        // 넣을 경우
        Choice_Candidate[ingredient_idx] = true;
        recursion(ingredient_idx + 1, p + ingredient[ingredient_idx][0], f + ingredient[ingredient_idx][1], s + ingredient[ingredient_idx][2], v + ingredient[ingredient_idx][3], price + ingredient[ingredient_idx][4]);
        Choice_Candidate[ingredient_idx] = false;

        // 안 넣을 경우
        recursion(ingredient_idx + 1, p, f, s, v, price);
    }
}
