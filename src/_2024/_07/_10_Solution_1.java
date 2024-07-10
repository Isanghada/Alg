package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5582
// - 참고 : https://velog.io/@jkh9615/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-5582-%EA%B3%B5%ED%86%B5-%EB%B6%80%EB%B6%84-%EB%AC%B8%EC%9E%90%EC%97%B4-Java
// - DP : 범위별로 공통 문자열 탐색하며 계산
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        char[] A = (" "+ in.readLine()).toCharArray();
        char[] B = (" "+ in.readLine()).toCharArray();

        // 정답 초기화
        int answer = 0;
        // DP 초기화
        // - dp[a][b] : 첫 문자부터 A[a], B[b]까지만 사용했을 때 최장 공통 길이!
        int[][] dp = new int[A.length][B.length];
        // A의 범위 a
        for(int a = 1; a < A.length; a++){
            // B의 범위 b
            for(int b = 1; b < B.length; b++){
                // A[a], B[b]가 동일한 경우 공통 부분 문자열 길이 증가
                if(A[a] == B[b]) {
                    // - 이전 공통 부분 문자열에 1 증가!
                    dp[a][b] = dp[a-1][b-1]+1;
                    // - 최대값 갱신
                    answer = Math.max(answer, dp[a][b]);
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
