package 강의.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long gcd = Long.parseLong(st.nextToken());
        long lsm = Long.parseLong(st.nextToken());

        long N = gcd * lsm;
        long A = Long.MAX_VALUE / 2;
        long B = Long.MAX_VALUE / 2;
        for (long n = gcd; n <= Math.sqrt(N); n += gcd) {
//            System.out.println(n);
//            System.out.println((N / n));
            if (n + (N / n) < (A + B)) {
                if (gcd(n, N / n) == gcd && lcm(n, N / n) == lsm) {
                    A = n;
                    B = N / n;
                }
            }
        }
        System.out.print(A + " " + B);
    }

    // 최대 공약수
    public static long gcd(long a, long b) {
        long tmp = -1;
        while (a % b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }

    // 최소 공배수
    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
