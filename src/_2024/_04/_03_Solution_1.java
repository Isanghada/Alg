package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17953
// - 순열 : 각 점을 정렬하고 순서대로 연결하는 방식으로 해결
// - 참고 : https://yabitemporary.tistory.com/entry/BOJ-26524-%EB%B0%A9%ED%96%A5-%EC%A0%95%ED%95%98%EA%B8%B0-Python3
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        final int mod = 1000000007;
        long answer = 1;
        for(int n = 1; n <= N; n++) answer = (answer * n) % mod;

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
