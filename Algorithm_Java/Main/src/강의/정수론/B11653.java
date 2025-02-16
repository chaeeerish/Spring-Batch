package 강의.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int copyN = N;

        ArrayList<Integer> arrayList = new ArrayList<>();
        int n = 2;
        while (copyN != 1) {
            if (copyN % n == 0) {
                arrayList.add(n);
                copyN /= n;
            } else {
                ++n;
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
