package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22352
// - BFS : 각 격자판에서 다른 부분이 있으면 해당 위치에서 BFS를 통해 변환하여 체크
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        int[][] before = getBoard(N, M, in);    // 이전 격자판
        int[][] after = getBoard(N, M, in);     // 이후 격자판
        // 변환 플래그
        boolean flag = false;
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 격자판에서 다른 부분이 있다면 변환!
                if(before[r][c] != after[r][c]){
                    // before 격자판의 (r, c) 위치에서 변화 시작
                    changeBoard(before, r, c, after[r][c]);
                    // 플래그 변경
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        // 정답 반환
        // - before, after를 확인하여 동일하면 "YES", 다르면 "NO" 반환
        sb.append(isPossible(before, after));
        System.out.println(sb);
    }

    private static String isPossible(int[][] before, int[][] after) {
        final int n = before.length;
        final int m = before[0].length;

        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                if(before[r][c] != after[r][c]) return "NO";
            }
        }

        return "YES";
    }

    private static class Node{
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    private static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static void changeBoard(int[][] before, int r, int c, int type) {
        Deque<Node> deque = new LinkedList<>();
        boolean[][] visited = new boolean[before.length][before[0].length];

        final int origin = before[r][c];

        deque.offerLast(new Node(r, c));
        visited[r][c] = true;
        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            before[cur.row][cur.col] = type;

            for(int[] d : DELTA){
                Node next = new Node(cur.row+d[0], cur.col+d[1]);

                if(next.row < 0 || next.row >= before.length || next.col < 0 || next.col >= before[0].length ||
                        visited[next.row][next.col] ||
                        before[next.row][next.col] != origin
                ) continue;

                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }
    }

    private static int[][] getBoard(int n, int m, BufferedReader in) throws Exception {
        int[][] board = new int[n][m];
        StringTokenizer st;
        for(int r = 0; r < n ; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < m; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }
        return board;
    }
}
