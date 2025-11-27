package SWExpertAcademy.필수개념;

public class Permutation {
    // [1, 2, 3, 4]에서 3개를 선택해서 순열과 조합을 출력하라.
    // [2, 3, 4]에서 2개를 선택해서 순열과 조합을 출력하라.
    // [3, 4]에서 1개를 선택해서 순열과 조합을 출력하라.
    // N개 중에서 R개를 선택해라.
    // N-1개 중에서 R-1개를 선택해라.
    // 범위가 줄어드는 재귀이다.

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = arr.length;
        int[] output = new int[n];
        boolean[] visited = new boolean[n];

        permutation(arr, output, visited, 0, n, 2); // n개 중에 3개 순열
    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void print(int[] arr, int r) {
        for (int i = 0; i < r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
