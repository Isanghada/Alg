package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31849
// - BFS : 편의점 기준 BFS를 통해 집별 편세권 점수 계산하여 비교!
public class _27_Solution_1 {
    // 이동 변수
    public static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1,0}};
    // 좌표 클래스
    public static class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int distance;   // 거리
        public Point(int row, int col, int distance){
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int R = Integer.parseInt(st.nextToken());   // 집 개수
        int C = Integer.parseInt(st.nextToken());   // 편의점 개수

        // 집 정보 입력!
        // - map[r][c] : r, c 좌표의 집 월세(집이 없는 경우 0!)
        int[][] map = new int[N+1][M+1];
        while(R-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            map[a][b] = p;
        }

        // 편의점 정보 입력
        Point[] stores = new Point[C];
        for(int i = 0; i < C; i++){
            st = new StringTokenizer(in.readLine());
            stores[i] = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    0
            );
        }

        // 정답 반환
        // - bfs를 통해 편세권 점수 출력
        sb.append(getMinScore(map, stores, N, M));
        System.out.println(sb);
    }

    private static int getMinScore(int[][] map, Point[] stores, int n, int m) {
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        
        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[n+1][m+1];

        // 초기 정보 입력 : 각 편의점에서 시작!
        for(Point s : stores){
            deque.offerLast(s);
            visited[s.row][s.col] = true;
        }

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 정보 반환
            Point cur = deque.pollFirst();

            // 집에 도착한 경우 : 편세권 점수 갱신!
            if(map[cur.row][cur.col] != 0){
                answer = Math.min(answer, cur.distance*map[cur.row][cur.col]);
            }

            // 다음 좌표 탐색
            for(int[] d : DELTA){
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.distance+1);

                // 범위를 벗어나거나 이미 방문한 경우 패스
                if(next.row < 1 || next.row > n || next.col < 1 || next.col > m ||
                        visited[next.row][next.col]
                ) continue;

                // 새로운 좌표 정보 추가!
                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }

        return answer;
    }
}
