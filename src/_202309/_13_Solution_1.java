package _202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2531
// - BFS를 통해 빙산의 영역 확인
public class _13_Solution_1 {
    public static int[][] BOARD;    // 빙산 배열
    public static int N;    // 행 크기
    public static int M;    // 열 크기

    public static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202309/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 배열의 크기 입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기

        // 빙산 배열 입력
        BOARD = new int[N][M];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++) BOARD[r][c] = Integer.parseInt(st.nextToken());
        }

        int answer = 0; // 정답 초기화
        int year = 0;   // 시간!
        while(true){
            // BFS를 통해 분리되었는지 확인
            int flag =  isSeparated();
            // 1개 이상인 경우 분리되었으므로 year을 정답에 입력하고 종료
            if(flag > 1){
                answer = year;
                break;
            }
            // 1인 경우 분리되지 않은 경우 이므로 다음 년도 확인
            else if(flag == 1) {
                // 빙산이 녹는 함수
                melting();
                year++;
            }
            // 0인 경우 빙산이 다 녹은 것이므로 종료
            else break;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 빙산을 녹이기 위한 함수
    private static void melting() {
        // 각 좌표별 녹는 정도를 담을 배열
        int[][] melt = new int[N][M];
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 빙산이 다 녹은 좌표면 패스
                if(BOARD[r][c] == 0) continue;
                // 좌표 기준 상, 하, 좌, 우를 확인하여 녹는 정도 계산
                if(r - 1 >= 0 && BOARD[r-1][c] == 0) melt[r][c]++;
                if(r + 1 < N && BOARD[r+1][c] == 0) melt[r][c]++;
                if(c - 1 >= 0 && BOARD[r][c-1] == 0) melt[r][c]++;
                if(c + 1 < M && BOARD[r][c+1] == 0) melt[r][c]++;
            }
        }

        // 좌표별로 빙산을 녹임!
        for(int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 0보다 작아질 수도 있으므로 녹은 것과 0 중 최대값으로 설정
                BOARD[r][c] = Math.max(BOARD[r][c]- melt[r][c], 0);
            }
        }
    }

    // 좌표 이동 함수
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 빙산 분리 확인 함수 : BFS를 통해 빙산의 영역 확인
    private static int isSeparated() {
        // 빙산 영역의 개수
        int count = 0;

        // 방문 체크 함수
        boolean[][] visited = new boolean[N][M];

        // 모든 좌표에서 검사
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 빙산이 다 녹았거나 방문한 곳은 패스
                if(BOARD[r][c] == 0 || visited[r][c]) continue;

                // 새로운 빙산 영역이므로 count 증가
                count++;
                // 빙산 영역이 1개 초과일 경우 종료!
                if(count > 1) return count;
                
                // 덱 초기화
                Deque<Point> deque = new LinkedList<>();
                
                // 초기값 설정
                // - 덱에 현재 좌표 추가
                deque.offerLast(new Point(r, c));
                // - 현재 좌표 방문 표시
                visited[r][c] = true;

                // 덱이 빌 때까지 반복
                while(!deque.isEmpty()){
                    // 현재 좌표 반환
                    Point cur = deque.pollFirst();

                    // 상, 하, 좌, 우로 새로운 좌표 확인
                    for(int[] d : DELTA){
                        Point next = new Point(cur.row+d[0], cur.col+d[1]);

                        // 아래의 경우 패스
                        // - 행 범위를 벗어나는 경우
                        // - 열 범위를 벗어나는 경우
                        // - 빙산이 다 녹은 경우
                        // - 이미 방문한 경우
                        if(next.row < 0 || next.row >= N ||
                        next.col < 0 || next.col >= M ||
                        BOARD[next.row][next.col] == 0 ||
                        visited[next.row][next.col])
                            continue;

                        // 새로운 좌표를 덱에 추가
                        deque.offerLast(next);
                        // 새로운 좌표 방문 표시
                        visited[next.row][next.col] = true;
                    }
                }
            }
        }
        
        // 빙산 영역 개수 반환
        return count;
    }
}
