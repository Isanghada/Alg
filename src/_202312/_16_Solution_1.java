package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23352
// - BFS : 모든 경로 확인!
public class _16_Solution_1 {
    // 구역 크기, 정답, 최대 경로 길이
    public static int N, M, ANSWER, MAX_LENGTH;
    // 구역 배열
    public static int[][] BOARD;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 구역의 크기 입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기

        // 구역 입력
        BOARD = new int[N][M];
        for(int i = 0; i < N; i++){
            BOARD[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        }

        // 정답 초기화
        ANSWER = 0;
        // 최대 경로 길이 초기화
        MAX_LENGTH = 0;
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 0인 경우 패스
                if(BOARD[r][c] == 0) continue;
                // 모든 좌표에서 비밀번호 탐색
                getPassword(r, c);
            }
        }

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }
    // 좌표 정보 클래스
    private static class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int length; // 경로 길이
        public Point(int row, int col, int length){
            this.row = row;
            this.col = col;
            this.length = length;
        }
    }
    // 이동 변수
    private static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 비밀번호 탐색 함수
    private static void getPassword(int r, int c) {
        // 시작 숫자
        int start = BOARD[r][c];

        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열
        boolean[][] visited = new boolean[N][M];

        // 초기값 설정 : (r, c)
        deque.offerLast(new Point(r, c, 0));
        visited[r][c] = true;

        // 종료 숫자
        int end = 0;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();
            // 종료 숫자 변경
            end = BOARD[cur.row][cur.col];

            // 현재 경로 길이가 길 경우
            if(cur.length > MAX_LENGTH){
                // 정답 업데이트
                ANSWER = start + end;
                // 최대 경로 길이 업데이트
                MAX_LENGTH = cur.length;
            // 현재 경로 길이와 같을 경우
            }else if(cur.length == MAX_LENGTH){
                // 정답을 최대값으로 변경
                ANSWER = Math.max(ANSWER, start + end);
            }

            // 4방향 이동!
            for(int[] d : DELTA){
                // 새로운 좌표 정보 설정
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.length+1);

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 새로운 좌표의 값이 0인 경우
                if(next.row < 0 || next.row >= N ||
                next.col < 0 || next.col >= M ||
                visited[next.row][next.col] ||
                BOARD[next.row][next.col] == 0) continue;

                // 새로운 좌표 추가
                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }
    }
}
