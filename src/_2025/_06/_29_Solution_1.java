package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9204
// -
public class _29_Solution_1 {
    static class Point{
        int row;
        int col;
        public Point(String col, int row){
            this.row = row;
            this.col = col.charAt(0) - 'A' + 1;
        }
        public Point(int col, int row){
            this.row = row;
            this.col = col;
        }
        public String getPoint(){
            StringBuilder sb = new StringBuilder();
            sb.append((char)('A'+ this.col - 1)).append(" ").append(this.row);
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point p = (Point) obj;
                if(this.row == p.row &&
                        this.col == p.col){
                    return true;
                }
                return false;
            }
            return false;
        }
    }
    static class Node{
        int count;
        Point point;
        String path;
        public Node(int count, Point point, String path){
            this.count = count;
            this.point = point;
            this.path = path;
        }
    }
    static final int[][] DELTA = new int[][]{{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    static final int MAX = 8;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());

            boolean flag = true;
            Point start = new Point(
                    st.nextToken(),
                    Integer.parseInt(st.nextToken())
            );

            Point end = new Point(
                    st.nextToken(),
                    Integer.parseInt(st.nextToken())
            );

            sb.append(bfs(start, end)).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static String bfs(Point start, Point end) {
        Deque<Node> deque = new LinkedList<>();
        boolean[][] visited = new boolean[MAX+1][MAX+1];

        deque.offerLast(new Node(
                0,
                start,
                start.getPoint()
        ));
        visited[start.row][start.col] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            // System.out.println(cur.point.getPoint()+"========");

            if (cur.point.equals(end)) {
                return cur.count + " " + cur.path;
            }
            if(cur.count >= 4) continue;
            for(int[] d : DELTA){
                int row = cur.point.row + d[0];
                int col = cur.point.col + d[1];

                while(row > 0 && row <= MAX &&
                        col > 0 && col <= MAX &&
                        !visited[row][col]
                ) {
                    Point p = new Point(col, row);
                    Node next = new Node(
                            cur.count + 1,
                            p,
                            cur.path + " " + p.getPoint()
                    );

                    deque.offerLast(next);
                    visited[p.row][p.col] = true;
                    row += d[0];
                    col += d[1];
                }
            }
        }

        return "Impossible";
    }
}