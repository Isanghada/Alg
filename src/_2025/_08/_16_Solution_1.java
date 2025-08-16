package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/25427
// - DP : D -> K -> S -> H 순으로 가능한 경우 계산!
public class _16_Solution_1 {
    static final int TYPE = 4;
    static final char[] DKSH = new char[]{'0', 'D', 'K', 'S', 'H'};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 길이
        int N = Integer.parseInt(in.readLine());
        // 문자열
        char[] S = ('0'+in.readLine()).toCharArray();

        // DP 초기화
        long[][] dp = new long[TYPE+1][N+1];
        Arrays.fill(dp[0], 1L);

        // D, K, S, H 순으로 탐색
        for(int type = 1; type <= TYPE; type++){
            for(int i = 1; i <= N; i++){
                // 이전 결과 입력
                dp[type][i] = dp[type][i-1];
                // 현재 위치가 탐색하는 값인 경우 : 가능한 경우만큼 증가
                if(S[i] == DKSH[type]) dp[type][i] += dp[type-1][i-1];
            }
        }

        // 정답 출력
        sb.append(dp[TYPE][N]);
        System.out.println(sb.toString());
    }
}
