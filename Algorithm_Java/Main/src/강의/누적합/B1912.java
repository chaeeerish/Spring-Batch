package 강의.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st1.nextToken());
        long[] arr = new long[(int) n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st2.nextToken());
        }

        long[] max = new long[(int) n];
        max[0] = arr[0];

        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i - 1] + arr[i], arr[i]);
        }

        long maxValue = Arrays.stream(max).max().getAsLong();
        System.out.println(maxValue);
    }
}
