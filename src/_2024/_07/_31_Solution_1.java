package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30405
// - BFS : 너비 우선 탐색을 통해 에어컨 바람 경로 탐색!
public class _31_Solution_1 {
    // 바람 클래스
    public static class Wind{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int type;   // 바람 방향
        public Wind(int row, int col, int type){
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 방 정보
        int[][] map = new int[N][M];
        // 바람 방문 정보
        boolean[][][] visited = new boolean[4][N][M];
        // 에어컨 정보!
        List<Wind> airConditioners = new ArrayList<>();
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                // 에어콘이 있는 경우 : 4방향 바람 추가!
                if(map[r][c] == 9){
                    for(int type = 0; type < 4; type++) airConditioners.add(new Wind(r, c, type));
                    map[r][c] = 0;
                }
            }
        }
        // BFS를 통해 바람 경로 탐색!
        bfs(N, M, map, visited, airConditioners);

        // 정답 반환
        // - 바람이 오는 좌표의 수 계산
        sb.append(getCount(N, M,visited));
        System.out.println(sb);
    }

    private static int getCount(int n, int m, boolean[][][] visited) {
        int result = 0;
        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                if(visited[0][r][c] ||
                        visited[1][r][c] ||
                        visited[2][r][c] ||
                        visited[3][r][c]
                ) {
//                    System.out.print("1 ");
                    result++;
                }//else System.out.print("0 ");
            }
            //System.out.println();
        }
        return result;
    }

    public static int[][][] DELTA = new int[][][]{
            {{-1, 0, 0}, {1, 0, 1}, {0, -1, 2}, {0, 1, 3}},
            {{-1, 0, 0}, {1, 0, 1}, {0, 1, 3}, {0, -1, 2}},
            {{1, 0, 1}, {-1, 0, 0}, {0, -1, 2}, {0, 1, 3}},
            {{0, 1, 3}, {0, -1, 2}, {1, 0, 1}, {-1, 0, 0}},
            {{0, -1, 2}, {0, 1, 3}, {-1, 0, 0}, {1, 0, 1}}
    };
    private static void bfs(int n, int m, int[][] map, boolean[][][] visited, List<Wind> airConditioners) {
        Deque<Wind> deque = new LinkedList<>();
        for(Wind w : airConditioners){
            deque.add(w);
            visited[w.type][w.row][w.col] = true;
        }

        while(!deque.isEmpty()){
            Wind cur = deque.pollFirst();

            int[] d = DELTA[map[cur.row][cur.col]][cur.type];
            cur.row += d[0];
            cur.col += d[1];
            cur.type = d[2];

            if(cur.row < 0 || cur. row >= n || cur.col < 0 || cur.col >= m ||
                    visited[cur.type][cur.row][cur.col]
            ) continue;

            visited[cur.type][cur.row][cur.col] = true;
            deque.offerLast(cur);
        }
    }
}