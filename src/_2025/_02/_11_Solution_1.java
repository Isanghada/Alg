package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20165
// - 구현 : 순서대로 공격, 수비를 진행하여 체크
public class _11_Solution_1 {
    // 동, 서, 남 북
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행
        int M = Integer.parseInt(st.nextToken());   // 열
        int R = Integer.parseInt(st.nextToken());   // 라운드

        // 게임판 상탱 입력 
        int[][] board = new int[N+1][M+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= M; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // 동, 서, 남, 북 인덱스
        Map<String, Integer> deltaIndex = new HashMap<>();
        deltaIndex.put("E", 0);
        deltaIndex.put("W", 1);
        deltaIndex.put("S", 2);
        deltaIndex.put("N", 3);
        
        // 공격수 점수
        int answer = 0;
        // 도미노 상태
        boolean[][] check = new boolean[N+1][M+1];
        while(R-- > 0){
            // 공격
            st = new StringTokenizer(in.readLine());
            int X = Integer.parseInt(st.nextToken());   // 행
            int Y = Integer.parseInt(st.nextToken());   // 열
            String D = st.nextToken();                  // 방향

            // 도미노 상태에 따라 범위 계산
            int count = check[X][Y] ? 0 : board[X][Y];
            // 범위내 도미노 공격
            while(count > 0 && X > 0 && X <= N &&
                    Y > 0 && Y <= M){
                if(!check[X][Y]){
                    answer++;

                    // 새로운 범위 계산!
                    count = Math.max(count, board[X][Y]);
                    check[X][Y] = true;
                }
                // 좌표 갱신!
                count--;
                X += DELTA[deltaIndex.get(D)][0];
                Y += DELTA[deltaIndex.get(D)][1];
            }

            // 수비
            st = new StringTokenizer(in.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            if(check[X][Y]) check[X][Y] = false;
        }

        // 점수 및 게임판 상태 출력
        sb.append(answer).append("\n");
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= M; c++) sb.append(check[r][c] ? "F " : "S ");
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
