package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2411
// - DP : 아이템의 위치를 정렬하여 차례로 방문할 수 있는 경우의 수 체크
//        - 이동 방향이 (오른쪽, 위)이므로 모든 아이템을 획득할 수 있는 방문 순서는 1개
public class _23_Solution_1 {
    // 좌표 클래스
    static class Point implements Comparable<Point>{
        int r;  // 행
        int c;  // 열
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        // 행 기준 오름차순, 열 기준 오름차순
        @Override
        public int compareTo(Point p){
            if(this.r == p.r) return this.c - p.c;
            return this.r - p.r;
        }
        @Override
        public String toString(){
            return "[ r="+this.r+", c="+this.c+" ]";
        }
    }
    static final int[][] DELTA = new int[][]{{0, 1}, {1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int A = Integer.parseInt(st.nextToken());   // 아이템의 수
        int B = Integer.parseInt(st.nextToken());   // 장애물의 수

        // 아이템 정보 입력
        // - 시작 (1, 1), 끝 (N, M) 좌표 추가!
        Point[] items = new Point[A+2];
        items[0] = new Point(1, 1);
        items[A+1] = new Point(N, M);
        for(int i = 1; i <= A; i++){
            st = new StringTokenizer(in.readLine());
            items[i] = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 아이템 정렬
        Arrays.sort(items);
        // System.out.println(Arrays.toString(items));

        // 맵 정보 입력 : 장애물 => -1로 표현
        int[][] board = new int[N+1][M+1];
        board[1][1] = 1;    // 시작 좌표의 경우의 수 1
        while(B-- > 0){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = -1;
        }

        // for(int[] b : board) System.out.println(Arrays.toString(b));
        // System.out.println("-------------");

        boolean flag = true;
        for(int item = 1; item < items.length; item++){
            // 아이템 정렬 순서에 따라 차례로 방문할 수 있는 경우의 수 체크
            Point start = items[item-1];    // 시작 좌표
            Point end = items[item];        // 도착 좌표

            for(int r = start.r; r <= end.r; r++){
                for(int c = start.c; c <= end.c; c++){
                    // 시작 좌표, 장애물인 경우 패스
                    if((r == start.r && c == start.c ) || board[r][c] == -1) continue;

                    // 현재 좌표 (r, c)에 도착할 수 있는 경우의 수 계산
                    for(int[] d : DELTA){
                        int pastR = r-d[0];
                        int pastC = c-d[1];

                        if(pastR <= end.r && pastC <= end.c
                                && board[pastR][pastC] != -1
                        ) board[r][c] += board[pastR][pastC];
                    }
                }
            }
            // 도착 좌표에 방문하는 경우의 수가 0 이하인 경우 : flag 갱신 후 종료
            // - 방문할 수 없는 경우
            // - 장애물이 있는 경우
            if(board[end.r][end.c] <= 0){
                flag = false;
                break;
            }
        }

        // for(int[] b : board) System.out.println(Arrays.toString(b));
        
        // 정답 출력
        sb.append(flag ? board[N][M] : 0);
        System.out.println(sb);
    }
}
