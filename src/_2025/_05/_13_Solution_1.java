package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32983
// - 누적합 : 빛의 세기에 따라 얻을 수 있는 루피의 합을 BFS를 통해 계산!
//              구해진 루피의 합을 이용해 세기별 이윤 계산하여 최대값 반환
public class _13_Solution_1 {
    // 좌표 클래스
    static class Point{
        int row;    // 행
        int col;    // 열
        int step;   // 거리
        public Point(int row, int col, int step){
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행
        int M = Integer.parseInt(st.nextToken());   // 열
        int C = Integer.parseInt(st.nextToken());   // 비용

        // 출발 지점
        st = new StringTokenizer(in.readLine());
        Point start = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                0
        );

        // 동굴 정보 입력
        int[][] map = new int[N+1][M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++) map[n][m] = Integer.parseInt(st.nextToken());
        }

        // 빛 세기별 루피의 합 초기화
        int[] marginArr = new int[N*M+1];

        Deque<Point> deque = new LinkedList<>();        // 덱
        boolean[][] visited = new boolean[N+1][M+1];    // 방문 배열

        // 초기값 설정
        deque.offerLast(start);
        visited[start.row][start.col] = true;
        int step = 0;

        while(!deque.isEmpty()){
            // 현재 최단 거리 좌표 개수!
            int size = deque.size();
            // 루피의 합 누적합!
            if(step != 0) marginArr[step] = marginArr[step-1];
            while(size-- > 0){
                Point cur = deque.pollFirst();
                marginArr[step] += map[cur.row][cur.col];

                for(int[] d : DELTA){
                    Point next = new Point(
                            cur.row + d[0],
                            cur.col + d[1],
                            cur.step + 1
                    );

                    if(next.row < 1 || next.row > N ||
                            next.col < 1 || next.col > M ||
                            visited[next.row][next.col] ||
                            map[next.row][next.col] == -1
                    ) continue;

                    visited[next.row][next.col] = true;
                    deque.offerLast(next);
                }
            }
            step++;
        }

        // System.out.println(Arrays.toString(marginArr));
        // 정답 초기화
        int answer = 0;
        // - 세기별 이윤 계산 후 최대값 갱신
        for(int i = 0; i < step; i++) answer = Math.max(answer, marginArr[i] - C * i);


        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
