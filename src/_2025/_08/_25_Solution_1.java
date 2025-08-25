package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20157
// - 구현 : DFS를 통해 각 경우 탐색!
public class _25_Solution_1 {
    static int N, M;
    static int[][] BOARD;
    static int[][] DELTA = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기

        // BOARD 초기화
        initBoard();
        
        // BOARD 정보 입력
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            // -1로 거울 표시
            for(int c = 1; c <= M; c++) BOARD[r][c] = -Integer.parseInt(st.nextToken());
        }

        // for(int[] b : BOARD) System.out.println(Arrays.toString(b));

        // row, col : 시작 좌표
        // d : 빛의 방향
        // move : 다음 좌표 방향
        int row = 1, col = 0, d = 0, move = 3;
        while(d < 4){
            // System.out.println(row+", "+col+", "+BOARD[row][col]+" | "+ d);
            // 좌표 방향 갱신
            if(BOARD[row][col] == 0) {
                d++;
                move = (move + 1) % 4;
            }
            // dfs를 통해 탈출 번호 출력
            else sb.append(dfs(row, col, d)).append(" ");
            
            // 시작 좌표 이동
            row += DELTA[move][0];
            col += DELTA[move][1];
        }
        
        // 정답 출력
        System.out.println(sb);
    }

    private static int dfs(int row, int col, int d) {
        int nextRow = row + DELTA[d][0];
        int nextCol = col + DELTA[d][1];
        if(BOARD[nextRow][nextCol] >= 1) return BOARD[nextRow][nextCol];
        else if(BOARD[nextRow][nextCol] == -1) return dfs(nextRow, nextCol, changeDelta(d));
        else return dfs(nextRow, nextCol, d);
    }

    private static int changeDelta(int d) {
        if(d == 0) return 1;
        else if(d == 1) return 0;
        else if(d == 2) return 3;
        else return 2;
    }

    private static void initBoard() {
        BOARD = new int[N+2][M+2];

        for(int r = 1; r <= N; r++){
            BOARD[r][0] = r;
            BOARD[N+1-r][M+1] = N+M+r;
        }

        for(int c = 1; c <= M; c++){
            BOARD[N+1][c] = N + c;
            BOARD[0][M+1-c] = 2 * N + M + c;
        }
    }
}