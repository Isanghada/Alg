package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/28471
// - BFS : 도착 지점을 기준으로 역으로 가능한 출발지 탐색
public class _08_Solution_1 {
    // 좌표 클래스
    static class Point{
        int row;    // 행
        int col;    // 열
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 이동 방향!
    static final int[][] DELTA = new int[][]{{1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 보드 크기 
        int N = Integer.parseInt(in.readLine());
        char[][] board = new char[N][N];

        // 시작 지점 초기화
        Point start = null;
        // 보드 정보 입력
        for(int r = 0; r < N; r++){
            String input = in.readLine();
            for(int c = 0; c < N; c++){
                board[r][c] = input.charAt(c);
                // 도착지인 경우 start 입력
                if(board[r][c] == 'F') start = new Point(r, c);
            }
        }

        // 정답 출력
        // - bfs를 통해 가능한 출발지의 수 탐색
        sb.append(getCount(board, N, start));
        System.out.println(sb);
    }

    private static int getCount(char[][] board, int n, Point start) {
        // count 초기화
        int count = 0;

        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[n][n];

        // start 추가
        deque.offerLast(start);
        visited[start.row][start.col] = true;

        while(!deque.isEmpty()){
            // 좌표 반환
            Point cur = deque.pollFirst();

            // 이전 좌표 체크
            for(int[] d : DELTA){
                // 목적지부터 역으로 탐지하므로 이동 방향의 역으로 탐색
                Point past = new Point(cur.row-d[0], cur.col-d[1]);

                // 다음의 경우 패스
                // - 범위를 벗어난 경우
                // - 빈 공간이 아닌 경우
                // - 이미 방문한 경우
                if(past.row < 0 || past.row >= n ||
                        past.col < 0 || past.col >= n ||
                        board[past.row][past.col] != '.' ||
                        visited[past.row][past.col]
                ) continue;

                // count 증가
                count++;
                // 좌표 추가
                deque.offerLast(past);
                visited[past.row][past.col] = true;
            }
        }

        return count;
    }
}
