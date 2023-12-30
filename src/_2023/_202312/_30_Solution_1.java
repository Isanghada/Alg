package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30508
// - BFS, 누적합 : BFS로 웅덩이를 찾고, 누적합으로 범위에 웅덩이가 있는지 확인
public class _30_Solution_1 {
    // VISITED : 방문 배열
    public static boolean[][] VISITED;
    // MAP : 횡단보도 배열
    // SUM : 웅덩이 누적합 배열
    // DELTA : 이동 변수
    public static int[][] MAP, SUM, DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // N : 행 크기
    // M : 열 크기
    // H : 신발 행 크기
    // W : 신발 열 크기
    // K : 웅덩이 개수
    public static int N, M, H, W, K;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 횡단보도 크기 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 행 크기
        M = Integer.parseInt(st.nextToken());   // 열 크기

        VISITED = new boolean[N+1][M+1];    // 배열 초기화
        MAP = new int[N+1][M+1];    // 배열 초기화
        SUM = new int[N+1][M+1];    // 배열 초기화

        // 신발 크기 입력
        st = new StringTokenizer(in.readLine());
        H = Integer.parseInt(st.nextToken());   // 행 크기
        W = Integer.parseInt(st.nextToken());   // 열 크기

        // 횡단 보도 정보 입력
        for(int r = 1; r <= N; r++)
            MAP[r] = Arrays.stream(("0 "+in.readLine()).split(" "))
                    .mapToInt(Integer::new).toArray();

        // 웅덩이 개수 입력
        K = Integer.parseInt(in.readLine());
        // 웅덩이 정보 입력 : 물이 빠지는 칸 계산
        List<Point> list=  new ArrayList<>();
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Point(r, c, MAP[r][c]));
        }

        BFS(list);

        // 누적합 계산 : 범위의 웅덩이 유무를 확인하기 위해 계산
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= M; c++){
                SUM[r][c] = SUM[r-1][c] + SUM[r][c-1] - SUM[r-1][c-1] + (MAP[r][c] == 0 ? 0 : 1);
            }
        }

        // 정답 초기화
        int answer = 0;
        // 모든 범위를 확인해 딛을 수 있는 공간의 개수 체크
        for(int r = H; r <= N; r++){
            for(int c = W; c <= M; c++){
                // 범위의 누적합 계산
                int sum = SUM[r][c] - SUM[r-H][c] - SUM[r][c-W] + SUM[r-H][c-W];
                // 웅덩이가 없는 경우 정답 증가
                if(sum == 0) answer++;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 좌표 클래스
    private static class Point{
        int row;    // 행
        int col;    // 열
        int height; // 높이

        public Point(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    private static void BFS(List<Point> list) {
        // 초기값 설정
        Deque<Point> deque = new LinkedList<>();
        for(Point root : list){
            VISITED[root.row][root.col] = true;
            deque.offerLast(new Point(root.row, root.col, root.height));
        }

        // 덱이 빌 때가지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();

            // 물 빠짐 표시
            MAP[cur.row][cur.col] = 0;
            // 4방향 탐색
            for(int[] d : DELTA){
                // 다음 좌표 계산
                Point next = new Point(cur.row+d[0], cur.col+d[1], 0);
                
                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 다음 좌표의 높이가 현재 높이보다 작을 경우
                if(next.row < 1 || next.row > N || next.col < 1 || next.col > M ||
                        VISITED[next.row][next.col] ||
                        MAP[next.row][next.col] < cur.height) continue;

                // 다음 좌표 높이 설정
                next.height = MAP[next.row][next.col];
                VISITED[next.row][next.col] = true;
                // 덱에 추가
                deque.offerLast(next);
            }
        }
    }
}