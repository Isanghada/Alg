package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28017
// - DP : 이전 결과에서 다음 결과를 계산하여 최소값 탐색
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202312/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 게임 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 회차의 수
        int M = Integer.parseInt(st.nextToken());   // 무기의 수

        // DP 초기화
        int[] dp = new int[M];
        // 게임 회차 만큼 반복
        while(N-- > 0){
            // 회차별 무기 정보 입력
            st = new StringTokenizer(in.readLine());
            // 현재 회차 계산을 위한 배열 초기화
            int[] cur = new int[M];
            // 각 무기별 이번 회차의 결과 계산
            for(int selected = 0; selected < M; selected++){
                // 이전 결과 중 최소값 계산
                int pastMin = Integer.MAX_VALUE;
                for(int past = 0; past < M; past++){
                    // 현재 회차와 이전 회차가 같을 경우 패스
                    if(past == selected) continue;
                    pastMin = Math.min(pastMin, dp[past]);
                }
                // 결과 계산!
                cur[selected] = Integer.parseInt(st.nextToken())+pastMin;
            }
            // DP 갱신
            dp = cur;
        }

        // 결과 반환
        // - 마지막 회차에서 최소값 반환
        sb.append(Arrays.stream(dp).min().getAsInt());
        System.out.println(sb);
    }
}
