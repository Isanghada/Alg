package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12869
// - DP : 탑다운 방식으로 가능한 모든 경우 탐색!
//        데미지를 줄 수 있는 방식이 6가지이므로 가능한 경우 체크!
public class _05_Solution_1 {
    // 데미지 방식!
    public static int[][] DAMAGES = new int[][]{
            {9, 3, 1},
            {9, 1, 3},
            {3, 1, 9},
            {3, 9, 1},
            {1, 3, 9},
            {1, 9, 3}
    };
    // 최대 SCV 수
    public static int SIZE = 3;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // SCV수
        int N = Integer.parseInt(in.readLine());
        // SCV 정보
        int[] scvArr = new int[SIZE];

        // SCV 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) scvArr[i] = Integer.parseInt(st.nextToken());

        // dp 초기화
        int[][][] dp = new int[scvArr[0]+1][scvArr[1]+1][scvArr[2]+1];
        for(int i = 0; i <= scvArr[0]; i++){
            for(int j = 0; j <= scvArr[1]; j++) {
                for (int k = 0; k <= scvArr[2]; k++) {
                    dp[i][j][k] = 100_000;
                }
            }
        }

        // 정답 반환
        sb.append(getDP(dp, scvArr[0], scvArr[1], scvArr[2]));
        System.out.println(sb);
    }

    public static int getDP(int[][][] dp, int hp1, int hp2, int hp3){
        if(dp[hp1][hp2][hp3] != 100_000) return dp[hp1][hp2][hp3];
        else{
            int result = 100_000;
            if(hp1 == 0 && hp2 == 0 && hp3 == 0) result = 0;
            else{
                for(int[] damage : DAMAGES){
                    int nextHp1 = Math.max(hp1-damage[0], 0);
                    int nextHp2 = Math.max(hp2-damage[1], 0);
                    int nextHp3 = Math.max(hp3-damage[2], 0);

                    result = Math.min(result, getDP(dp, nextHp1, nextHp2, nextHp3)+1);
                }
            }
            return dp[hp1][hp2][hp3] = result;
        }
    }
}
