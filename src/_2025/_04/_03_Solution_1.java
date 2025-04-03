package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16469
// - BFS : BFS를 통해 악당의 최소 이동 횟수 탐색!
//         모든 좌표를 탐색하여 최소 이동 횟수로 이동할 수 있는 좌표 계산
public class _03_Solution_1 {
    static class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static final int ENEMY = 3, MAX = 1_000_000;
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] board = new int[R+1][C+1];
        for(int r = 1; r <= R; r++){
            String input = in.readLine();
            for(int c = 1; c <= C; c++){
                board[r][c] = input.charAt(c-1) - '0';
            }
        }

        Point[] points = new Point[ENEMY];
        for(int i = 0; i < ENEMY; i++){
            st = new StringTokenizer(in.readLine());
            points[i] = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
        int[][][] moveCount = new int[ENEMY][R+1][C+1];
        for(int i = 0; i < ENEMY; i++) {
            moveCount[i] = bfs(board, points[i], R, C);
//            for(int[] m : moveCount[i]) {
//                for(int value : m) System.out.printf("%7d ", value);
//                System.out.println();
//            }
//            System.out.println("-------");
        }

        int answer = 0;
        int minMove = MAX;
        for(int r = 1; r <= R; r++){
            for(int c = 1; c <= C; c++){
                int move = check(moveCount, r, c);
                if(move < minMove){
                    answer = 1;
                    minMove = move;
                }else if(move == minMove) answer++;
            }
        }

        if(minMove != MAX) sb.append(minMove).append("\n").append(answer);
        else sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }

    private static int check(int[][][] moveCount, int r, int c) {
        int result = moveCount[0][r][c];
        for(int[][] move : moveCount) result = Math.max(result, move[r][c]);
        return result;
    }

    private static int[][] bfs(int[][] board, Point start, int r, int c) {
        Deque<Point> deque = new LinkedList<>();
        int[][] moveCount = new int[r+1][c+1];
        for(int i = 0; i <= r; i++) Arrays.fill(moveCount[i], MAX);

        deque.offerLast(start);
        moveCount[start.row][start.col] = 0;

        while(!deque.isEmpty()){
            Point cur = deque.pollFirst();

            for(int[] d : DELTA){
                Point next = new Point(cur.row+d[0], cur.col+d[1]);
                if(next.row > r || next.row < 1 ||
                        next.col > r || next.col < 1 ||
                        moveCount[next.row][next.col] != MAX ||
                        board[next.row][next.col] == 1) continue;

                deque.offerLast(next);
                moveCount[next.row][next.col] = moveCount[cur.row][cur.col] + 1;
            }
        }

        return moveCount;
    }
}
