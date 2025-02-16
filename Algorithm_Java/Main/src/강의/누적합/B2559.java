package 강의.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st1.nextToken()); // 온도 전체 날짜 수
        long K = Long.parseLong(st1.nextToken()); // 연속적인 날짜의 수

        long[] arr = new long[(int) N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st2.nextToken());
        }

        long[] prefix = new long[(int) N + 1];
        for (int i = 1; i < N + 1; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        long max = Long.MIN_VALUE;
        for (int i = (int) K; i < N + 1; i++) {
            if (prefix[i] - prefix[(int) (i - K)] > max) {
                max = prefix[i] - prefix[(int) (i - K)];
            }
        }

        System.out.println((int) max);
    }
}
