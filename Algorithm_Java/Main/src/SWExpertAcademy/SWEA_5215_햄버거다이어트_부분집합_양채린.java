package SWExpertAcademy;

/*
    햄버거의 맛은 치대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거

    [Input]
    테스트케이스 수
    재료의 수 제한 칼로리
    맛에 대한 점수와 칼로리
    ...

    [Ouput]
    민기가 좋아하는 햄버거를 먹으면서도 정해진 칼로리 이하의 조합!

    [Solution]
    부분집합 사용해서
    모든 부분집합 경우를 돌면서,
    선택된 재료들에 대해서 => 칼로리 계산 => 제한 안 넘으면 => 최대 만족도인지 검사!
 */

import java.util.Scanner;

public class SWEA_5215_햄버거다이어트_부분집합_양채린 {
    public static int[][] ingredient;
    public static int N;
    public static int L;

    public static boolean[] isSelected;

    public static int maxLike;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            String[] temp = scanner.nextLine().split(" ");
            N = Integer.parseInt(temp[0]);
            L = Integer.parseInt(temp[1]);

            ingredient = new int[N][2];
            isSelected = new boolean[N];
            maxLike = Integer.MIN_VALUE;

            for (int index = 0; index < N; index++) {
                temp = scanner.nextLine().split(" ");
                ingredient[index][0] = Integer.parseInt(temp[0]);
                ingredient[index][1] = Integer.parseInt(temp[1]);
            }

            powerset(0);
            answer.append("#").append((tc + 1) + " ").append(maxLike).append("\n");
        }
        System.out.println(answer);
    }

    public static void powerset(int cnt) {
        if (cnt == N) {
            int currentLike = 0;
            int currentCalories = 0;
            for (int index = 0; index < N; index++) {
                if (isSelected[index]) {
                    currentLike += ingredient[index][0];
                    currentCalories += ingredient[index][1];
                }
            }

            if (currentCalories <= L) {
                maxLike = Math.max(maxLike, currentLike);
            }

            return;
        }

        isSelected[cnt] = true;
        powerset(cnt + 1);

        isSelected[cnt] = false;
        powerset(cnt + 1);
    }
}
