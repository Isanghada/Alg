package _2023._202309;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12942
// - 동적 프로그래밍!!!!!!!
// - 하나씩 각 구간을 계산해나가는 방식!
public class _09_Solution_1 {
    public int solution(int[][] matrix_sizes) {
        // 행렬 개수
        int N = matrix_sizes.length;

        // dp 초기화
        int[][] dp = new int[N][N];
        // - 최대값으로 초기화
        for(int r = 0; r < N; r++){
            Arrays.fill(dp[r], Integer.MAX_VALUE);
        }

        // 가능한 모든 경우를 확인
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N - i; j++){
                // 계산 횟수를 확인할 범위
                int a = j;
                int b = j+i;
                // 같은 값일 경우 0으로 입력
                if(a == b) dp[a][b] = 0;
                else{
                    // a, b 범위에서 2부분으로 나누어 최소값 계산.
                    // - (a, k까지 계산 횟수)A + (k+1, b까지 계산 횟수)B + (A, B 행렬의 연산 횟수)
                    // - (A, B 행렬의 연산 횟수) = a의 행 * k의 열(or k+1의 행) * b의 열
                    for(int k = a; k < b; k++){
                        dp[a][b] = Math.min(dp[a][b], dp[a][k] + dp[k+1][b] + matrix_sizes[a][0] * matrix_sizes[k][1] * matrix_sizes[b][1]);
                    }
                }
            }
        }
        
        // 최소값 반환
        return dp[0][N-1];
    }
}
