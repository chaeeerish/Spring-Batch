package SWExpertAcademy.필수개념;

// 부분 집합
public class PowerSet {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = 3;
        boolean[] visited = new boolean[n];

        powerSet(arr, visited, n, 0);
    }

    public static void powerSet(int[] arr, boolean[] visited, int n, int index) {
        if (index == n) {
            print(arr, visited, n);
            return;
        }

        visited[index] = false;
        powerSet(arr, visited, n, index + 1);

        visited[index] = true;
        powerSet(arr, visited, n, index + 1);
    }

    public static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i])
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
