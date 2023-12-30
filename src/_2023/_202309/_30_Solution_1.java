package _2023._202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1261
// - 우선순위큐를 사용한 BFS로 해결
// - 벽을 깨트린 횟수로 정렬하며 최소값에서 부터 탐색하는 방식
public class _30_Solution_1 {
    // 좌표를 담을 클래스
    // - count 기준 오름차순 정렬 설정
    public static class Node implements Comparable<Node>{
        int r;      // 행 좌표
        int c;      // 열 좌표
        int count;  // 벽을 깨트린 횟수

        public Node(int r, int c, int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    // 입력값을 담을 static 변수
    public static int N;
    public static int M;
    public static int[][] map;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202309/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 미로 크기 입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 상태 입력
        map = new int[M][N];
        for(int r = 0; r < M; r++){
            String mapValue = in.readLine();
            for(int c = 0; c < N; c++) {
                map[r][c] = mapValue.charAt(c) - '0';
            }
        }

        // 최소값 반환
        sb.append(getMinCount());
        System.out.println(sb);
    }

    // 이동 변수 : 상, 하, 좌, 우
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // BFS 탐색 함수 : 최소 값 탐색
    private static int getMinCount() {
        // 정답 초기화
        int answer = 0;

        // 우선순위큐, 방문 2차원 배열 선언
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        boolean[][] visited = new boolean[M][N];

        // 초기값 설정 : (0, 0) 좌표
        priorityQueue.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        // 우선순위큐가 빌 때까지 반복
        while(!priorityQueue.isEmpty()){
            // 현재 값 반환
            Node cur = priorityQueue.poll();
//            System.out.println(cur.r+", "+cur.c+", "+cur.count);

            // 목적지에 도착하면 값 입력 후 종료
            if((cur.r == (M - 1)) && (cur.c == (N - 1))){
                answer = cur.count;
                break;
            }

            // 다음 좌표 계산
            for(int[] d : DELTA){
                // 상, 하, 좌, 우에 대해 다음 좌표 계산
                Node next = new Node(cur.r+d[0], cur.c+d[1], cur.count);

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                if(next.r < 0 || next.r >= M || next.c < 0 || next.c >= N ||
                        visited[next.r][next.c]
                ) continue;

                // 벽이 있는 경우 count 증가
                if(map[next.r][next.c] == 1) next.count++;
//                System.out.println(cur.r+", "+cur.c+", "+cur.count+", "+map[next.r][next.c]);
                // 우선순위큐에 추가
                priorityQueue.offer(next);
                visited[next.r][next.c] = true;
            }
        }

        return answer;
    }
}
