package SWExpertAcademy;

import java.util.Scanner;

/*
    1부터 N번까지의 번호가 부여된 N개의 물건과 최대 K 부피만큼 넣을 수 있는 가방이 있다.
    각 물건은 Vi 부피, Ci 가치를 가지고 있다.

    물건들 중 몇 개를 선택해서 가방에 넣어서, 그 가치의 합을 최대로 하려고 한다.
    단, 선택한 물건들의 합이 K 이하여야 한다.

    [Input]
    테스트케이스개수
    물건의개수N 가방의부피K
    N개의 줄에
    부피Vi 가치Ci

    [Output]
    가방에 담을 수 있는 최대 가치

    [Solution]
    DP...

    N = 4, K = 5
    번호 1 2 3 4
    부피 1 3 4 2
    가치 2 2 4 3

    부피가 K이하라는 제한이 있으면서,, 물건의 가치는 최대가 되는,,

    2차원 배열을 만든다. NS[n][w]
    top - down
    NS[4][5] = Math.max(NS[n - 1][w - w[n]] + v[n], NS[n - 1][w])
 */

public class SWEA_3282_01Knapsack {
    public static int N;
    public static int W;
    public static int[][] items;
    public static int[][] knapsack;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            N = scanner.nextInt();
            W = scanner.nextInt(); // Weight Limit

            items = new int[N + 1][2]; // 0: 부피, 1: 가치
            for (int index = 1; index < N + 1; index++) {
                items[index][0] = scanner.nextInt();
                items[index][1] = scanner.nextInt();
            }

            knapsack = new int[N + 1][W + 1];
            for (int n = 1; n < N + 1; n++) {
                for (int w = 1; w < W + 1; w++) {
                    knapsack[n][w] = -1;
                }
            }

            NS(N, W);

            answer.append("#").append((tc + 1)).append(" ").append(knapsack[N][W]).append("\n");
        }
        System.out.println(answer);
    }

    public static int NS(int n, int w) {
        if (n < 0 || w < 0) return 0;
        if (knapsack[n][w] != -1) return knapsack[n][w];

        // n번째 아이템 넣는다
        int cand1 = 0;
        if (w >= items[n][0]) {
            cand1 = NS(n - 1, w - items[n][0]) + items[n][1];
        }

        // n번째 아이템 안넣는다
        int cand2 = NS(n - 1, w);

        knapsack[n][w] = Math.max(cand1, cand2);
        return knapsack[n][w];
    }
}
