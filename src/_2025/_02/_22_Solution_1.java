package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

// https://www.acmicpc.net/problem/2226
// - 참고 : https://gamzaggang7.tistory.com/155
// - DP : 규칙을 찾아 차례로 계산!
//          N       2진수                 0 그룹 개수
//          1       1                     0
//          2       01                    1 (0 * 2 + 1)
//          3       1001                  1 (1 * 2 - 1)
//          4       01101001              3 (1 * 2 + 1)
//          5       1001011001101001      5 (3 * 2 - 1)
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        // 정답 출력
        sb.append(dp(N));
        System.out.println(sb);
    }

    private static BigInteger dp(int n) {
        BigInteger count = BigInteger.ZERO;

        for(int i = 1; i < n; i++){
            if((i & 1) == 1) count = count.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
            else count = count.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE);
        }

        return count;
    }
}
