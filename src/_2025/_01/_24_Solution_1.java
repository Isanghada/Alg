package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/19952
// -
public class _24_Solution_1 {
    static class Node{
        int row;
        int col;
        int f;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
        public Node(int row, int col, int f){
            this.row = row;
            this.col = col;
            this.f = f;
        }
    }
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            int[] starts = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
            int[] ends = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };

            int[][] map = new int[H+1][W+1];
            while(O--  > 0){
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                map[x][y] = l;
            }

            sb.append(bfs(map, H, W, starts, ends, F) ? "잘했어!!":"인성 문제있어??").append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean bfs(int[][] map, int h, int w, int[] starts, int[] ends, int f) {
        Deque<Node> deque = new LinkedList<>();
        boolean[][] visited = new boolean[h+1][w+1];

        deque.offerLast(new Node(starts[0], starts[1], f));
        visited[starts[0]][starts[1]] = true;
        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if(cur.row == ends[0] && cur.col == ends[1]) return true;
            if(cur.f == 0) continue;

            for(int[] d : DELTA){
                Node next = new Node(cur.row+d[0], cur.col+d[1]);
                if(next.row < 1 || next.row > h ||
                        next.col < 1 || next.col > w ||
                        visited[next.row][next.col] ||
                        map[next.row][next.col] - map[cur.row][cur.col] > cur.f
                ) continue;
                next.f = cur.f - 1;
                visited[next.row][next.col] = true;
                deque.offerLast(next);
            }
        }

        return false;
    }
}
