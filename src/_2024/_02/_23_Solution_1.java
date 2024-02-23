package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1553
// - 백트래킹 : 가능한 모든 경우 탐색!
public class _23_Solution_1 {
    public static int ANSWER;
    public static int[][] BOARD, DELTA = new int[][]{{0, 1}, {1, 0}};
    public static boolean[][] VISITED, USED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        BOARD = new int[8][7];          // 보드 초기화
        VISITED = new boolean[8][7];    // 방문 배열
        USED = new boolean[8][7];       // 사용 도미노

        // 보드 입력
        for(int r = 0; r < 8; r++){
            String board = in.readLine();
            for(int c = 0; c < 7; c++) BOARD[r][c] = board.charAt(c) - '0';
        }

        // 정답 초기화
        ANSWER = 0;
        // 모든 인덱스 차례로 탐색하며 도미노 체크
        backtracking(0);

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }
    private static void backtracking(int idx) {
        // 모든 인덱스를 확인한 경우 : 정답 증가
        if(idx >= 56) ANSWER++;
        else{
            int row = idx / 7;  // 현재 행 좌표
            int col = idx % 7;  // 현재 열 좌표
            // 이미 방문한 경우 다음 인덱스로 이동
            if(VISITED[row][col]) backtracking(idx+1);
            else{
                // 도미노 체크
                for(int[] d : DELTA){
                    int nextRow = row + d[0];   // 연결된 도미노 행 좌표
                    int nextCol = col + d[1];   // 연결된 도미노 열 좌표

                    // 아래의 경우 패스
                    // - 범위를 벗어난 경우
                    // - 이미 사용한 도미노인 경우
                    // - 이미 방문한 경우
                    if( nextRow < 0 || nextRow >= 8 || nextCol < 0 || nextCol >= 7 ||
                            USED[BOARD[row][col]][BOARD[nextRow][nextCol]] ||
                            VISITED[nextRow][nextCol]
                    ) continue;

                    // 도미노 사용 체크 : 회전할 수 있으므로 양방향 체크
                    USED[BOARD[row][col]][BOARD[nextRow][nextCol]] = true;
                    USED[BOARD[nextRow][nextCol]][BOARD[row][col]] = true;
                    // 방문 체크 : 도미노 사용은 방문으로 체크
                    VISITED[row][col] = true;
                    VISITED[nextRow][nextCol] = true;
                    // 다음 인덱스 탐색
                    backtracking(idx+1);
                    // 도미노 사용 해제
                    USED[BOARD[row][col]][BOARD[nextRow][nextCol]] = false;
                    USED[BOARD[nextRow][nextCol]][BOARD[row][col]] = false;
                    // 방문 해제
                    VISITED[row][col] = false;
                    VISITED[nextRow][nextCol] = false;
                }
            }
        }
    }
}
