package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15710
// - 분할 정복 : 거듭제곱을 분할 정복을 통해 빠르게 계산
public class _09_Solution_1 {
    public static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());   // 처음 숫자
        int b = Integer.parseInt(st.nextToken());   // 목표 숫자
        int n = Integer.parseInt(st.nextToken());   // 게임 진행 횟수

        // 정답 출력
        // - xor 연산을 통해서 모든 숫자가 될 수 있으므로,
        //   마지막 연산을 제외하고, (2^31)^(n-1)의 경우의 수가 존재한다!
        sb.append(pow(pow(2, 31), n-1));
        System.out.println(sb);
    }
    // 거듭제곱 함수 : a를 b번 제곱한 값 반환!
    private static long pow(long a, long b){
        if(b == 0) return 1L;
        // 남은 제곱 횟수가 홀수인 경우 a를 따로 곱하여 계산
        long value = (b % 2 == 0 ? 1L : a);
        return (value * pow((a * a) % MOD, b / 2)) % MOD;
    }
}
