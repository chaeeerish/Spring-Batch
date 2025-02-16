package 강의.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14232 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int cnt = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (i >= 1000000) {
                sb.append(N);
                cnt++;
                break;
            }
            while (N % i == 0) {
                N /= i;
                cnt++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}
