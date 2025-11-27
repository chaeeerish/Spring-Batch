package SWExpertAcademy;

import java.util.*;

/*
    문자열 집합: 문자열들로 구성된 집합
    {"aba", "cdefasad", "wefawef"}

    2개의 문자열 집합이 주어졌을 때, 두 집합에 모두 속하는 문자열 원소의 개수를 구해라.

    [Input]
    테스트케이스수 T
    첫번째집합원소의개수 N 두번째집합원소의개수 M

    [Output]
    두 집합에 모두 속하는 문자열 원소의 개수를 구해라.

    [Solution]
    아 교집합~!
 */

public class SWEA_2948_문자열교집합 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            String[] temps = scanner.nextLine().split(" ");
            int N = Integer.parseInt(temps[0]);
            int M = Integer.parseInt(temps[1]);

            String[] array1 = new String[N];
            String[] array2 = new String[N];

            HashSet<String> set1 = new HashSet<>();
            HashSet<String> set2 = new HashSet<>();

            temps = scanner.nextLine().split(" ");
            for (int index = 0; index < N; index++) {
                set1.add(temps[index]);
            }

            temps = scanner.nextLine().split(" ");
            for (int index = 0; index < M; index++) {
                set2.add(temps[index]);
            }

            set1.retainAll(set2);
            answer.append("#").append((tc + 1)).append(" ").append(set1.size()).append("\n");
        }

        System.out.println(answer);
    }
}
