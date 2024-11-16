package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14923
// - BFS : 마법을 사용한 경우/사용하지 않은 경우 2가지 경우를 모두 BFS로 체크!
//         기본적으로 마법을 사용하지 않고 움직이다 마법 사용이 가능한 경우 사용
public class _16_Solution_1 {
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 노드 클래스 : 좌표 정보
    private static class Node {
        int row;    // 행 좌표
        int col;    // 열 좌표
        int count;  // 이동 횟수
        int used;   // 마법 사용 여부(0-미사용, 1-사용)
        public Node(int row, int col){
            this(row, col, 0, 0);
        }
        public Node(int row, int col, int count, int used){
            this.row = row;
            this.col = col;
            this.count = count;
            this.used = used;
        }
        @Override
        public String toString() {
            StringBuilder toString = new StringBuilder();
            toString.append("[ row=").append(row)
                    .append(", col=").append(col)
                    .append(", count=").append(count)
                    .append(", used=").append(used);
            return toString.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미로 크기
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 시작 위치 정보
        st = new StringTokenizer(in.readLine());
        Node start = new Node(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1
        );

        // 종료 위치 정보
        st = new StringTokenizer(in.readLine());
        Node end = new Node(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1
        );

        // 미로 정보
        int[][] board = new int[N][M];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // bfs를 통해 최소 이동 횟수 계산
        int answer = bfs(board, start, end, N, M);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static int bfs(int[][] board, Node start, Node end, int n, int m) {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열
        // - visited[0][n][m] : 마법을 사용하지 않고 (n, m) 방문 
        // - visited[1][n][m] : 마법을 사용하고 (n, m) 방문
        boolean[][][] visited = new boolean[2][n][m];

        // 시작 위치 설정
        deque.offerLast(start);
        visited[start.used][start.row][start.col] = true;

        while(!deque.isEmpty()){
            // 현재 노드 반환
            Node cur = deque.pollFirst();
//            System.out.println(cur);

            // 도착 위지인 경우 이동 횟수 반환
            if(check(cur, end)) return cur.count;
            for(int[] d : DELTA){
                // 다음 노드 계산
                Node next = new Node(cur.row+d[0], cur.col+d[1], cur.count+1, cur.used);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 벽을 만났지만 이미 마법을 사용한 경우
                // - 방문한 경우
                if(next.row < 0 || next.row >= n ||
                        next.col < 0 || next.col >= m ||
                        (board[next.row][next.col] == 1 && next.used == 1) ||
                        visited[next.used][next.row][next.col]
                ) continue;

                // 벽인 경우 : used 갱신
                if(board[next.row][next.col] == 1) next.used = 1;
                // next 노드 추가!
                deque.offerLast(next);
                visited[next.used][next.row][next.col] = true;
            }
        }
        // 탈출하지 못한 경우 -1 반환
        return -1;
    }

    private static boolean check(Node cur, Node target) {
        if(cur.row == target.row && cur.col == target.col) return true;
        else return false;
    }
}
