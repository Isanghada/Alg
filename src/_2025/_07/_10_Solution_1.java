package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13269
// -
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] answer = new int[N+1][M+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < M; j++) answer[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine());
        for(int j = 0; j < M; j++) answer[N][j] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i = N-1; i >= 0; i--) answer[i][M] = Integer.parseInt(st.nextToken());


        if(check(answer, N, M)){
            for(int i = 0; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(answer[i][j] == 1) answer[i][j] = Math.min(answer[i][M], answer[N][j]);
                    sb.append(answer[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }else sb.append(-1);


        // 정답 출력
        System.out.println(sb.toString());
    }

    private static boolean check(int[][] answer, int n, int m) {
        for(int j = 0; j < m; j++){
            boolean flag = false;
            int target = answer[n][j];
            for(int i = 0; i < n; i++){
                int cur = Math.min(answer[n][j], answer[i][m]);
                if(answer[i][j] == 1 && target == cur) {
                    if(target != 0) flag = true;
                }
            }
            if(!flag) return false;
        }

        for(int i = 0; i < n; i++){
            boolean flag = false;
            int target = answer[i][m];
            for(int j = 0; j < m; j++){
                int cur = Math.min(answer[n][j], answer[i][m]);
                if(answer[i][j] == 1 && target == cur) {
                    if(target != 0) flag = true;
                }
            }
            if(!flag) return false;
        }

        return true;
    }
}
