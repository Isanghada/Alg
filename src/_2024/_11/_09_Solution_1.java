package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17141
// - BFS : 바이러스를 놓을 수 있는 모든 경우에서 BFS를 통해 최소 시간 탐색
public class _09_Solution_1 {
    // 좌표 클래스
    public static class Point{
        int row;    // 행
        int col;    // 열
        int time;   // 시간
        public Point(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    // 최대 시간
    public static final int MAX = 1_000_000;
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 연구소 크기
        int M = Integer.parseInt(st.nextToken());   // 바이러스 개수

        // 바이러스가 퍼질 수 있는 칸의 수
        int total = 0;
        // 바이러스 시작점 정보
        List<Point> virusList = new ArrayList<>();
        // 연구소 정보 입력
        int[][] board = new int[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
                // 바이러스가 아닌 경우 total 증가
                if(board[r][c] != 1) {
                    // 바이러스 시작점인 경우 리스트에 추가
                    if(board[r][c] == 2) virusList.add(new Point(r, c, 0));
                    total++;
                }
            }
        }

        // 바이러스 시작점! : M개를 선택하는 조합 활용
        int[] used = new int[virusList.size()];
        for(int use = used.length-M; use < used.length; use++) used[use] = 1;

        // 정답 초기화
        int answer = MAX;
        // nextPermutation으로 M개를 선택하는 모든 조합 탐색
        do{
            int time = 0;   // 시간
            int count = 0;  // 바이러스가 퍼진 칸의 수
            
            // 덱 초기화
            Deque<Point> deque = new LinkedList<>();
            // 방문 배열
            boolean[][] visited = new boolean[N][N];

            // 초기값 설정 : used의 값이 1인 경우 바이러스 시작점!
            for(int use = 0; use < used.length; use++){
                if(used[use] == 1){
                    deque.offerLast(virusList.get(use));
                    visited[virusList.get(use).row][virusList.get(use).col] = true;
                }
            }

            // 덱이 빌 때까지 반복
            while(!deque.isEmpty()){
                // 현재 바이러스 반환
                Point cur = deque.pollFirst();

                // 시간 갱신
                time = cur.time;
                // 바이러스가 퍼진 칸의 수 증가
                count++;
                // 다음 칸 탐색
                for(int[] d : DELTA){
                    // 다음 칸 계산
                    Point next = new Point(cur.row+d[0], cur.col+d[1], cur.time+1);

                    // 아래의 경우 패스
                    // - 범위를 벗어난 경우
                    // - 이미 방문한 경우
                    // - 벽인 경우
                    if(next.row < 0 || next.row >= N ||
                            next.col < 0 || next.col >= N ||
                            visited[next.row][next.col] ||
                            board[next.row][next.col] == 1
                    ) continue;

                    // 새로운 칸의 정보 추가
                    deque.offerLast(next);
                    visited[next.row][next.col] = true;
                }
            }

            // 모든 칸에 바이러스가 퍼진 경우 정답 갱신
            if(count == total) answer = Math.min(answer, time);
        }while(nextPermutation(used));

        // 정답 출력
        sb.append(answer == MAX ? -1 : answer);
        System.out.println(sb);
    }

    private static boolean nextPermutation(int[] used) {
        int len = used.length;

        int i = len - 1;
        while(i > 0 && used[i] <= used[i-1]) i--;
        if(i == 0) return false;

        int j = len - 1;
        while(i < j && used[j] <= used[i-1]) j--;

        swap(used, i-1, j);

        int k = len - 1;
        while(i < k) swap(used, i++, k--);

        return true;
    }

    private static void swap(int[] used, int i, int j) {
        int temp = used[i];
        used[i] = used[j];
        used[j] = temp;
    }
}
