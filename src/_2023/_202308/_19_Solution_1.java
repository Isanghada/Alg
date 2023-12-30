package _2023._202308;

import java.util.Deque;
import java.util.LinkedList;

// https://school.programmers.co.kr/learn/courses/30/lessons/87694
// BFS 알고리즘으로 최단 거리 계산
// - **인접한** 가장자리로만 이동할 수 있음
// - 직사각형 **내부**를 지날 수 없음
// - 조건이 꽤나 복잡하게 되어있는 느낌.
public class _19_Solution_1 {
    // 좌표를 담을 클래스
    class Point{
        public int x;   // x 좌표
        public int y;   // y 좌표
        public int distance;    // 이동 거리
        public Point prev;

        public Point(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.prev = null;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 정답 초기화
        int answer =  Integer.MAX_VALUE;
        
        // BFS를 위해 Deque 사용
        Deque<Point> pointDeque = new LinkedList<>();
        // 방문 확인을 위해 boolean형 2차원 배열 사용
        boolean[][] visited = new boolean[51][51];

        // 초기값 설정
        // - character 좌표, 이동거리 0, 방문 표시
        pointDeque.offer(new Point(characterX, characterY, 0));
        visited[characterX][characterY] = true;
        
        // 이동을 위한 변수 선언
        final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // pointDeque이 빌 때까지 반복
        while(!pointDeque.isEmpty()){
            // 현재 좌표값 반환
            Point cur = pointDeque.poll();

            // item 좌표와 동일하다면 정답 입력 후 종료
            if(cur.x == itemX && cur.y == itemY){
                answer = cur.distance;

                while(true){
//                    System.out.println(cur.x +", " + cur.y + ", " + cur.distance);
                    cur = cur.prev;
                    if(cur == null) break;
                }

                break;
            }

            // 상, 하, 좌, 우 4방향으로 이동하며 탐색
            for(int[] d : DELTA){
                // 새로운 좌표 생성
                Point next = new Point(cur.x + d[0], cur.y + d[1], cur.distance + 1);
                next.prev = cur;
                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 인접한 직사각형의 가장자리가 아닌 경우
                // - 직사각형 내부에 있는 경우
                if(next.x < 0 || next.x > 50 || next.y < 0 || next.y > 50 ||
                        visited[next.x][next.y] ||
                        !isEdge(rectangle, cur, next) ||
                        isInner(rectangle, cur, next)
                ) continue;

                // 방문 좌표 추가 설정
                pointDeque.offer(next);
                visited[next.x][next.y] = true;
            }
        }

        // 정답 반환
        return answer;
    }

    // 가장자리 확인 함수
    // - rectangle : 직사각형의 좌표를 담은 2차원 배열
    // - cur : 현재 좌표를 담을 Point 클래스
    // - next : 다음 좌표를 담을 Point 클래스
    private boolean isEdge(int[][] rectangle, Point cur, Point next) {
        // 모든 직사각형에 대해서 확인
        // - cur, next를 비교하여 같은 직사각형에서 모두 만족한다면 가장자리라는 의미.
        for(int[] r : rectangle){
            // 가로에 대한 검사
            if((r[0] <= cur.x && r[2] >= cur.x && (r[1] == cur.y || r[3] == cur.y)) &&
                    (r[0] <= next.x && r[2] >= next.x && (r[1] == next.y || r[3] == next.y)))
                return true;
            // 세로에 대한 검사
            if((r[1] <= cur.y && r[3] >= cur.y && (r[0] == cur.x || r[2] == cur.x)) &&
                    (r[1] <= next.y && r[3] >= next.y && (r[0] == next.x || r[2] == next.x)))
                return true;
        }
        
        // 가장자리가 아니라면 false 반환
        return false;
    }

    // 직사각형 내부 확인 함수
    // - rectangle : 직사각형의 좌표를 담은 2차원 배열
    // - cur : 현재 좌표를 담을 Point 클래스
    // - next : 다음 좌표를 담을 Point 클래스
    private boolean isInner(int[][] rectangle, Point cur, Point next) {
        // 모든 직사각형에 대해서 확인
        // - cur, next 평균값이 내부에 있는지 확인 => 내부라면 이동하면서 내부를 지난다는 뜻이므로 true 반환
        double avgX = (cur.x + next.x) / 2.0;
        double avgY = (cur.y + next.y) / 2.0;
        for(int[] r : rectangle){
            if(r[0] < avgX && r[2] > avgX && r[1] < avgY && r[3] > avgY) return true;
        }
        
        // 내부가 아니라면 false 반환
        return false;
    }
}
