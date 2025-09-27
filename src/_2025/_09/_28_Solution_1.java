package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17037
// -
public class _28_Solution_1 {
    static final int MAX = 1000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[MAX+3][MAX+3];
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            dp[x1][y1]++;
            dp[x1][y2]--;
            dp[x2][y1]--;
            dp[x2][y2]++;
        }

        int answer = 0;
        for(int r = 0; r <= MAX; r++){
            // System.out.println(Arrays.toString(dp[r]));
            for(int c = 0; c <= MAX; c++){
                if(r != 0) dp[r][c] += dp[r-1][c];
                if(c != 0) dp[r][c] += dp[r][c-1];
                if(r != 0 && c != 0) dp[r][c] -= dp[r-1][c-1];
                if(dp[r][c] == K) answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
