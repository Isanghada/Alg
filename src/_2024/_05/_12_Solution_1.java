package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1245
// - BFS : 모든 지점에서 산봉우리인지 탐색!
public class _12_Solution_1 {
    // 좌표 클래스
    private static class Point{
        int r;  // 행 좌표
        int c;  // 열 좌표
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 길이
        int M = Integer.parseInt(st.nextToken());   // 열 길이


        int[][] map = new int[N][M];            // 농장 배열
        boolean[][] top = new boolean[N][M];    // 산봉우리 여부!
        // 농장 정보 입력
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(in.readLine());
            for (int c = 0; c < M; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        // 정답 초기화
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 0이 아니고 산봉우리가 아닌 곳! => 체크
                if (map[r][c] != 0 && !top[r][c]) {
                    // 산봉우리인 경우 정답 증가!
                    if(bfs(map, top, new Point(r, c), N, M)) answer++;
                }
            }
        }

        // 결과 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 이동 정보!
    private static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    // bfs 함수 : 인접 지역 탐색
    private static boolean bfs(int[][] map, boolean[][] top, Point start, int n, int m) {
        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[n][m];
        // 초기 정보 설정
        deque.offerLast(start);
        visited[start.r][start.c] = true;
        // 산봉우리 리스트!
        List<Point> topList = new ArrayList<>();
        // 덱이 빌때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표
            Point cur = deque.pollFirst();

            for(int[] d : DELTA){
                // 인접 좌표
                Point next = new Point(cur.r+d[0], cur.c+d[1]);

                // 아래의 경우 패스
                // - 범위를 벗어날 경우
                // - 이미 방문한 경우
                if(next.r < 0 || next.r >= n || next.c < 0 || next.c >= m ||
                        visited[next.r][next.c]
                ) continue;
                // 초기 좌표보다 높이가 높을 경울 false 반환
                if(map[next.r][next.c] > map[start.r][start.c]) return false;
                // 초기 좌표와 같은 높이일 경우 : 산봉우리 리스트에 추가!
                else if(map[next.r][next.c] == map[start.r][start.c]){
                    deque.offerLast(next);
                    topList.add(next);
                }
                // 방문 체크
                visited[next.r][next.c] = true;
            }
        }
        // 산봉우리인 경우 배열에 체크
        for(Point t : topList) top[t.r][t.c] = true;
        // 산봉우리인 경우 true 반환
        return true;
    }
}
