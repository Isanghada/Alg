package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25688
// - 브루트포스, BFS
//   1. BFS로 각 좌표에서 다른 좌표로 이동하는 최소 거리 계산
//   2. 가능한 모든 경로 탐색
public class _27_Solution_1 {
    // MAP : 보드
    // ADJARR : (1, 2, 3, 4, 5, 6)의 인접 행렬
    public static int[][] MAP, ADJARR;
    // 좌표 클래스
    public static class Point{
        int num;    // 숫자
        int row;    // 행
        int col;    // 열
        int step;   // 이동 거리
        public Point(int num, int row, int col, int step){
            this.num = num;
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        List<Point> numList = new ArrayList<>();    // 숫자 좌표 리스트
        MAP = new int[5][5];
        // 보드 입력
        for(int row = 0; row < 5; row++){
            st = new StringTokenizer(in.readLine());
            for(int col = 0; col < 5; col++) {
                MAP[row][col] = Integer.parseInt(st.nextToken());
                // 0보다 큰 숫자는 리스트에 추가
                if(MAP[row][col] > 0) numList.add(new Point(MAP[row][col], row, col, 0));
            }
        }

        // 인접 행렬 초기화
        ADJARR = new int[7][7];
        for(int num = 1; num <= 6; num++) {
            Arrays.fill(ADJARR[num], 10000);
            ADJARR[num][num] = 0;
        }
        // 인접 행렬 계산
        for(Point cur : numList) {
            int[] result = bfs(cur);
            for(int next = 1; next <= 6; next++) ADJARR[cur.num][next] = result[next];
        }

        // 시작 좌표 입력
        st = new StringTokenizer(in.readLine());
        Point start = new Point(0,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                0);
        start.num = MAP[start.row][start.col];

        // 시작 좌표에서 각 숫자로 이동하는 거리 계산
        int[] startArr = bfs(start);

        // 이동 경로 초기화 : 1 -> 2 -> 3 -> 4 -> 5 -> 6
        int[] route = new int[6];
        for(int idx = 0; idx < 6; idx++) route[idx] = idx+1;

        // 정답 초기화 : 10000일 경우 이동할 수 없는 경우
        int answer = 10000;
        // nextPermutation으로 모든 이동 경로 차레로 탐색
        do{
            // 시작 좌표에서 첫 경로로 이동
            int sum = startArr[route[0]];
            // 이동할 수 없는 경우 패스
            if(sum == 10000) continue;

            // 이동 가능 여부 플래그
            boolean flag = true;
            // 모든 좌표로 차례로 이동
            for(int idx = 1; idx < 6; idx++){
                // 이동할 수 없는 경우
                // - 플래그 변경
                // - 반복 종료
                if(ADJARR[route[idx-1]][route[idx]] == 10000){
                    flag = false;
                    break;
                }
                sum += ADJARR[route[idx-1]][route[idx]];
            }
            // 이동 가능한 경로인 경우 정답 최소값으로 변경
            if(flag) answer = Math.min(answer, sum);
        }while(nextPermutation(route));

        // 정답 반환
        sb.append(answer != 10000 ? answer : -1);
        System.out.println(sb);
    }
    // nPn 함수 : 사전순으로 가능한 순열 탐색
    public static boolean nextPermutation(int[] route){
        int n = 6;

        int i = n - 1;
        while(i > 0 && route[i - 1] > route[i]){
            i--;
        }

        if(i == 0) return false;

        int j = n - 1;
        while(j > i && route[i-1] > route[j]){
            j--;
        }

        int temp = route[i - 1];
        route[i-1] = route[j];
        route[j] = temp;

        Arrays.sort(route, i, n);

        return true;
    }
    // 이동 변수
    private static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // BFS 함수 : start를 기준으로 (1, 2, 3, 4, 5, 6)으로 이동하는 최소 거리 계산
    private static int[] bfs(Point start) {
        // 정답 초기화
        int[] result = new int[7];
        Arrays.fill(result, 10000);

        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 행렬
        boolean[][] visited = new boolean[5][5];

        // 초기값 설정
        deque.offerLast(start);
        visited[start.row][start.col] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Point cur = deque.pollFirst();

            // 0보다 큰 숫자일 경우 result 업데이트
            if(cur.num > 0){
                result[cur.num] = cur.step;
            }

            // 가능한 모든 방향으로 이동
            for(int[] d : DELTA){
                // 다음 좌표 계산
                Point next = new Point(0,
                        cur.row+d[0],
                        cur.col+d[1],
                        cur.step+1);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 이미 방문한 경우
                // - 이동할 수 없는 위치일 경우
                if(next.row < 0 || next.row >= 5 ||
                next.col < 0 || next.col >= 5 ||
                visited[next.row][next.col] ||
                MAP[next.row][next.col] == -1) continue;

                // 숫자 변경
                next.num = MAP[next.row][next.col];
                // 새로운 좌표 추가
                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }

        return result;
    }
}
