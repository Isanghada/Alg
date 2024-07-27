package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14712
// - 백트래킹 : 가능한 모든 경우 탐색!
public class _27_Solution_1 {
    public static int N, M, SIZE, ANSWER;
    public static boolean[][] VISITED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        SIZE = N * M;

        ANSWER = 0;
        VISITED = new boolean[N][M];
        dfs(0, 0);

        // 정답 반환
        sb.append(ANSWER);
        System.out.println(sb);
    }

    private static void dfs(int step, int start) {
        if(isPossible(step)) ANSWER++;
//        if(step == SIZE) return;
        for(int point = start; point < SIZE; point++){
            int r = point / M;
            int c = point % M;
            if(VISITED[r][c]) continue;

            VISITED[r][c] = true;
            dfs(step+1, point+1);
            VISITED[r][c] = false;
        }

    }

    private static boolean isPossible(int step) {
        if(step < 4) return true;
        for(int r = 0; r < N-1; r++){
            for(int c = 0; c < M-1; c++){
                if(VISITED[r][c] && VISITED[r][c+1] &&
                VISITED[r+1][c] && VISITED[r+1][c+1]) return false;
            }
        }
        return true;
    }
}
