package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2436
// -
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long gcd = Integer.parseInt(st.nextToken());    // 최대 공약수
        long lcd = Integer.parseInt(st.nextToken());    // 최소 공배수

        // a와 b의 곱은 gcd * lcd
        long target = gcd * lcd;

        // a, b 초기화
        long a = 0, b = 0;
        // a의 최대값 설정
        final long limit = (long)Math.sqrt(target);
        // 최소 공약수의 배수를 차례로 확인
        for(long c = gcd; c <= limit; c += gcd){
            // target의 약수이며 (c, target/c)의 최대 공약수가 gcd인 경우 가능!
            if(target % c == 0 && euclidean(c, target / c) == gcd){
                a = c;
                b = target / c;
            }
        }

        // 정답 출력
        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }
    // 유클리드 호제법 : a와 b의 최대 공약수는 b와 b % a의 최대 공약수와 같다
    private static long euclidean(long a, long b) {
        long r = a % b;
        if(r == 0) return b;
        return euclidean(b, r);
    }
}
