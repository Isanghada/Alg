package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2589
// - BFS로 해결 : 모든 좌표에서 최대 시간을 구하여 비교
public class _30_Solution_1 {
    public static class Node{
        int r;      // 행 좌표
        int c;      // 열 좌표
        int time;  // 이동한 시간

        public Node(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    // 입력값을 담을 static 변수
    public static int N;
    public static int M;
    public static char[][] map;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 미로 크기 입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 상태 입력
        map = new char[N][M];
        for(int r = 0; r < N; r++) map[r] = in.readLine().toCharArray();

        // 정답 초기화
        int answer = 0;

        // 모든 좌표에서 탐색
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                // 바다인 경우 패스
                if(map[r][c] == 'W') continue;
                // 육지인 경우 가장 오래 걸리는 경우와 현재 값 중 최대값 선택
                answer = Math.max(answer, getMaxTime(r, c));
            }
        }
        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 이동 변수 : 상, 하, 좌, 우
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // BFS 탐색 함수 : 최대 값 탐색
    private static int getMaxTime(int r, int c) {
        // 시간 초기화
        int time = 0;

        // 덱, 방문 2차원 배열 선언
        Deque<Node> deque = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // 초기값 설정 : (r, c) 좌표
        deque.offerLast(new Node(r, c, 0));
        visited[r][c] = true;

        // 덱가 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 값 반환
            Node cur = deque.pollFirst();

            // 시간을 최대값으로 변경
            time = Math.max(time, cur.time);

            // 다음 좌표 계산
            for(int[] d : DELTA){
                // 상, 하, 좌, 우에 대해 다음 좌표 계산
                Node next = new Node(cur.r+d[0], cur.c+d[1], cur.time + 1);

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 바다인 경우
                if(next.r < 0 || next.r >= N || next.c < 0 || next.c >= M ||
                        visited[next.r][next.c] ||
                        map[next.r][next.c] == 'W'
                ) continue;

                // 덱에 추가
                deque.offerLast(next);
                visited[next.r][next.c] = true;
            }
        }

        return time;
    }
}