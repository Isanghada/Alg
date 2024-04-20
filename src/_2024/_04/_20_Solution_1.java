package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15717
// - 참고 : https://blog.naver.com/rdd573/221270261981
// - 거듭제곱 : 나이를 먹는 경우의 수는 1이 늘어날 수록 2배로 증가하므로, 거듭제곱을 통해 계산
public class _20_Solution_1 {
    private static final int MOD = 900528;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 나이 입력
        long N = Long.parseLong(in.readLine());

        // 나머지 연산을 위한 변수
        final int MOD = 1_000_000_007;

        // 2의 거듭 제곱으로 경우의 수 계산
        sb.append(getCount(2, N-1, MOD));
        System.out.println(sb.toString());
    }
    public static long getCount(long a, long b, int mod){
        long sum = 1;
        while(b > 0){
            if((b & 1) == 1) sum = sum * a % mod;
            a = a * a % mod;
            b /= 2;
        }

        return sum;
    }
}
