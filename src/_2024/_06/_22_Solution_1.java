package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2288
// -
public class _22_Solution_1 {
    static int N, M, INIT = -1, LEFT = 0, RIGHT = 1;
    static char[][] BOARD;
    static boolean[][] IS_USED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            inputNM(in);
            if(N + M == 0) break;

            init();
            inputBoard(in);

            sb.append(getMinCount()).append("\n");
        }


        // 정답 출력
        System.out.println(sb);
    }

    private static int getMinCount() {
        int d = INIT;
        int result = 1;
        int m = getIndexOfM(1);

        for(int n = 1; n <= N; n++){
            if(m != 1 && BOARD[n][m-1] == 'S'){
                d = LEFT;
                while(m > 1 && BOARD[n][m-1] == 'S'){
                    m--;
                    result++;
                }
                result++;
                continue;
            }

            if(m < M && BOARD[n][m+1] == 'S'){
                if(d == INIT) result--;
                else if(d == LEFT) result -= 2;

                d = RIGHT;
                while(m < M && BOARD[n][m+1] == 'S'){
                    m++;
                    result++;
                }
                result++;
                continue;
            }
            result++;
        }

        return (d == LEFT ? result - 2 : result - 1);
    }

    private static int getIndexOfM(int n) {
        int index = 0;
        for(int m = 1; m <= M; m++){
            if(BOARD[n][m] == 'S'){
                index = m;
                break;
            }
        }
        return index;
    }

    private static void inputBoard(BufferedReader in) throws Exception{
        for (int n = 1; n <= N; n++) {
            String input = (" " + in.readLine());
            for(int m = 1; m <= M; m++) {
                BOARD[n][m] = input.charAt(m);
            }
        }
    }

    private static void inputNM(BufferedReader in) throws Exception {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private static void init() {
        BOARD = new char[N+2][M+2];
        for(int n = 0; n < N+2; n++) Arrays.fill(BOARD[n], ' ');
        IS_USED = new boolean[N+2][M+2];
    }
}
