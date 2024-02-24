package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7569
// - BFS : 초기 토마토를 기준으로 BFS를 통해 가능한 최소 날짜 탐색
public class _24_Solution_1 {
    // M, N, H : 열, 행, 높이
    // TOTAL : 전체 토마토의 수
    public static int M, N, H, TOTAL;
    public static int[][][] BOX;
    // 이동 변수
    public static int[][] DELTA = new int[][] {{1, 0, 0}, {-1, 0, 0},
                                                {0, 1, 0}, {0, -1, 0},
                                                {0, 0, 1}, {0, 0, -1}};
    // 토마토를 담을 노드 클래스
    public static class Node{
        int h;  // 높이
        int n;  // 행
        int m;  // 열
        int day;    // 날짜
        public Node(int h, int n, int m, int day){
            this.h = h;
            this.n = n;
            this.m = m;
            this.day = day;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());   // 열 입력
        N = Integer.parseInt(st.nextToken());   // 행 입력
        H = Integer.parseInt(st.nextToken());   // 높이 입력
        // 전체 토마토의 수 초기화
        TOTAL = H * N * M;

        // 박스 입력
        BOX = new int[H][N][M];
        List<Node> startList = new ArrayList<>();
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(in.readLine());
                for(int m = 0; m < M; m++){
                    BOX[h][n][m] = Integer.parseInt(st.nextToken());
                    // 토마토가 없는 경우 TOTAL 감소
                    if(BOX[h][n][m] == -1) TOTAL--;
                    // 토마토가 익은 경우 startList에 추가
                    else if(BOX[h][n][m] == 1) startList.add(new Node(h, n, m, 0));
                }
            }
        }

        // 정답 반환
        // - BFS를 통해 모든 토마토가 익을 최소 날짜 계산
        sb.append(getMinDay(startList));
        System.out.println(sb);
    }
    // BFS 함수
    private static int getMinDay(List<Node> startList) {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열
        boolean[][][] visited = new boolean[H][N][M];

        // 초기 상태 입력
        for(Node start : startList){
            deque.offerLast(start);
            visited[start.h][start.n][start.m] = true;
        }

        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Node cur = deque.pollFirst();

            // 토마토의 수 감소!
            TOTAL--;
            // 모든 토마토가 익은 경우 날짜 반환
            if(TOTAL == 0) return cur.day;

            for(int[] d : DELTA){
                // 다음 좌표
                Node next = new Node(cur.h + d[0], cur.n+d[1], cur.m+d[2], cur.day+1);

                // 아래의 경우 패스
                // - 범위를 벗어날 경우
                // - 이미 방문한 경우
                // - 토마토가 없는 경우
                if(next.h < 0 || next.h >= H ||
                        next.n < 0 || next.n >= N ||
                        next.m < 0 || next.m >= M ||
                        visited[next.h][next.n][next.m] ||
                        BOX[next.h][next.n][next.m] == -1
                ) continue;

                // 좌표 추가!
                deque.offerLast(next);
                visited[next.h][next.n][next.m] = true;
            }
        }

        return -1;
    }
}
