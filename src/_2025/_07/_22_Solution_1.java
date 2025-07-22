package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17492
// - 백트래킹 : 바둑알을 기준으로 모든 경우의 수를 실행하여 1개의 바둑알을 남길 수 있는지 탐색
public class _22_Solution_1 {
    // 좌표 클래스
    static class Point{
        int row;    // 행
        int col;    // 열
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // N : 바둑판 크기
    // STONE_COUNT : 바둑알 개수
    static int N, STONE_COUNT;
    // BOARD : 바둑판 배열
    static int[][] BOARD;
    // DELTA : 이동 변수
    static int[][] DELTA = new int[][]{
            {0, 1}, {0, -1},    // 가로
            {1, 0}, {-1, 0},    // 세로
            {1, 1}, {-1, -1},   // 대각선
            {1, -1}, {-1, 1}
    };
    // 바둑알 리스트
    static List<Point> STONE_LIST;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        BOARD = new int[N][N];
        STONE_LIST = new ArrayList<>();

        StringTokenizer st = null;
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++){
                BOARD[r][c] = Integer.parseInt(st.nextToken());
                if(BOARD[r][c] == 2) {
                    STONE_LIST.add(new Point(r, c));
                    STONE_COUNT++;
                }
            }
        }

        // 정답 출력
        // - 백트래킹을 통해 가능한 모든 경우 탐색
        sb.append(backtracking(1) ? "Possible" : "Impossible");
        System.out.println(sb);
    }

    private static boolean backtracking(int step) {
        // 1개의 바둑알만 남은 경우 true 반환
        if(step == STONE_COUNT) return true;
        // 모든 바둑알 체크
        for(int i = 0; i < STONE_COUNT; i++){
            Point cur = STONE_LIST.get(i);
            // 바둑알이 있는 경우만 탐색!
            if(BOARD[cur.row][cur.col] == 2){
                // 위치 이동 가능 여부 확인
                for(int[] d : DELTA){
                    // 제거할 바둑알
                    Point remove = new Point(cur.row+d[0], cur.col+d[1]);
                    // 이동할 위치
                    Point next = new Point(remove.row+d[0], remove.col+d[1]);

                    // 아래의 경우 패스
                    // - 제거할 바둑알이 없는 경우
                    // - 이동할 위치가 없는 경우
                    if(remove.row < 0 || remove.row >= N  ||
                            remove.col < 0 || remove.col >= N ||
                            BOARD[remove.row][remove.col] != 2 ||
                            next.row < 0 || next.row >= N ||
                            next.col < 0 || next.col >= N ||
                            BOARD[next.row][next.col] != 0
                    ) continue;

                    // 바둑알 정보 갱신!
                    BOARD[cur.row][cur.col] = 0;
                    BOARD[remove.row][remove.col] = 0;
                    BOARD[next.row][next.col] = 2;
                    STONE_LIST.set(i, next);
                    if(backtracking(step+1)) return true;
                    // 바둑알 정보 복구!
                    BOARD[cur.row][cur.col] = 2;
                    BOARD[remove.row][remove.col] = 2;
                    BOARD[next.row][next.col] = 0;
                    STONE_LIST.set(i, cur);
                }
            }
        }
        return false;
    }
}
