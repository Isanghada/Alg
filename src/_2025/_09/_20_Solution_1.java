package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/17351
// -
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[][] map = new int[N+1][N+1];
        for(int r = 0; r <= N; r++) Arrays.fill(map[r], -1);

        int[][] dp = new int[N+1][N+1];
        for(int r = 1; r <= N; r++){
            String input = in.readLine();
            for(int c = 1; c <= N; c++) {
                char value = input.charAt(c-1);
                if(value == 'M') map[r][c] = 0;
                else if(value == 'O') map[r][c] = 1;
                else if(value == 'L') map[r][c] = 2;
                else if(value == 'A') map[r][c] = 3;

                dp[r][c] = Math.max(check(map[r][c], map[r][c-1], dp[r][c-1]), check(map[r][c], map[r-1][c], dp[r-1][c]));
            }
            // System.out.println(Arrays.toString(map[r]));
        }

        // 정답 출력
        sb.append(dp[N][N] / 4);
        System.out.println(sb.toString());
    }

    private static int check(int cur, int prev, int prevDp) {
        if((cur == (prev + 1) % 4) && (cur == prevDp % 4)) return prevDp + 1;;

        prevDp /= 4;
        prevDp *= 4;

        if(cur == 0) prevDp++;

        return prevDp;
    }
}
