package 강의.백트래킹;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15652 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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

        br.close();
        bw.flush();
        bw.close();
    }

    public static void recursion(int count) throws IOException {
        if (count == M) {
            print();
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (!arrayList.isEmpty() && arrayList.get(arrayList.size() - 1) > i) {
                continue;
            }
            arrayList.add(i);
            recursion(count + 1);
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public static void print() throws IOException {
        for (int i = 0; i < M; i++) {
            bw.write(arrayList.get(i) + " ");
        }
        bw.newLine();
    }
}
