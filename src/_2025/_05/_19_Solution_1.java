package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30461
// - 누적합 : 각 좌표별 행, 대각선 방향으로 누적합을 계산!
public class _19_Solution_1 {
    static final int[][] DELTA = new int[][]{{1, 1}, {1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++) map[n][m] = Integer.parseInt(st.nextToken())
                                                    + map[n-1][m];
        }

        for(int n = 1; n <= N; n++){
            for(int m = 1; m <= M; m++) map[n][m] += map[n-1][m-1];
        }

        for(int[] m : map) System.out.println(Arrays.toString(m));

        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int W = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            sb.append(map[W][P]).append("\n");
        }


        // 정답 반환
        System.out.println(sb);
    }
}
