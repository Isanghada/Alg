package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1958
// - LCS : DP를 활용해 최장 공통 부분 수열 탐색!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력!
        char[] A = in.readLine().toCharArray();
        char[] B = in.readLine().toCharArray();
        char[] C = in.readLine().toCharArray();

        // 정답 출력
        // - 3개의 문자열을 통해 최장 공통 부분 수열 탐색!
        sb.append(lcs3(A, B, C));
        System.out.println(sb.toString());
    }
    private static int lcs3(char[] A, char[] B, char[] C) {
        // 문자열 길이!
        int lenA = A.length;
        int lenB = B.length;
        int lenC = C.length;

        // DP 초기화!
        int[][][] dp = new int[lenA+1][lenB+1][lenC+1];

        // a : 사용할 A의 인덱스
        // b : 사용할 B의 인덱스
        // c : 사용할 C의 인덱스
        for(int a = 1; a <= lenA; a++){
            for(int b = 1; b <= lenB; b++){
                for(int c = 1; c <= lenC; c++){
                    // (a, b, c)가 동일한 경우! => 값 갱신!
                    if(A[a-1] == B[b-1] && B[b-1] == C[c-1]) dp[a][b][c] = dp[a-1][b-1][c-1] + 1;
                    // (a-1, b, c), (a, b-1, c), (a, b, c-1) 중 최대값 선택!
                    else dp[a][b][c] = Math.max(dp[a-1][b][c],
                                       Math.max(dp[a][b-1][c],
                                                dp[a][b][c-1]));
                }
            }
        }

        return dp[lenA][lenB][lenC];
    }
}
