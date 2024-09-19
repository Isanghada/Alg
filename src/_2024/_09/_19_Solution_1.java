package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - DFS : 순환이 생기는 구조인지 DFS를 통해 탐색
public class _19_Solution_1 {
    // 보드 크기
    public static int N, M;
    // 보드 정보
    public static char[][] BOARD;
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 방문 배열
    public static boolean[][] VISITED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기

        // 보드 정보 입력
        BOARD = new char[N][M];
        for(int r = 0; r < N; r++){
            String input = in.readLine();
            for(int c = 0; c < M; c++) BOARD[r][c] = input.charAt(c);
        }

        boolean flag = false;
        // 모든 좌표에 대해 순환이 생기는지 확인!
        for(int r = 0; r < N && !flag; r++){
            for(int c = 0; c < M; c++){
                // (r, c)를 기준으로 DFS를 통해 순환 탐색!
                VISITED = new boolean[N][M];
                VISITED[r][c] = true;
                if(dfs(r, c, r, c, 1)){
                    flag = true;
                    break;
                }
            }
        }

        // 정답 출력
        sb.append(flag ? "Yes" : "No");
        System.out.println(sb.toString());
    }

    private static boolean dfs(int startR, int startC, int curR, int curC, int count) {
        for(int[] d : DELTA){
            // 다음 좌표 계산
            int nextR = curR + d[0];
            int nextC = curC + d[1];

            // 범위를 벗어나거나 다른 색깔인 경우 패스
            if(nextR < 0 || nextR >= N ||
                    nextC < 0 || nextC >= M ||
                    BOARD[curR][curC] != BOARD[nextR][nextC]
            ) continue;

            // 방문하지 않은 좌표인 경우
            if(!VISITED[nextR][nextC]){
                // 방문 체크
                VISITED[nextR][nextC] = true;
                // 순환이 생기면 true 반환
                if(dfs(startR, startC, nextR, nextC, count+1)) return true;
            // 처음 좌표와 동일한 경우 : true 반환
            }else if(count >= 4 && startR == nextR && startC == nextC) return true;
        }

        return false;
    }
}
