package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1230
// - DP를 활용해 해결
// - 매 인덱스마다 새로운 값을 추가, 미추가일 경우의 횟수를 계산
public class _18_Solution_1 {
    // 최대 삽입 횟수 설정 : 문자열이 최대 1000자 이므로 1000이상으로 설정
    private static final int INF = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 문자열 입력 : char[]으로 형변환
        char[] a = in.readLine().toCharArray(); // 기존 문자열
        char[] b = in.readLine().toCharArray(); // 타겟 문자열
        int lenA = a.length;    // 문자열 길이
        int lenB = b.length;    // 문자열 길이

        // 기존이 타겟보다 길 경우 -1 반환
        if(lenA > lenB) sb.append(-1);
        else{
            // dp 초기화
            // - dp[i][j][flag] : 문자열 a의 i인덱스에 문자열 b의 j인덱스 추가 여부에 따른 문자열 거리
            //   - flag 0 : 문자열 추가 X
            //   - flag 1 : 문자열 추가 O
            int[][][] dp = new int[lenA+1][lenB+1][2];
            dp[0][0][0] = 0;
            dp[0][0][1] = INF;

            for(int i = 1; i <= lenB; i++){
                dp[0][i][0] = INF;
                dp[0][i][1] = 1;
            }

            // a의 첫 인덱스부터 차례로 모든 인덱스 계산
            for(int i = 0; i < lenA; i++){
                // i보다 작은 인덱스들은 이미 앞서 사용했으므로 INF로 초기화
                for(int j = 0; j <= i; j++) dp[i+1][j][0] = dp[i+1][j][1] = INF;
                // b의 i인덱스부터 모든 인덱스 계산
                for(int j = i; j < lenB; j++){
                    // i, j가 동일한 경우 : 이전 값중 최소값 선택
                    if(a[i] == b[j]) dp[i+1][j+1][0] = Math.min(dp[i][j][0], dp[i][j][1]);
                    // 동일하지 않은 경우 : 추가가 필수이므로 flag 0은 INF로 설정
                    else dp[i+1][j+1][0] = INF;
                    // 추가하는 경우 (이전 flag 0의 횟수 + 1), (이전 flag 1의 횟수) 중 최소값 선택
                    dp[i+1][j+1][1] = Math.min(dp[i+1][j][0]+1, dp[i+1][j][1]);
                }
            }
            // 마지막 값들 중 최소값 선택
            int result = Math.min(dp[lenA][lenB][0], dp[lenA][lenB][1]);
            // 값이 최대값을 넘어가면 -1, 아니라면 result 반환
            sb.append(result >= INF ? -1 : result);
        }

        // 정답 출력
        System.out.println(sb);
    }
}
