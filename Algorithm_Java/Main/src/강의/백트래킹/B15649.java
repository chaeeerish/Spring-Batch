package 강의.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15649 {
    public static int[] number;
    public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = i + 1;
        }

        recursion(0);
    }

    public static void recursion(int count) {
        if (count == M) {
            print();
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (arrayList.contains(i)) {
                continue;
            }
            arrayList.add(i);
            recursion(count + 1);
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
    }
}
