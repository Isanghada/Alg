package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16973
// - BFS : BFS를 통해 최소 이동 경로 탐색
//         - 벽의 유무는 누적합을 통해 직사각형 범위 체크!
public class _24_Solution_1 {
    static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 격자 행 크기
        int M = Integer.parseInt(st.nextToken());   // 격자 열 크기

        // 격자 정보
        int[][] board = new int[N+1][M+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= M; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // 누적합 : 벽의 유무 계산에 활용
        prefixSum(board, N, M);

        st = new StringTokenizer(in.readLine());
        // 직사각형 크기
        int[] rectangle = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        // 시작 위치
        int[] S = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        // 도착 위치
        int[] F = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };

        // 정답 출력
        // - bfs를 통해 최소 이동 경로 탐색
        sb.append(bfs(board, N, M, rectangle, S, F));
        System.out.println(sb);
    }

    private static void prefixSum(int[][] board, int n, int m) {
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= m; c++) board[r][c] += board[r][c-1];
        }

        for(int c = 1; c <= m; c++){
            for(int r = 1; r <= n; r++) board[r][c] += board[r-1][c];
        }
    }

    private static int bfs(int[][] board, int n, int m, int[] rectangle, int[] s, int[] f) {
        // 시작 위치가 가능한지 체크
        if(check(board, n, m, rectangle, s)){
            // 덱 초기화
            Deque<int[]> deque = new LinkedList<>();
            // 방문 배열 초기화
            boolean[][] visited = new boolean[n+1][m+1];

            // 초기값 설정
            deque.offerLast(new int[]{s[0], s[1], 0});
            visited[s[0]][s[1]] = true;

            while(!deque.isEmpty()){
                // 현재 좌표 반환
                int[] cur = deque.pollFirst();

                // 도착 위치인 경우 이동 횟수 반환
                if(cur[0] == f[0] && cur[1] == f[1]) return cur[2];

                // 다음 좌표 탐색
                for(int[] d : DELTA){
                    int[] next = new int[]{cur[0]+d[0], cur[1]+d[1], cur[2]+1};

                    // 아래의 경우 패스
                    // - 직사각형이 가능한지 체크
                    //      1. 범위를 벗어나는 경우
                    //      2. 벽이 존재하는 경우
                    // - 방문한 경우
                    if(!check(board, n, m, rectangle, next) ||
                            visited[next[0]][next[1]]
                    ) continue;

                    // 좌표 추가
                    deque.offerLast(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }

        return -1;
    }

    private static boolean check(int[][] board, int n, int m, int[] rectangle, int[] target) {
        int[] point = new int[]{target[0], target[1], target[0]+rectangle[0]-1, target[1]+rectangle[1]-1};

        // 왼쪽 위, 오른쪽 아래가 범위를 벗어나면 불가능
        if(point[0] < 1 || point[1] < 1 || point[2] > n || point[3] > m) return false;

        // 직사각형 범위의 벽 개수
        int sum = board[point[2]][point[3]]
                - board[point[2]][point[1]-1]
                - board[point[0]-1][point[3]]
                + board[point[0]-1][point[1]-1];

        // 벽이 존재하면 불가능
        if(sum > 0) return false;

        return true;
    }
}
