package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17130
// - DP : 각 좌표에 도착할 때 최대 당근 개수를 기준으로 탐색
public class _26_Solution_1 {
    // 좌표 클래스
    static class Point{
        int row;    // 행
        int col;    // 열
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 이동 방향
    static final int[][] DELTA = new int[][]{{0, 1}, {1, 1}, {-1, 1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 출발
        Point start = null;
        // 출구
        List<Point> end = new ArrayList<>();
        // 지도 정보 입력
        char[][] map = new char[N][M];
        for(int n = 0; n < N; n++) {
            map[n] = in.readLine().toCharArray();
            for(int m = 0; m < M; m++){
                // 출발 입력
                if(map[n][m] == 'R') {
                    start = new Point(n, m);
                    map[n][m] = '.';
                }
                // 출구 입력
                else if(map[n][m] == 'O') end.add(new Point(n, m));
            }
        }

        // 출구가 없는 경우 -1 반환
        if(end.size() == 0) sb.append(-1);
        else{
            // dp 초기화
            int[][] dp = new int[N][M];
            for(int n = 0; n < N; n++) Arrays.fill(dp[n], -1);
            // 출발점 초기화
            dp[start.row][start.col] = 0;

            // 정답 초기화
            int answer = -1;
            // 모든 좌표 탐색
            for(int m = 0; m < M; m++){
                for(int n = 0; n < N; n++){
                    // 벽인 경우 패스
                    if(map[n][m] == '#') continue;
                    for(int[] d : DELTA){
                        // 이전 좌표 계산
                        Point past = new Point(n-d[0], m-d[1]);
                        // past 좌표가 범위 내의 좌표이고, 출발점에서 도달할 수 있는 경우
                        if(check(past, N, M) && dp[past.row][past.col] != -1){
                            // 당근의 개수가 최대인 경우로 갱신!
                            if(map[n][m] == 'C') dp[n][m] = Math.max(dp[n][m], dp[past.row][past.col]+1);
                            else {
                                dp[n][m] = Math.max(dp[n][m], dp[past.row][past.col]);
                                if(map[n][m] =='O') answer = Math.max(answer, dp[n][m]);
                            }
                        }
                    }
                }
            }
            sb.append(answer);
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean check(Point point, int n, int m) {
        return 0 <= point.row && point.row < n &&
                0 <= point.col && point.col < m;
    }
}
