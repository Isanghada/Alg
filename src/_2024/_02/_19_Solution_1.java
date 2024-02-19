package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12887
// - BFS : 최소 경로를 남겨두고 나머지를 검은색으로 변환!
public class _19_Solution_1 {
    // 노드 클래스
    private static class Node{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int count;  // 이동 횟수

        public Node(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }
    public static int N;
    public static char[][] BOARD;
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 열 크기
        N = Integer.parseInt(in.readLine());

        // 보드 초기화
        BOARD = new char[2][N];
        StringTokenizer st = null;

        // 흰색 칸의 개수
        int totalWhite = 0;
        // 보드 입력
        for (int r = 0; r < 2; r++) {
            String input = in.readLine();
            for (int c = 0; c < N; c++) {
                BOARD[r][c] = input.charAt(c);
                // 흰색 칸 카운팅
                if (BOARD[r][c] == '.') totalWhite++;
            }
        }

        // 정답 반환
        // - 모든 시작 위치에서 최소 경로를 구하여 해당 경로를 제외한 모든 칸을 검은색으로 변경!
        sb.append(totalWhite - Math.min(BFS(0, 0), BFS(1, 0)));
        System.out.println(sb);
    }
    private static int BFS(int row, int col) {
        Deque<Node> deque = new LinkedList<>();
        boolean[][] visited = new boolean[2][N];

        if(BOARD[row][col] == '.') {
            deque.offerLast(new Node(row, col, 1));
            visited[row][col] = true;
        }

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if(cur.col == (N-1)) return cur.count;

            for(int[] d : DELTA){
                Node next = new Node(cur.row+d[0], cur.col+d[1], cur.count+1);

                if(next.row < 0 || next.row >= 2 || next.col < 0 || next.col >= N ||
                        BOARD[next.row][next.col] =='#' ||
                        visited[next.row][next.col]
                ) continue;

                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }

        return N*N;
    }
}
