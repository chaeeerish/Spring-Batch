package 강의.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st1.nextToken());
        int w = Integer.parseInt(st1.nextToken());

        int[] array = new int[w + 2];
        for (int i = 1; i < w + 1; i++) {
            array[i] = Integer.parseInt(st2.nextToken());
        }

        int left_max = -1;
        int right_max = -1;
        int water_quantity = 0;
        for (int i = 1; i < w + 1; i++) {
            left_max = -1;
            right_max = -1;

            for (int j = i - 1; j >= 1; j--) {
                left_max = Math.max(left_max, array[j]);
            }
            for (int j = i + 1; j < w + 1; j++) {
                right_max = Math.max(right_max, array[j]);

            }

            if (left_max == -1 || right_max == -1 || (Math.min(left_max, right_max) - array[i] < 0)) {
                continue;
            }

//            System.out.println(i);
//            System.out.println("left_max = " + left_max);
//            System.out.println("right_max = " + right_max);
//            System.out.println("(Math.min(left_max, right_max) - array[i]) = " + (Math.min(left_max, right_max) - array[i]));
//            System.out.println("water_quantity = " + water_quantity);

            water_quantity += (Math.min(left_max, right_max) - array[i]);
        }

        System.out.print(water_quantity);
    }
}
