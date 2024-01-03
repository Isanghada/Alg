package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14271
// - BFS : 초기 기준에서 살아있는 공간을 넓혀가며 계산
public class _03_Solution_1 {
    private static class Point{
        int row;
        int col;
        int time;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
            this.time = 0;
        }
        public Point(int row, int col, int time){
            this(row, col);
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 그리드 크기 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N =  Integer.parseInt(st.nextToken());  // 행 크기
        int M =  Integer.parseInt(st.nextToken());  // 열 크기

        // 그리드 정보 입력
        List<Point> list = new ArrayList<>();
        // 살아있는 공간의 좌표만 리스트에 추가
        for(int r = 0; r < N; r++){
            char[] input=  in.readLine().toCharArray();
            for(int c = 0;c < M; c++){
                if(input[c] == 'o') list.add(new Point(r, c));
            }
        }

        // 기준 시간 입력
        int K = Integer.parseInt(in.readLine());

        // 정답 반환
        // - 초기 그리드에서 K초 후 살아있는 공간 개수 반환
        sb.append(getAlivePoint(K, N, M, list));
        System.out.println(sb);
    }
    // 이동 방향 변수
    private static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 살아있는 공간 계산 함수 : BFS
    // - k : 기준 시간
    // - n : 초기 행 크기
    // - m : 초기 열 크기
    // - list : 초기 살아있는 공간 좌표
    private static int getAlivePoint(int k, int n, int m, List<Point> list) {
        // 카운트 초기화
        int count = 0;
        // k, n, m으로 k초 후의 그리드 크기 계산
        final int ROW = n+2*k;
        final int COL = m+2*k;

        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[ROW][COL];

        // 초기 공간 설정
        for(Point cur : list){
            // k만큼 좌표 증가!
            cur.row += k;
            cur.col += k;
            // 덱에 추가
            deque.offerLast(cur);
            // 방문 표시
            visited[cur.row][cur.col] = true;
        }

        // 덱이 빌 떄까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();
            // 카운트 증가
            count++;
            // 기준 시간 이상일 경우 패스!
            if(cur.time >= k) continue;
            // 다음 좌표 탐색!
            for(int[] d : DELTA){
                // 다음 좌표 계산
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.time+1);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 이미 방문한 경우
                if(next.row < 0 || next.row >= ROW || next.col < 0 || next.col >= COL ||
                        visited[next.row][next.col]
                ) continue;

                // 덱에 추가
                deque.offerLast(next);
                // 방문 표시
                visited[next.row][next.col] = true;
            }
        }

//        for(boolean[] b : visited){
//            for(boolean a : b) System.out.print(a ? "o" : ".");
//            System.out.println();
//        }

        // 살아있는 공간의 수 반환
        return count;
    }
}
