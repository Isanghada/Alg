package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17845
// - DP : 시간별로 각 과목을 수강할 때 최대 중요도를 차례로 계산!
public class _25_Solution_1 {
    // 과목 클래스
    public static class Subject{
        int value;  // 중요도
        int time;   // 공부 시간
        public Subject(int value, int time){
            this.value = value;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 최대 공부 시간
        int K = Integer.parseInt(st.nextToken());   // 과목 수

        // 과목 정보 입력
        Subject[] subjects = new Subject[K+1];
        for(int i = 1; i <= K; i++){
            st = new StringTokenizer(in.readLine());
            subjects[i] = new Subject(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // dp 초기화
        // dp[k][n] : 1~k번의 과목을 n시간 동안 공부할 때의 최대 중요도!
        int[][] dp = new int[K+1][N+1];
        for(int k = 1; k <= K; k++){
            for(int t = 1; t <= N; t++){
                // k과목을 공부할 수 없는 경우 : 이전 결과 활용
                if(t < subjects[k].time) dp[k][t] = dp[k-1][t];
                else{
                    // k과목을 수강하지 않는 경우와 수강하는 경우 중 최대값 선택!
//                    dp[k][t] = Math.max(dp[k-1][t], dp[k][t-1]);
                    dp[k][t] = Math.max(dp[k-1][t], dp[k-1][t-subjects[k].time]+subjects[k].value);
                    // k
//                    dp[k][t] = Math.max(dp[k][t], dp[k-1][t-subjects[k].time]+subjects[k].value);
                }
            }
        }

        // 정답 출력
        sb.append(dp[K][N]);
        System.out.println(sb);
    }
}