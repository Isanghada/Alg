package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14926
// -
public class _10_Solution_1 {
    static int N;
    static boolean[][] USED;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        USED = new boolean[N+1][N+1];
        for(int n = 1; n <= N; n++) USED[n][n] = true;
        USED[N][1] = true;
        USED[1][N] = true;

        sb.append("a1 ");
        dfs(1);
        sb.append("a1");

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void dfs(int target) {
        for(int n = 1; n <= N; n++){
            if(USED[target][n]) continue;

            USED[target][n] = true;
            USED[n][target] = true;
            sb.append("a").append(n).append(" ");
            dfs(n);
        }
    }
}
