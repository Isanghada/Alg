package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25391
// - 브루트포스 : 각 바이러스를 BFS로 S초까지 증식하여 결과 확인
public class _25_Solution_1 {
    public static int[][] MAP;              // 시험관
    public static PriorityQueue<Node> PQ;   // BSF에 활용할 우선순위 큐
    public static boolean[][] VISITED;      // 방문 배열
    // 노드 클래스
    public static class Node implements Comparable<Node>{
        int row;    // 행
        int col;    // 열
        int type;   // 바이러스
        int time;   // 시간
        public Node(int row, int col, int type, int time){
            this.row = row;
            this.col = col;
            this.type = type;
            this.time = time;
        }
        // 시간 기준 오름차순, 바이러스 기준 오름차순
        @Override
        public int compareTo(Node o){
            int diff = this.time - o.time;
            return (diff == 0 ? (this.type - o.type) : diff);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 시험관 크기
        int K = Integer.parseInt(st.nextToken());   // 바이러스 개수

        // 초기화
        MAP = new int[N][N];
        PQ = new PriorityQueue<>();
        VISITED = new boolean[N][N];

        // 시험관 입력 : 바이러스일 경우 PQ, VISITED 입력
        for(int r = 0 ; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++){
                MAP[r][c] = Integer.parseInt(st.nextToken());
                if(MAP[r][c] != 0) {
                    PQ.offer(new Node(r, c, MAP[r][c], 0));
                    VISITED[r][c] = true;
                }
            }
        }

        st = new StringTokenizer(in.readLine());
        int S = Integer.parseInt(st.nextToken());       // 최종 시간
        int X = Integer.parseInt(st.nextToken()) - 1;   // 목표 행
        int Y = Integer.parseInt(st.nextToken()) - 1;   // 목표 열

        // 결과 반환
        // - BFS를 통해 탐색
        sb.append(getVirus(S, X, Y));
        System.out.println(sb);
    }
    private static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int getVirus(int s, int x, int y) {
        // PQ가 빌 떄까지 반복
        while(!PQ.isEmpty()){
            // 현재 노드 확인
            Node cur = PQ.poll();

            // 목표 행, 열인 경우 현재 바이러스 반환
            if(cur.row == x && cur.col == y) return cur.type;
            // 최종 시간과 동일한 경우 패스
            if(cur.time >= s) continue;
            // 상, 하, 좌, 우로 이동
            for(int[] d : DELTA){
                // 새로운 노드 생성
                Node next = new Node(cur.row+d[0], cur.col+d[1], cur.type, cur.time+1);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 이미 방문한 경우
                // - 다른 바이러스가 증식한 경우
                if(next.row < 0 || next.row >= MAP.length ||
                        next.col < 0 || next.col >= MAP[0].length ||
                        VISITED[next.row][next.col] ||
                        MAP[next.row][next.col] != 0
                ) continue;

                // 값 갱신
                MAP[next.row][next.col] = next.type;
                VISITED[next.row][next.col] = true;
                PQ.offer(next);
            }
        }

        return 0;
    }
}
