package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/9251
// - DP 활용 LCS!!
public class _05_Solution_1 {
    private static int[][] dp;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // A 문자열 char배열 변환 : 인덱스를 1부터 시작하기 위해 공백 추가!
        char[] A = (" "+in.readLine()).toCharArray();
        // B 문자열 char배열 변환 : 인덱스를 1부터 시작하기 위해 공백 추가!
        char[] B = (" "+in.readLine()).toCharArray();

        // DP 초기화
        // - 계산의 편의를 위해 인덱스를 1부터 시작!
        int[][][] dp = new int[2][B.length][A.length];
        for(int b = 1; b < B.length; b++){
            for(int a = 1; a < A.length; a++){
                // B[b]와 A[a]를 비교하지 않는 경우 중 최대값 계산
                dp[0][b][a] = Math.max(dp[0][b][a-1], dp[0][b-1][a]);
                // B[b]와 A[a]를 비교하지 않는 경우 중 최대값 계산
                dp[1][b][a] = Math.max(dp[1][b][a-1], dp[1][b-1][a]);
                // A[a]를 B[b]로 강제로 변경하는 경우 중 최대값 계산
                dp[1][b][a] = Math.max(dp[1][b][a], dp[0][b-1][a-1] + 1);
                // B[b]와 A[a]가 동일한 경우
                // - DP(b-1, a-1) + 1 중 최대값 계산
                if(B[b] == A[a]) {
                    dp[0][b][a] = Math.max(dp[0][b][a], dp[0][b-1][a-1] + 1);
                    dp[1][b][a] = Math.max(dp[1][b][a], dp[1][b-1][a-1] + 1);
                }
            }
        }
        sb.append(Math.max(dp[0][B.length-1][A.length-1],dp[1][B.length-1][A.length-1]))
                .append("\n").append(dp[0][B.length-1][A.length-1])
                .append("\n").append(dp[1][B.length-1][A.length-1]);

        // 값을 -1로 초기화
//        for(int b = 1; b < B.length; b++){
//            for(int a = 1; a < A.length; a++)
//                dp[b][a] = -1;
//        }
//
//        // 정답 반환 : lcs 함수를 통해 재귀 방식으로 계산
//        sb.append(lcs(A, B, A.length-1, B.length-1));
//        for(int[] d : dp) System.out.println(Arrays.toString(d));

        System.out.println(sb);

    }

    // DP 함수 : 재귀를 통해 Top Down 방식으로 계산
    // - A : A 문자열 char 배열
    // - B : B 문자열 char 배열
    // - a : A의 인덱스
    // - b : B의 인덱스
    private static int lcs(char[] A, char[] B, int a, int b){
        // 범위를 벗어날 경우 0 반환
        if(a < 0 || b < 0) return 0;
        // 값이 있는 경우 : 해당 값 반환
        if(dp[b][a] != -1) return dp[b][a];
        // B[b], A[a]가 동일한 경우 : (a-1, b-1)의 최대값 + 1 반환
        if(B[b] == A[a]) return dp[b][a] = lcs(A, B, a-1, b-1) + 1;
        // 동일하지 않은 경우 : (a-1), (b-1)인 경우 중 최대값 반환
        else return dp[b][a] = Math.max(lcs(A, B, a-1, b), lcs(A, B, a, b-1));
    }
}
