package 강의.이분탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2805 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] treeList = new long[N];
        long maxTreeHeight = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            treeList[i] = Long.parseLong(st.nextToken());
            maxTreeHeight = Math.max(maxTreeHeight, treeList[i]);
        }

        long start = 0;
        long end = maxTreeHeight;
        long mid, wood;
        long H = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            wood = 0;
            for (int i = 0; i < N; i++) {
                if (treeList[i] - mid > 0) {
                    wood += (treeList[i] - mid);
                }
            }

            if (wood < M) {
                end = mid - 1;
            } else { // wood >= M
                start = mid + 1;
                H = Math.max(H, mid);
            }
        }

        System.out.println(H);
    }
}
