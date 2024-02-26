package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2247
// - 정수 : 각 숫자를 약수로 가지는 수를 구하여 계산!
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 목표 정수 N
        int N = Integer.parseInt(in.readLine());

        // 정답 초기화
        long answer = 0;
        // 나머지 변수
        final int MOD = 1000000;
        // 절반 이후의 수는 약수로 포함될 수 없음
        final int LIMIT = N / 2;
        // 2 ~ N까지 모든 숫자 계산
        // - 1~N까지의 수에서 num이 약수인 개수 : (N-num) / num
        for(int num = 2; num <= LIMIT; num++) answer = (answer + ((N - num)/num) * num) % MOD;

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
