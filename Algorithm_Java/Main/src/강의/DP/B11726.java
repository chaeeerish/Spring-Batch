package 강의.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11726 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] array;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        array = new int[N];
        array[0] = 1;
        array[1] = 2;
        for (int i = 2; i < N; i++) {
            array[i] = (array[i - 1] + array[i - 2]) % 10007;
        }

        System.out.println(array[N - 1] % 10007);
    }
}
