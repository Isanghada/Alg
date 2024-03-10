package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19539
// - 그리디 : 1의 개수가 2의 개수보다 많으면 불가능!
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사과나무 개수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int total = 0;  // 전체 높이의 합
        int one = 0;    // 1이 사용된 횟수
        int two = 0;    // 2가 사용된 횟수
        while(N-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            total += num;
            one += num % 2;
            two += num / 2;
        }

        // 정답 반환
        // - 전체 높이가 3의 배수가 아니거나 1이 2보다 많이 사용된 경우 불가능, 아닐 경우 가능!
        sb.append((total % 3 > 0 || one > two) ? "NO" : "YES");
        System.out.println(sb);
    }
}
