package 백준;

import java.util.Scanner;

/*
* 도영이가 만든 맛있는 음식
*
* N개의 재료가 있다.
* 신맛 S와 쓴맛 B를 알고 있다.
*
* 신맛은 사용한 재료의 신맛의 곱이고,
* 쓴맛은 사용한 재료의 쓴맛의 합이다.
*
* [제한사항]
* 재료를 적어도 하나 이상 사용해야 한다.
*
* [Input]
* 재료의 개수 N
* 신맛과 쓴맛이 공백으로 구분
*
* [Output]
* 신맛과 쓴맛의 차이가 가장 적은 요리의 차이를 출력한다
*
* [Solution]
* N개의 재료를 각각 넣고, 안넣고, 넣고, 안넣고, ...
* 모두 검증해보아야 한다.
*
* 부분집합!!
*
* 기저조건에 도달했을 때,
* 신맛과 쓴맛의 차이를 비교해서 최소값을 static 변수로 유지해보자.
*
* */
public class BOJ_2961_도영이가만든맛있는음식_양채린 {
    public static int[][] ingredient;
    public static int ingredientN;
    public static int minDiffenrence;
    public static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ingredientN = scanner.nextInt();

        ingredient = new int[ingredientN][2];
        for (int ingredientIndex = 0; ingredientIndex < ingredientN; ingredientIndex++) {
            ingredient[ingredientIndex][0] = scanner.nextInt(); // 신맛
            ingredient[ingredientIndex][1] = scanner.nextInt(); // 쓴맛
        }

        minDiffenrence = Integer.MAX_VALUE;
        isSelected = new boolean[ingredientN];
        powerSet(0, 0);

        System.out.println(minDiffenrence);
    }

    // 모든 경우의 수를 재귀로 구한다
    public static void powerSet(int currentIndex, int pickCount) {
        if (currentIndex == ingredientN) {
            // 공집합은 제외한다
            if (pickCount == 0) return;

            // 기저조건에 도달했을 때, 신맛과 쓴맛의 차이를 구한다
            int sourness = 0; // 신맛
            int bitter = 0; // 쓴맛

            for (int ingredientIndex = 0; ingredientIndex < ingredientN; ingredientIndex++) {
                if (isSelected[ingredientIndex]) {
                    if (sourness == 0) {
                        sourness = ingredient[ingredientIndex][0];
                    } else {
                        sourness *= ingredient[ingredientIndex][0];
                    }

                    bitter += ingredient[ingredientIndex][1];
                }
            }

            minDiffenrence = Math.min(minDiffenrence, Math.abs(sourness - bitter));
            return;
        }

        isSelected[currentIndex] = true;
        powerSet(currentIndex + 1, pickCount + 1);

        isSelected[currentIndex] = false;
        powerSet(currentIndex  + 1, pickCount);
    }
}
