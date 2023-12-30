package _2023._202309;

// https://school.programmers.co.kr/learn/courses/30/lessons/1832
// - DP 활용. => 왼쪽에서 오는 경우, 위쪽에서 오는 경우로 나누어 계산 후 합산.
public class _06_Solution_1 {
    // MOD 연산을 위한 상수
    private final int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        // dp 초기화
        // - dp[r][c][0] : (r, c-1)에서 (r, c)로 도달할 수 있는 경우 (왼쪽에서 오는 경우)
        // - dp[r][c][1] : (r-1, c)에서 (r, c)로 도달할 수 있는 경우 (위쪽에서 오는 경우)
        int[][][] dp = new int[m][n][2];
        // 시작 지점은 1로 초기화
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        // 첫 열 계산 : 통행금지인 경우 종료
        for(int r = 1; r < m; r++) {
            if(cityMap[r][0] != 1) {
                dp[r][0][1] = dp[r-1][0][1];
            }else break;
        }
        // 첫 행 계산 : 통행금지인 경우 종료
        for(int c = 1; c < m; c++) {
            if(cityMap[0][c] != 1) {
                dp[0][c][0] = dp[0][c-1][0];
            }else break;
        }

        // 모든 좌표 계산
        for(int r = 1; r < m; r++){
            for(int c = 1; c < n; c++){
                // 통행 금지인 경우 패스.
                if(cityMap[r][c] == 1) continue;
                // 왼쪽에서 들어오는 경우 계산
                // - [이전 동작이 왼쪽에서 온 경우] + (자유 이동인 경우 [이전 동작이 위쪽에서 온 경우])
                dp[r][c][0] = (dp[r][c-1][0] + (cityMap[r][c-1] == 0 ? dp[r][c-1][1] : 0)) % MOD;
                // 위쪽에서 들어오는 경우 계산
                // - [이전 동작이 위쪽에서 온 경우] + (자유 이동인 경우 [이전 동작이 왼쪽에서 온 경우])
                dp[r][c][1] = (dp[r-1][c][1] + (cityMap[r-1][c] == 0 ? dp[r-1][c][0] : 0)) % MOD;
            }
        }

        // 도착 지점에 오는 경우의 합 계산
        int answer = (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;

        return answer;
    }
}
