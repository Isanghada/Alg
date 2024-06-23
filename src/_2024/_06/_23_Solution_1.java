package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17836
// - bfs : 너비 우선 탐색을 통해 최소 시간 계산
public class _23_Solution_1 {
    // 영웅 클래스
    public static class Hero{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int time;   // 이동 시간
        int gram;   // 그람 여부(0-미소유, 1-소유)
        public Hero(int row, int col){
            this(row, col, 0, 0);
        }
        public Hero(Hero h){
            this(h.row, h.col, h.time, h.gram);
        }
        public Hero(int row, int col, int time, int gram){
            this.row = row;
            this.col = col;
            this.time = time;
            this.gram = gram;
        }
        public void setGram(int gram){ this.gram = gram; }
        public void movePosition(int row, int col) {
            this.row += row;
            this.col += col;
        }
        public void addTime() {this.time++;}
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int T = Integer.parseInt(st.nextToken());   // 제한 시간

        // 맵 입력
        int[][] map = new int[N+1][M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++) map[n][m] = Integer.parseInt(st.nextToken());
        }

        // bfs를 통해 최소 시간 계산
        int minTime = bfs(map, N, M);

        // 정답 출력
        // - 제한 시간 이하일 경우 시간 출력, 불가능한 경우 Fail 출력
        sb.append(minTime <= T ? minTime : "Fail");
        System.out.println(sb);
    }
    // 이동 변수 : 상, 하, 좌, 우 이동 변수
    private static int [][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // BFS 함수 : (1, 1)을 시작으로 (n, m)에 도달하는 최소 시간 반환
    private static int bfs(int[][] map, int n, int m) {
        // 덱 초기화
        Deque<Hero> deque = new LinkedList<>();
        // 방문 배열 초기화
        // - visited[0][r][c] : 그람 미소유시 (r, c) 방문 여부 
        // - visited[1][r][c] : 그람 소유시 (r, c) 방문 여부 
        boolean[][][] visited = new boolean[2][n+1][m+1];
        
        // 초기값 설정
        deque.offerLast(new Hero(1, 1));
        visited[0][1][1] = true;

        // BFS 실행
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Hero cur = deque.pollFirst();
            // 공주님에게 도착한 경우 시간 반환
            if(cur.row == n && cur.col == m) return cur.time;
            // 상, 하, 좌, 우 탐색
            for(int[] d : DELTA){
                // 새로운 좌표 생성
                Hero next = new Hero(cur);
                // 값 계산
                next.movePosition(d[0], d[1]);
                next.addTime();

                // 아래의 경우 패스
                // - 범위를 벗어날 경우
                // - 이미 방문한 경우
                // - 그람 미소유이며 벽일 경우
                if(next.row < 1 || next.row > n || next.col < 1 || next.col > m ||
                        visited[next.gram][next.row][next.col] ||
                        (next.gram == 0 && map[next.row][next.col] == 1)
                ) continue;
                // 그람 좌표일 경우
                if(map[next.row][next.col] == 2) {
                    // 방문 표시
                    visited[next.gram][next.row][next.col] = true;
                    // 그람 획득!
                    next.setGram(1);
                }
                // 새로운 좌표 추가!
                deque.offerLast(next);
                visited[next.gram][next.row][next.col] = true;
            }
        }
        // 도달할 수 없는 경우 1000000 반환(최대 제한 시간을 넘어서는 값)
        return 1000000;
    }
}
