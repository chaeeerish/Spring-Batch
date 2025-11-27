package SWExpertAcademy.필수개념;

public class Combination {
    // 1. 현재 인덱스를 선택하는 경우
    // 2. 현재 인덱스를 선택하지 않는 경우

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int n = 4;
        boolean[] visited = new boolean[n];

        for (int i = 0; i <= n; i++) {
            System.out.println("\n" + n + "개 중에서 " + i + "개 뽑기");
            combination(arr, visited, 0, n, i);
        }
    }

    // 백트래킹 사용
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
        for (int i = 0; i < n; i++) {
            if (visited[i])
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
