package 강의.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[1001];
        int left = 0;
        int height = 0;
        int max_left = 0;
        int max_height = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            arr[left] = height;

            if (max_height <= height) {
                max_left = left;
                max_height = height;
            }
        }

        // left
        for (int i = 1; i <= max_left; i++) {
            arr[i] = Math.max(arr[i - 1], arr[i]);
            System.out.println(arr[i]);
        }

        // right
        for (int i = 999; i > max_left; i--) {
            arr[i] = Math.max(arr[i + 1], arr[i]);
            System.out.println(arr[i]);
        }

        // sum
        int sum = 0;
        for (int i = 0; i < 1001; i++) {
            sum += arr[i];
        }
        System.out.println(sum);
    }
}
