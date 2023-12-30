package _2023._202304;

import java.util.Deque;
import java.util.LinkedList;

public class _30_Solution_1 {
    // 좌표를 담을 클래스
    public class Point{
        public int row;  // 행 좌표
        public int col;  // 열 좌표
        public int step;  // 이동 횟수

        public Point(int row, int col, int step){
            this.row = row;
            this.col =col;
            this.step = step;
        }

        @Override
        public String toString() {
            return row +", "+col+", "+step;
        }
    }
    public int solution(String[] board) {
        // 정답 초기화
        int answer = -1;

        // 행 길이
        final int ROW = board.length;
        // 열 길이
        final int COL = board[0].length();
        // 이동 방향
        final int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 시작 좌표
        Point start = null;
        // 모든 좌표에서 R 탐색
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(board[i].charAt(j) == 'R'){
                    start = new Point(i, j, 0);
                    break;
                }
            }
            if(start != null) break;
        }

        // BFS 구현을 위한 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 표시 배열
        boolean[][] visited = new boolean[ROW][COL];
        // 초기값 추가
        deque.offer(start);
        visited[start.row][start.col] = true;

        // 덱이 빌 때까지 반복
        // - 다 비어도 도달하지 못하면 갈 수 없는 것이므로 -1 그대로 반환
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.poll();

            // 도착지일 경우 이동 횟수 반환 및 종료
            if(board[cur.row].charAt(cur.col) == 'G'){
                answer = cur.step;
                break;
            }

            // 각 이동 방향으로 이동.
            for(int[] d : DELTA){
                // 새로운 좌표
                Point next = new Point(cur.row, cur.col, cur.step+1);

                // 한 방향으로 이동(범위를 벗어나거나 장애물을 만날 때까지)
                while(true) {
                    next.row += d[0];
                    next.col += d[1];
                    // 범위를 벗어나거나 장애물을 만날 경우 반대 방향으로 이동하여 정상 좌표 획득
                    // - 만나기 직전의 좌표 획득
                    if(next.row < 0 || next.row >= ROW || next.col < 0 || next.col >= COL ||
                            board[next.row].charAt(next.col) == 'D') {
                        next.row -= d[0];
                        next.col -= d[1];
                        break;
                    }
                }

                // 이미 방문한 좌표일 경우 패스
                if(visited[next.row][next.col] || (cur.row == next.row && cur.col == next.col)) continue;
                // 방문 표시
                visited[next.row][next.col] = true;
                // 덱에 추가
                deque.offer(next);
            }
        }

        return answer;
    }
}
