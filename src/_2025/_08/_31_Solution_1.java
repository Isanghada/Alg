package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5913
// - BFS : (1, 1)을 시작으로 절반을 이동한 후 (5, 5)까지 이동가능한지 체크
public class _31_Solution_1 {
    static int ANSWER, DISTANCE, K;
    static boolean[][] BOARD;
    static final int MAX = 5;
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(in.readLine());
        BOARD = new boolean[MAX+1][MAX+1];


        StringTokenizer st = null;
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(in.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            BOARD[i][j] = true;
        }

        ANSWER = 0;
        DISTANCE = (MAX * MAX - K) / 2;

        bfs(1, 1, 0, false);

        // 정답 반환
        sb.append(ANSWER);
        System.out.println(sb);
    }

    private static void bfs(int x, int y, int step, boolean flag) {
        if(step == DISTANCE){
            if(!flag) {
                //System.out.println(x+", "+y+"============");
                bfs(x, y, 0, true);
            }
            else if(x == MAX && y == MAX) ANSWER++;
        }else{
            // System.out.println(x+", "+y+"----"+step+" / "+DISTANCE);
            BOARD[x][y] = true;
            for(int[] d : DELTA){
                int nextX = x + d[0];
                int nextY = y + d[1];
                if(nextX < 1 || nextX > MAX ||
                        nextY < 1 || nextY > MAX ||
                        BOARD[nextX][nextY]
                ) continue;

                bfs(nextX, nextY, step+1, flag);
            }
            BOARD[x][y] = false;
        }
    }
}