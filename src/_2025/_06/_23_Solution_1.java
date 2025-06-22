package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15980
// -
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] birds = new int[N+1][M+1];
        int[] sum = new int[M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            birds[n][0] = st.nextToken().equals("L") ? -1 : 1;
            String input = st.nextToken();
            for(int m = 1; m <= M; m++) {
                birds[n][m] = input.charAt(m-1) == '1' ? birds[n][0] : 0;
                sum[m] += birds[n][m];
            }
        }

        int[] answers = new int[] {0, Integer.MAX_VALUE};
        for(int n = 1; n <= N; n++){
            int cur = 0, maxCur = 0;
            for(int m = 1; m <= M; m++){
                cur += sum[m] - birds[n][m];
                maxCur = Math.max(maxCur, Math.abs(cur));
            }
            if(maxCur < answers[1]){
                answers[0] = n;
                answers[1] = maxCur;
            }
        }

        // 정답 출력
        sb.append(answers[0]).append("\n").append(answers[1]);
        System.out.println(sb);
    }
}
