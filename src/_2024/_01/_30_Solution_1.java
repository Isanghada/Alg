package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29704
// - DP : 문제별로 제출 기한 내에 가능한 경우 계산!
public class _30_Solution_1 {
    // 문제 클래스
    public static class Problem{
        int day;    // 소요 시간
        int fine;   // 벌금

        public Problem(int day, int fine){
            this.day = day;
            this.fine = fine;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 문제의 수
        int T = Integer.parseInt(st.nextToken());   // 제출 기한

        // 벌금의 총합
        int totalFine = 0;
        // 문제 배열
        Problem[] problems = new Problem[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            problems[i] = new Problem(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            totalFine += problems[i].fine;
        }

        // DP 초기화 : 각 배열 값을 벌금의 총합으로 설정
        int[][] dp = new int[N+1][T+1];
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], totalFine);

        // 첫 문제부터 가능한 경우 차례로 계산
        for(int idx = 1; idx <= N; idx++){
            for(int day = 1; day <= T; day++){
                // 현재 날짜에 문제를 완료하기 위해 시작할 날짜
                int pastDay = day-problems[idx].day;

                dp[idx][day] = dp[idx-1][day];
                if(pastDay >= 0) dp[idx][day] = Math.min(
                        dp[idx][day],
                        dp[idx-1][pastDay] - problems[idx].fine);
            }
        }

        // 정답 반환
        sb.append(dp[N][T]);
        System.out.println(sb);
    }
}