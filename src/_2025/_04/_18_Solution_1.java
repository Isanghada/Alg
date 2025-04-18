package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31671
// - DP : 가능한 모든 경우의 수 중 최대값 탐색
public class _18_Solution_1 {
    // 이동 변수
    static final int[][] DELTA = new int[][]{{1, 1}, {1, -1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 오름의 높이
        int M = Integer.parseInt(st.nextToken());   // 피해야하는 지점(선생님 위치) 개수

        // 간편한 계산을 위해 좌표 1부터 시작!
        int limitX = N*2 + 1; // 너비
        int limitY = N + 1;   // 높이

        // dp 초기화
        // dp[x][y] : (x, y) 좌표에 도달할 때 방문한 최대 높이
        int[][] dp = new int[limitX+1][limitY+2];
        // - 시작 위치 높이
        dp[1][1] = 1;

        // 선생님 지점 체크
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken())+1;
            int y = Integer.parseInt(st.nextToken())+1;
            // 선생님이 계신 지점은 -1로 표현
            dp[x][y] = -1;
        }

        // x 좌표를 기준으로 탐색
        for(int x = 1; x <= limitX; x++){
            // 모든 y좌표 탐색
            for(int y = 1; y <= limitY; y++){
                // (x, y)가 선생님 좌표인 경우 패스
                if(dp[x][y] == -1) continue; 
                for(int[] d : DELTA){
                    int pastX = x - d[0];
                    int pastY = y - d[1];
                    // 이전 좌표에 도착하지 못한 경우 패스
                    if(dp[pastX][pastY] <= 0) continue;
                    // 현재 높이, 이전 높이 중 최대값 선택
                    dp[x][y] = Math.max(y, dp[pastX][pastY]);
                }
            }
            // System.out.println(Arrays.toString(dp[x]));
        }

        // 도착하지 못한 경우 -1 반환
        if(dp[limitX][1] <= 0) sb.append(-1);
        // 도착 지점에서의 (최대 높이 - 1)
        // - 좌표를 1 증가시켜 시작했으므로 문제에 맞게 1 감소
        else sb.append(dp[limitX][1] - 1);

        // 정답 출력
        System.out.println(sb.toString().trim());
    }
}
