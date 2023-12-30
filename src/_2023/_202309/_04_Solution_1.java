package _2023._202309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15486
// - Bottom-Up 방식의 DP
// - 1일차부터 얻을 수 있는 최대값 계산
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        // System.setIn(new FileInputStream("src/_2023._202309/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 일할 수 있는 날짜 입력
        int N = Integer.parseInt(in.readLine());
        
        // 상담 일정 입력
        int[][] work = new int[N+1][2];
        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            // 상담 일수
            work[i][0] = Integer.parseInt(st.nextToken());
            // 상담 금액
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        // DP 초기화
        int[] dp = new int[N+2];
        // 1일차부터 계싼
        for(int day = 1; day <= N; day++){
            // 상담이 끝나는 날 계산
            int next = day + work[day][0];
            // 현재 금액과 이전 금액 중 최대값으로 변경!
            dp[day] = Math.max(dp[day-1], dp[day]);
            // 상담 일자를 넘어서면 패스
            if(next > N + 1) continue;
            // 상담이 끝나는 날의 금액 계산 : 최대값으로 변경
            dp[next] = Math.max(dp[next], dp[day] + work[day][1]);
        }

        // 정답 초기화
        int answer = 0;
        // dp에서 가장 큰 값 탐색
        for(int value : dp) answer = Math.max(answer, value);

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
