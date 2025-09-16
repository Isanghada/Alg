package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

// https://www.acmicpc.net/problem/5547
// - BFS
public class _16_Solution_1 {
    static public class Point{
        int w;
        int h;
        public Point(int w, int h){
            this.w = w;
            this.h = h;
        }
    }

    static boolean[][] VISITED;
    static int[][] RESULT;
    static final int[][] ODD_DELTA = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static final int[][] EVEN_DELTA = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] map = new int[W+2][H+2];
        VISITED = new boolean[W+2][H+2];
        RESULT = new int[W+2][H+2];
        for(int w = 1; w <= W; w++){
            st = new StringTokenizer(in.readLine());
            for(int h = 1; h <= H; h++) {
                map[w][h] = Integer.parseInt(st.nextToken());
                if(map[w][h] == 1) VISITED[w][h] = true;
            }
        }

        bfs(map, new Point(0, 0), W, H);

        int answer = 0;
        final int limitW = W + 2;
        final int limitH = H + 2;
        for(int w = 0; w < limitW; w++){
            for(int h = 0; h < limitH; h++){
                answer += RESULT[w][h];
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static void bfs(int[][] map, Point start, int W, int H) {
        Deque<Point> deque = new LinkedList<>();

        deque.offerLast(start);
        VISITED[start.w][start.h] = true;
        while(!deque.isEmpty()){
            Point cur = deque.pollFirst();

            int[][] delta = (cur.w % 2 == 1) ? ODD_DELTA : EVEN_DELTA;
            for(int[] d : delta){
                Point next = new Point(cur.w+d[0], cur.h+d[1]);
                if(next.w < 0 || next.w >= W+2 || next.h < 0 || next.h >= H+2) continue;
                if(map[next.w][next.h] == 1) RESULT[next.w][next.h]++;
                else{
                    if(VISITED[next.w][next.h]) continue;
                    VISITED[next.w][next.h] = true;
                    deque.offerLast(next);
                }
            }
        }
    }
}
