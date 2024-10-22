package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5427
// - BFS : 불, 사람이 이동하는 것을 매 초마다 BFS로 탐색!
public class _22_Solution_1 {
    // 이동 변수
    private static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 좌표 클래스
    public static class Point{
        int row;    // 행
        int col;    // 열
        int time;   // 시간
        public Point(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int C = Integer.parseInt(st.nextToken());   // 열 크기
            int R = Integer.parseInt(st.nextToken());   // 행 크기
            char[][] map = new char[R][C];

            // 불 이동 덱!
            Deque<Point> fireDeque = new LinkedList<>();
            // 사람 이동 덱!
            Deque<Point> peopleDeque = new LinkedList<>();
            // 방문 배열
            boolean[][] visited = new boolean[R][C];

            // 건물 정보 입력!
            for(int r = 0; r < R; r++){
                String info = in.readLine();
                for(int c = 0; c < C; c++){
                    map[r][c] = info.charAt(c);
                    if(map[r][c] == '*') fireDeque.offerLast(new Point(r, c, 0));
                    else if(map[r][c] == '@') {
                        peopleDeque.offerLast(new Point(r, c, 0));
                        visited[r][c] = true;
                    }
                }
            }

            // 정답 초기화
            int answer = -1;
            // 사람 이동!
            while(!peopleDeque.isEmpty() && answer == -1){
                // 불 먼저 번짐!
                burn(fireDeque, map, R, C);

                // 사람 이동! => 탈출한 경우 시간, 탈출하지 못한 경우 -1
                answer = move(peopleDeque, map, visited, R, C);
            }
            
            // 사람 탈출이 불가능한 경우 IMPOSSIBLE
            // 사람 탈출이 가능한 경우 시간 반환
            sb.append(answer == -1 ? "IMPOSSIBLE" : answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int move(Deque<Point> peopleDeque, char[][] map,  boolean[][] visited, int R, int C) {
        int size = peopleDeque.size();
        while(size-- > 0){
            Point cur = peopleDeque.pollFirst();
            for(int[] d : DELTA){
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.time+1);

                if(next.row < 0 || next.row >= R ||
                        next.col < 0 || next.col >= C
                ) return next.time;

                if(map[next.row][next.col] == '#' ||
                        map[next.row][next.col] == '*' ||
                        visited[next.row][next.col]
                ) continue;

                peopleDeque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }

        return -1;
    }

    private static void burn(Deque<Point> fireDeque, char[][] map, int R, int C) {
        int size = fireDeque.size();
        while(size-- > 0){
            Point cur = fireDeque.pollFirst();
            for(int[] d : DELTA){
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.time+1);

                if(next.row < 0 || next.row >= R ||
                        next.col < 0 || next.col >= C ||
                        map[next.row][next.col] == '#' ||
                        map[next.row][next.col] == '*'
                ) continue;

                map[next.row][next.col] = '*';
                fireDeque.offerLast(next);
            }
        }
    }
}
