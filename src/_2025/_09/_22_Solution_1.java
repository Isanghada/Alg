package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14500
// -
public class _22_Solution_1 {
    private static class Tetromino{
        int[][][] tetromino;
        int type;
        public Tetromino(int[][][] tetromino, int type){
            this.tetromino = tetromino;
            this.type = type;
        }
    }
    static final int TYPE = 5;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];

        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++) map[n][m] = Integer.parseInt(st.nextToken());
        }

        Tetromino[] tetrominos = new Tetromino[TYPE];
        tetrominos[0] = new Tetromino(new int[][][]{
                {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
                {{0, 0}, {1, 0}, {2, 0}, {3, 0}}},
                2);
        tetrominos[1] = new Tetromino(new int[][][]{
                {{0, 0}, {0, 1}, {1, 0}, {1, 1}}},
                1);
        tetrominos[2] = new Tetromino(new int[][][]{
                {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
                {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
                {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
                {{0, 0}, {1, 0}, {1, -1}, {1, -2}},
                {{0, 0}, {0, -1}, {1, -1}, {2, -1}},
                {{0, 0}, {0, -1}, {0, -2}, {-1, -2}},
                {{0, 0}, {1, 0}, {2, 0}, {2, -1}}},
                8);
        tetrominos[3] = new Tetromino(new int[][][]{
                {{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
                {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
                {{0, 0}, {1, 0}, {1, -1}, {2, -1}}},
                4);
        tetrominos[4] = new Tetromino(new int[][][]{
                {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
                {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
                {{0, 0}, {1, 0}, {2, 0}, {1, -1}}},
                4);

        // 정답 출력
        sb.append(bfs(tetrominos, map, N, M));
        System.out.println(sb);
    }

    private static int bfs(Tetromino[] tetrominos, int[][] map, int n, int m) {
        int result = 0;

        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= m; c++){
                for(Tetromino t : tetrominos){
                    for(int[][] delta : t.tetromino){
                        boolean flag = true;
                        int sum = 0;
                        for(int[] d : delta){
                            int nextR = r + d[0];
                            int nextC = c + d[1];

                            if(nextR < 1  || nextR > n ||
                                    nextC < 1 || nextC > m){
                                flag = false;
                                break;
                            }
                            sum += map[nextR][nextC];
                        }
                        if(flag) result = Math.max(result, sum);
                    }
                }
            }
        }

        return result;
    }
}
