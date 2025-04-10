package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14585
// - DP : 사탕 바구니에 도착한 순간 모든 사탕을 먹는 것으로 계산!
//        사탕 바구니의 사탕이 1씩 감소하므로 도착까지 걸린 시간을 제외하고 계산
public class _11_Solution_1 {
    static final int MAX = 300; // 최대 좌표
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사탕 바구니 개수
        int M = Integer.parseInt(st.nextToken());   // 사탕 개수

        // DP 초기화
        long[][] dp = new long[MAX+1][MAX+1];
        // 사탕 바구니 위치
        boolean[][] candies = new boolean[MAX+1][MAX+1];

        // 사탕 바구니 정보 입력
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            candies[x][y] = true;
            // 좌표 x, y 중 0이 있는 경우 체크
            if(x == 0 || y == 0) dp[x][y] = Math.max(M - x - y, 0);
        }
        
        // 모든 좌표 탐색
        for(int x = 1; x <= MAX; x++) {
            for (int y = 1; y <= MAX; y++) {
                // 사탕 바구니가 있는 경우 남은 사탕만큼 증가
                if (candies[x][y]) dp[x][y] = Math.max(M - x - y, 0);
                // 이전 위치 중 최대값만큼 증가
                dp[x][y] += Math.max(dp[x - 1][y], dp[x][y - 1]);
            }
        }

        // 정답 출력
        sb.append(dp[MAX][MAX]);
        System.out.println(sb.toString());
    }
}
