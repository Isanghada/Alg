package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14550
// - DP를 활용해 해결 : 각 턴별로 가능한 경우 계산
public class _04_Solution_1 {
    public static final int MIN_VALUE = -1000000;   // 최소값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202312/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 반복
        while(true){
            // 입력값 설정
            st = new StringTokenizer(in.readLine());
            // 별 사이의 칸 수
            int N = Integer.parseInt(st.nextToken());
            // 0인 경우 종료
            if(N == 0) break;

            // 주사위 범위
            int S = Integer.parseInt(st.nextToken());
            // 턴 수
            int T = Integer.parseInt(st.nextToken());

            // 위치별 코인 배열
            List<Integer> coin = new ArrayList<>();
            while(coin.size() < N){
                st = new StringTokenizer(in.readLine());
                while(st.hasMoreTokens()) coin.add(Integer.parseInt(st.nextToken()));
            }

            // dp 초기화
            int[][] dp = new int[T+1][N+1];
            // - MIN_VALUE로 초기화
            for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], MIN_VALUE);
            // - 1번째인 경우 가능한 위치 입력
            for(int i = 0; i < S; i++) dp[1][i] = coin.get(i);

            // 가능한 모든 경우 계산
            // - count : 턴 수
            // - point : 위치
            // - t : 주사위 숫자
            for(int count = 1; count < T; count++){
                for(int point = 0; point < N; point++){
                    if(dp[count][point] == MIN_VALUE) continue;
                    for(int t = 1; t <= S; t++){
                        // 다음 턴의 경우 계산
                        int nextCount = count+1;
                        int nextPoint = point + t;
                        // 별을 지나갈 경우 : 값을 최대값으로 변경
                        if(nextPoint >= N) dp[nextCount][N] = Math.max(dp[nextCount][N], dp[count][point]);
                        // 범위 내일 경우 : 새로운 값과 원래 값 중 최대값으로 변경
                        else{
                            dp[nextCount][nextPoint] = Math.max(dp[nextCount][nextPoint], dp[count][point] + coin.get(nextPoint));
                        }
                    }
                }
            }
            // 정답 초기화
            int answer = dp[1][N];
            // 별을 지나는 경우 중 최대값으로 변경
            for(int d[] : dp) answer = Math.max(answer, d[N]);
            // 정답 출력
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
