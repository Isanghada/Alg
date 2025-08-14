package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3098
// -
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        final int MAX = 3 * N;
        int[][] floyd = new int[N+1][N+1];
        for(int n = 1; n <= N; n++) {
            Arrays.fill(floyd[n], MAX);
            floyd[n][n] = 1;
        }

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            floyd[A][B] =1;
            floyd[B][A] =1;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j =1; j <= N; j++){
                    if(i == j) continue;
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                    floyd[j][i] = floyd[i][j];
                }
            }
        }

        for(int n = 0 ; n <= N; n++) System.out.println(Arrays.toString(floyd[n]));

        int time = 0;
        int[] answers = new int[N+1];
        for(int i = 1; i <= N; i++){
            for(int j = i + 1; j <= N; j++){
                time = Math.max(time, floyd[i][j] - 1);
                answers[floyd[i][j]-1]++;
            }
        }

        sb.append(time).append("\n");
        for(int t = 1; t <= time; t++) sb.append(answers[t]).append("\n");


        // 정답 출력
        System.out.println(sb.toString());
    }
}

