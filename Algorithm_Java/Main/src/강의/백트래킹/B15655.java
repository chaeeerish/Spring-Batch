package 강의.백트래킹;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15655 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[] number;
    public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(number);

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

        for (int i = 0; i < N; i++) {
            if (!arrayList.isEmpty() && arrayList.get(arrayList.size() - 1) >= number[i]) {
                continue;
            }
            arrayList.add(number[i]);
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
