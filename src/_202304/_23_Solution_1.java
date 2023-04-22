package _202304;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// 전체 경우 탐색
public class _23_Solution_1 {
    // 좌표를 담을 클래스
    public class Point{
        public int row; // 행
        public int col; // 열

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public int[] solution(String[] maps) {
        // 정답의 개수가 유동적이므로 AraayList로 정답 입력.
        ArrayList<Integer> answer = new ArrayList();

        final int ROW = maps.length;  // 행 사이즈
        final int COL = maps[0].length(); // 열 사이즈
        // 이동할 수 있는 방향(상, 하, 좌, 우)
        final int[][] DELTA = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 방문한 좌표인지 확인할 배열
        boolean[][] visited = new boolean[ROW][COL];
        // 모든 좌표를 돌며 BFS로 각 섬에서 최대로 머물 수 있는 계산
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COL; c++){
                // X(바다)이거나 이미 방문한 경우 패스
                if(maps[r].charAt(c) == 'X' || visited[r][c]) continue;

                // 이번 섬에서 머물 수 있는 날짜
                int count = 0;

                // BFS로 계산하기 deque 생성
                Deque<Point> deque = new LinkedList<>();
                // 현재 좌표 추가
                deque.add(new Point(r, c));
                // 방문 표시
                visited[r][c] = true;
                // 덱이 빌 때까지 반복
                while(!deque.isEmpty()){
                    // 가장 먼저 방문한 좌표 반환
                    Point cur =  deque.poll();
                    // 해당 값만큼 추가
                    count += maps[cur.row].charAt(cur.col) - '0';

                    // 상하좌우로 이동이 가능한지 확인.
                    for(int[] d : DELTA){
                        // 새로운 좌표 계산
                        Point newPoint = new Point(cur.row+d[0], cur.col+d[1]);

                        // 아래의 경우 패스
                        // - 행, 열 범위를 벗어나는 경우
                        // - 다음 좌표 값이 X(바다)인 경우
                        // - 이미 방문한 섬일 경우
                        if(newPoint.row < 0 || newPoint.row >= ROW ||
                                newPoint.col < 0 || newPoint.col >= COL ||
                                maps[newPoint.row].charAt(newPoint.col) == 'X' ||
                                visited[newPoint.row][newPoint.col]
                        ) continue;

                        // 새로운 좌표인 경우 방문표시, 덱에 추가
                        visited[newPoint.row][newPoint.col] = true;
                        deque.offer(newPoint);
                    }
                }
                // 정답에 날짜 추가
                answer.add(count);
            }
        }

        // 만약, 정답이 0개라면 -1 추가
        if(answer.size() == 0) answer.add(-1);
        // 정답을 오름차순으로 정렬
        answer.sort((e1, e2) -> e1 - e2);

        // array로 변환하여 반환
        return answer.stream().mapToInt(e -> e).toArray();
    }
}
