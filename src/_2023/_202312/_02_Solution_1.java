package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/26598
// - BFS를 통해 모든 영역이 직사각형인지 확인
public class _02_Solution_1 {
    public static int N, M; // 행, 열 길이
    public static char[][] BOARD;   // 색종이 배열
    public static boolean[][] VISITED;  // 방문 배열
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202312/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());   // 행 길이
        M = Integer.parseInt(st.nextToken());   // 열 길이

        // 색종이 배열
        BOARD = new char[N][M];
        for(int i = 0; i < N; i++) BOARD[i] = in.readLine().toCharArray();
        
        boolean flag = true;    // 플래그 초기화
        VISITED = new boolean[N][M];    // 방문 배열

        // 모든 좌표의 영역이 직사각형인지 확인
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 방문한 곳인 경우 패스
                if(VISITED[r][c]) continue;

                // flag를 현재 영역이 직사각형인지로 변경
                flag = isPossible(r, c);

                // 직사각형이 아니라면 종료
                if(!flag) break;
            }
            if(!flag) break;
        }

        // 정답 반환
        // - 직사각형 영역으로 구성되었다면 dd, 아니라면 BaboBabo 반환
        sb.append(flag ? "dd" : "BaboBabo");
        System.out.println(sb);
    }
    // 좌표 클래스
    private static class Point{
        int r;  // 행
        int c;  // 열

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    // 이동 계산용 변수
    private static int[][] DELTA = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static boolean isPossible(int r, int c) {
        // 현재 영역의 색종이 타입
        final char TYPE = BOARD[r][c];

        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();

        // 초기값 설정 : (r, c) 좌표 추가
        deque.offerLast(new Point(r, c));
        // 방문 표시
        VISITED[r][c] = true;

        // 직사각형 우하단 좌표
        // - (r, c)는 좌상단 좌표
        int limitR = r;
        int limitC = c;
        // 영역의 크기
        int count = 0;
        
        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();
            // count 증가
            count++;

            // 우하단 좌표를 최대값으로 변경
            limitR = Math.max(limitR, cur.r);
            limitC = Math.max(limitC, cur.c);

            // 다음 좌표 탐색
            for(int[] d : DELTA){
                Point next = new Point(cur.r+d[0], cur.c+d[1]);

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 현재 TYPE이 아닌 경우
                if(next.r < 0 || next.r >= N || next.c < 0 || next.c >= M ||
                        VISITED[next.r][next.c] ||
                        BOARD[next.r][next.c] != TYPE)
                    continue;

                // 덱에 좌표 추가
                deque.offerLast(next);
                // 방문 표시
                VISITED[next.r][next.c] = true;
            }
        }

        // 직사각형 크기가 현재 영역의 크기와 같다면 true, 아니라면 false 반환
        return (((limitR-r+1) * (limitC-c+1)) == count ? true : false);
    }
}
