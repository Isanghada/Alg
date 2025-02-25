package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/17218
// - DP : 1. 이전 결과 중 최대 길이 선택
//        2. 현재 비교하는 문자가 같은 경우 해당 문자를 선택하는 경우와
//           (1)의 경우를 비교하여 최대 길이 선택
//        3. 위의 과정을 반복하며 모든 경우 탐색!
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 계산의 편의를 위해 의미없는 값 추가!
        // - 비교하는 문자의 인덱스를 1부터 시작하기 위함
        String A = "0" + in.readLine(); // 문자열 A
        String B = "0" + in.readLine(); // 문자열 B

        int lenA = A.length() - 1;  // A의 길이
        int lenB = B.length() - 1;  // B의 길이

        // dp 초기화
        String[][] dp = new String[lenA+1][lenB+1];
        for(int a = 0; a <= lenA; a++) Arrays.fill(dp[a], "");

        // A의 첫 문자부터 모든 문자 탐색
        for(int a = 1; a <= lenA; a++){
            // B의 첫 문자부터 모든 문자 탐색
            for(int b = 1; b <= lenB; b++){
                // 이전 결과 중 최대 길이 선택
                if(dp[a-1][b].length() > dp[a][b-1].length()) dp[a][b] = dp[a-1][b];
                else dp[a][b] = dp[a][b-1];

                // A[a], B[b]가 동일한 문자인 경우
                if(A.charAt(a) == B.charAt(b)){
                    // 최대 길이인 경우 갱신!
                    if(dp[a-1][b-1].length() + 1 > dp[a][b].length()) {
                        dp[a][b] = dp[a-1][b-1] + A.charAt(a);
                    }
                }
            }
        }

        // 결과 반환
        sb.append(dp[lenA][lenB]);
        System.out.println(sb);
    }
}
