package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11560
// - DP : 이전 다항식의 결과에 새로운 다항식을 곱하여 차례로 계산
/*
    K = 1 : 1 + x
    K = 2 : (1 + x)(1 + x + x^2) = 1 + 2x + 2x^2 + x^3
    K = 3 : (1 + x)(1 + x + x^2)(1 + x + x^2 + x^3) = 1 + 3x + 5x^2 + 6x^3 + 5x^4 + 3x^5 + x^6
    K = 4 : (1 + x)(1 + x + x^2)(1 + x + x^2 + x^3)(1 + x + x^2 + x^3 + x^4)
            = 1 + 4x + 9x^2 + 15x^3 + 20x^4 + 22x^5 + 20x^6 + 15x^7 + 9x^8 + 4x^9 + x^10
    - 이전 결과에 새로운 다항식 (1 + x + x^2 + ... + x^(k-1) + x^k)을 곱하면 p(x)를 구할 수 있음.
    - 따라서, 이전 결과에 새로운 다항식의 항을 차례로 곱하여 계산하면 p(x)를 구할 수 있음.
 */
public class _07_Solution_1 {
    // K : 다항식의 개수, MAX : 다항식의 최대 차수
    static final int K = 20, MAX = K * (K+1) / 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // dp 계산
        long[][] dp = getDP(K, MAX);

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int K = Integer.parseInt(st.nextToken());   // 다항식의 개수
            int N = Integer.parseInt(st.nextToken());   // 구하고자 하는 항의 계수

            sb.append(dp[K][N]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static long[][] getDP(int K, int max) {
        // dp 초기화
        long[][] dp = new long[max+1][max+1];
        dp[1][0] = 1;
        dp[1][1] = 1;
        // k가 2인 경우부터 모두 계산
        for(int k = 2; k <= K; k++){
            // 이전 다항식의 최대 차수
            int pastN = (k-1) * k / 2;

            // 이전 다항식 결과에 새로운 식의 항을 모두 계산
            for(int n = 0; n <= k; n++){
                for(int past = 0; past <= pastN; past++) {
                    dp[k][past+n] += dp[k-1][past];
                }
            }
        }
        return dp;
    }
}
