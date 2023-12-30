package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27211
// - BFS를 통해 영역 탐색!
public class _26_Solution_1 {
    // 좌표 정보를 담을 클래스
    private static class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static int N, M;             // 행성 크기
    public static int[][] MAP;          // 행성 정보
    public static boolean[][] VISITED;  // 방문 배열
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 행성 크기 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기
        
        // 행성 정보 입력
        MAP = new int[N][M];
        for(int r = 0; r < N; r++){
            MAP[r] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        }

        // 구역 개수 초기화
        int answer = 0;
        // 방문 배열 초기화
        VISITED = new boolean[N][M];
        // 모든 좌표 탐색
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 0번 지역이면서 방문하지 않은 경우!
                if(MAP[r][c] == 0 && !VISITED[r][c]){
                    // 구역 탐색
                    visitZone(r, c);
                    // 구역 개수 증가
                    answer++;
                }
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 이동 변수! : 상, 하, 좌, 우 => 4방향
    private static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 구역 탐색 함수 : BFS 활용
    private static void visitZone(int r, int c) {
        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();

        // (r, c)를 기준으로 초기값 설정
        deque.offerLast(new Point(r, c));
        VISITED[r][c] = true;

        // 덱이 빌 때가지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();

            // 새로운 구역 탐색
            for(int[] d : DELTA){
                // 새로운 좌표 탐색
                Point next = new Point(cur.row+d[0], cur.col+d[1]);
                // 범위를 벗어나는 경우 조정
                next.row = (next.row + N) % N;
                next.col = (next.col + M) % M;

                // 구역 1인 경우 혹은 이미 방문한 경우 패스
                if(MAP[next.row][next.col] == 1 ||
                VISITED[next.row][next.col]) continue;

                // 새로운 좌표 탐색
                deque.offerLast(next);
                VISITED[next.row][next.col] = true;
            }
        }
    }
}
