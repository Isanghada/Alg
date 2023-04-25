package _202304;

import java.util.Deque;
import java.util.LinkedList;

public class _25_Solution_1 {
    // 좌표와 이동 시간을 담을 클래스
    public class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int step;   // 걸린 시간

        public Point(int row, int col, int step){
            this.row=row;
            this.col=col;
            this.step=step;
        }
    }
    public int solution(String[] maps) {
        int answer = -1;    // 탈출할 수 없는 경우로 초기화
        // 맵의 행 길이
        final int ROW = maps.length;
        // 맵의 열 길이
        final int COL = maps[0].length();
        // 이동가능한 방향
        final int[][] DELTA = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        boolean flagS = false;  // 출발 지점 플래그
        boolean flagL = false;  // 레버 도착 플래그

        // BFS로 탐색하기 위해 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 여부를 담을 행렬
        boolean[][] visited = new boolean[ROW][COL];
        // 출발 좌표
        Point start = null;
        // 모든 좌표를 돌며 출발지 확인
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(maps[i].charAt(j) == 'S') {
                    start = new Point(i, j, 0);
                    flagS = true;
                    break;
                }
            }
            if (flagS) break;
        }

        // 출발지로 초기화
        // 방문 표시
        visited[start.row][start.col] = true;
        // 덱에 추가
        deque.offer(start);
        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.poll();

            // 레버일 경우 방문 행렬 초기화
            // - 지나왔던 길로 가는 것이 더 빠를 수도 있기 때문이다.
            if(maps[cur.row].charAt(cur.col) == 'L'){
                flagL = true;   // 레버 플래그 true
                visited = new boolean[ROW][COL];    // 방문 행렬 초기화
                visited[cur.row][cur.col] = true;   // 레버 좌표만 true로 설정
                deque.clear();  // 덱 초기화
            }

            // 레버를 방문한 뒤 출구로 간 경우 정답 업데이트 후 종료
            if(flagL && maps[cur.row].charAt(cur.col) == 'E'){
                return cur.step;
            }

            // 상, 하, 좌, 우로 이동 가능한 좌표 탐색
            for(int[] d : DELTA){
                // 새로운 좌표
                Point next = new Point(cur.row+d[0], cur.col +d[1], cur.step + 1);

                // 아래의 경우 패스
                // - 맵의 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 벽인 경우
                if(next.row < 0 || next.row >= ROW || next.col < 0 || next.col >= COL ||
                        visited[next.row][next.col] ||
                        maps[next.row].charAt(next.col) == 'X'
                ) continue;

                // 방문 표시
                visited[next.row][next.col] = true;
                // 덱에 추가
                deque.offer(next);
            }
        }

        return answer;
    }
}
