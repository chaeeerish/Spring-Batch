package SWExpertAcademy;

/*
    N개의 식재료가 있다.
    식재료들을 N/2개씩 나누어 두 개의 요리를 하려고 한다.
    A음식, B음식을 만든다.

    A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 배분하자.
    식재료 i와 식재료 j는 같이 요리하게 되면 궁합이 잘 맞아 시너지 Sij가 발생한다.

    각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지 Sij들의 합이다

    Sij 정보를 가지고, 두 음식간의 맛의 차이가 최소가 되는 경우를 찾아라.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class P4012 {
    public static List<List<Integer>> combination = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P4012_input.txt"));
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            // 0. 초기화 한다.
            int ingredientN = Integer.parseInt(sc.nextLine()); // 짝수
            int[][] synergyList = new int[ingredientN][ingredientN];
            int[] indexArray = new int[ingredientN];

            for (int index = 0; index < ingredientN; index++) {
                String[] temp = sc.nextLine().split(" ");
                indexArray[index] = index;

                for (int index2 = 0; index2 < ingredientN; index2++) {
                    synergyList[index][index2] = Integer.parseInt(temp[index2]);
                }
            }

            // 1. 부분집합을 두 갈래로 나눈다. N/2 씩!
            combination = new ArrayList<>();
            boolean[] visited = new boolean[ingredientN];

            combination(indexArray, visited, 0, ingredientN, ingredientN / 2);

//            for (List<Integer> elemList : combination) {
//                for (Integer elem : elemList) {
//                    System.out.print(elem + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            int minAbsSynergy = Integer.MAX_VALUE;
            for (List<Integer> elemList : combination) {
                // 2. 나머지 부분집합을 구한다.
                List<Integer> powerSet1 = elemList; // {0, 1}
                List<Integer> powerSet2 = new ArrayList<>(); // {2, 3}

                for (int index = 0; index < ingredientN; index++) {
                    if (! powerSet1.contains(index)) {
                        powerSet2.add(index);
                    }
                }

                // 3. 각 부분집합 별로 시너지를 구한다.
                int synergy1 = getSynergy(synergyList, powerSet1);
                int synergy2 = getSynergy(synergyList, powerSet2);

                // 4. 시너지의 절대값을 구한다.
                int absSynergy = Math.abs(synergy1 - synergy2);
                minAbsSynergy = Math.min(minAbsSynergy, absSynergy);
            }

            answer.append("#").append((tc + 1) + " ").append(minAbsSynergy).append("\n");
        }

        System.out.println(answer);
    }

    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void print(int[] arr, boolean[] visited, int n) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i])
                temp.add(arr[i]);
        }
        combination.add(temp);
    }

    public static int getSynergy(int[][] synergyList, List<Integer> indexList) {
        int sum = 0;
        for (Integer index1 : indexList) {
            for (Integer index2 : indexList) {
                if (index1.equals(index2)) {
                    continue;
                }
                sum += synergyList[index1.intValue()][index2.intValue()];
            }
        }
        return sum;
    }
}
