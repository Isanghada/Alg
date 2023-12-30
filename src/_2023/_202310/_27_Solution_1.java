package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1584
// - BFS를 통해 잃는 생명의 최소값 계산
//   - 위험 지역, 죽음 지역을 차례로 입력
//   - 우선순위 큐를 사용해 잃는 생명의 최소값이 좌표부터 차례로 탐색
public class _27_Solution_1 {
    // 이동 방향
    public static int[][] DELTA = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // 지역 정보를 담을 2차원 배열
    public static int[][] board;
    // 정답 : 잃는 생명의 최소값
    public static int answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 보드 초기화
        board = new int[501][501];
        
        // 위험 지역의 수
        int N = Integer.parseInt(in.readLine());
        // 위험 지역 입력 => 위험 지역 숫자 : 1
        setArea(1, N, in);

        // 죽음 지역의 수
        int M = Integer.parseInt(in.readLine());
        // 죽음 지역 입력 => 죽음 지역 숫자 : 2
        setArea(2, M, in);

        // for(int[] b : board) System.out.println(Arrays.toString(b));

        // 정답 초기화 : (500, 500)에 도달할 수 없는 경우 -1
        answer = -1;
        // 잃는 생명의 최소값 계산 : BFS!
        getMinHealth();

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 좌표 클래스
    private static class Point implements Comparable<Point>{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int health; // 잃는 생명의 수
        public Point(int row, int col, int health){
            this.row = row;
            this.col = col;
            this.health = health;
        }
        // 잃는 생명 기준 오름차순 정렬
        @Override
        public int compareTo(Point o) {
            return this.health - o.health;
        }
    }
    // 잃는 생명의 최소값 계산 함수 : BFS를 통해 탐색
    private static void getMinHealth() {
        // 우선순위 큐 초기화
        PriorityQueue<Point> pq = new PriorityQueue<>();
        // 방문 행렬 초기화
        boolean[][] visited = new boolean[501][501];

        // 초기값 설정 : (0, 0), 잃은 생명 0
        pq.offer(new Point(0, 0, 0));
        // 방문 표시
        visited[0][0] = true;

        // 우선순위큐가 빌 때까지 반복
        while(!pq.isEmpty()){
            // 잃는 생명이 최소인 좌표 반환
            Point cur = pq.poll();
            
            // 목적지에 도달한 경우
            if(cur.row == 500 && cur.col == 500){
                // - 잃는 생명 업데이트
                answer = cur.health;
                break;
            }

            // 4방향 탐샛!
            for(int[] d : DELTA){
                // 새로운 좌표 계산
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.health);

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 죽음 지역(2)인 경우)
                if(next.row < 0 || next.row > 500 || next.col < 0 || next.col > 500 ||
                        visited[next.row][next.col] ||
                        board[next.row][next.col] == 2
                ) continue;

                // 위험 지역(1)인 경우 : 잃은 생명 증가!
                if(board[next.row][next.col] == 1) next.health += 1;

                // 새로운 좌표 추가!
                pq.offer(next);
                visited[next.row][next.col] = true;
            }
        }
    }

    // 지역 입력 함수 : 위험 지역, 죽음 지역 입력
    // - type : 지역 타입(1-위험, 2-죽음)
    // - count : 반복 횟수(지역을 입력할 제어 변수)
    // - in : 입력 변수
    private static void setArea(int type, int count, BufferedReader in) throws Exception {
        // count만큼 반복하여 입력
        for(; count > 0; count--){
            // 좌표 입력
            int[] point = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

            // 좌표를 계산하기 편하게 변경!
            // - ([0], [1]) : 좌상단 좌표
            // - ([2], [3]) : 우하단 좌표
            if(point[0] > point[2]) swap(point, 0, 2);
            if(point[1] > point[3]) swap(point, 1, 3);

            // 직사각형 범위의 지역 타입 변경!
            for(int r = point[0]; r <= point[2]; r++){
                for(int c = point[1]; c <= point[3]; c++){
                    board[r][c] = type;
                }
            }
        }
    }

    // 좌표 변경 함수 : index1, index2의 값을 변경!
    private static void swap(int[] point, int index1, int index2) {
        int temp = point[index1];
        point[index1] = point[index2];
        point[index2] = temp;
    }
}
