package 강의.정수론;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken()) - 1;
        long B = Long.parseLong(st.nextToken());

        long tmp_A = A;
        for (int i = 1; i < 99; i++) {
            tmp_A += (long) ((long) (A / Math.pow(2, i)) * (Math.pow(2, i) - Math.pow(2, i - 1)));
        }

        long tmp_B = B;
        for (int i = 1; i < 99; i++)  {
            tmp_B += (long) ((long) (B / Math.pow(2, i)) * (Math.pow(2, i) - Math.pow(2, i - 1)));
        }

        System.out.println(tmp_B - tmp_A);
    }
}