package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/27468
// -
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N  = Integer.parseInt(in.readLine());
        int[] gap = N%4 == 1 ? new int[]{1, 2, -1, 2} : new int[]{2, -1, 2, 1};

        sb.append("YES\n");
        int base = 0, pt = 0;
        while (N-- > 0) {
            sb.append(base += gap[pt++]).append(' ');
            pt %= 4;
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
