package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25417
// - BFS : 너비 우선 탐색을 통해 최소 이동 횟수 계산
public class _09_Solution_1 {
    public static final int SIZE = 5;   // 보드의 크기
    public static int ANSWER;           // 정답
    public static int[] startPoint;     // 시작 좌표
    public static int[][] MAP;          // 보드
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 보드 초기화
        MAP = new int[SIZE][SIZE];
        // 보드 입력
        for(int r = 0; r < SIZE; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0;c < 5; c++) MAP[r][c] = Integer.parseInt(st.nextToken());
        }

        // 시작 좌표
        st = new StringTokenizer(in.readLine());
        startPoint = new int[]{Integer.parseInt(st.nextToken()),
                               Integer.parseInt(st.nextToken())};

        // 정답 초기화
        ANSWER = Integer.MAX_VALUE;
        // 최소 이동 횟수 계산
        getMinCount();

        // 정답 출력
        // - 이동할 수 없는 경우 -1, 이동할 수 있는 경우 최소 횟수 반환
        sb.append(ANSWER == Integer.MAX_VALUE ? -1 : ANSWER);
        System.out.println(sb);
    }
    // 노드 클래스
    private static class Node implements Comparable<Node>{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int count;  // 이동 횟수
        public Node(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
        @Override
        public int compareTo(Node o){
            return this.count - o.count;
        }
        @Override
        public String toString(){
            return "[ "+this.row+", "+this.col+", "+this.count+" ]";
        }
    }
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static void getMinCount() {
        // 우선순위큐 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 방문 배열
        boolean[][] visited = new boolean[SIZE][SIZE];

        // 시작 지점 설정
        pq.offer(new Node(startPoint[0], startPoint[1], 0));

        // 우선순위큐가 빌 때까지 반복
        while(!pq.isEmpty()){
            // 현재 노드 반환
            Node cur = pq.poll();
            
            // 방문한 노드인 경우 패스
            if(visited[cur.row][cur.col]) continue;

            // 방문 표시
            visited[cur.row][cur.col] = true;
//            System.out.println(cur.row+", "+cur.col+", "+cur.count);

            // 도착 지점일 경우 : 정답 갱신 후 종료
            if(MAP[cur.row][cur.col] == 1){
                ANSWER = cur.count;
                break;
            }

            // 다음 좌표 탐색
            for(int[] d : DELTA){
                // 1칸 이동하는 경우
                int nextRow = cur.row+d[0];
                int nextCol = cur.col+d[1];

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 이미 방문한 경우
                // - 좌표의 값이 -1인 경우
                if(nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5 ||
                visited[nextRow][nextCol] ||
                MAP[nextRow][nextCol] == -1) continue;

                // 노드 추가!
                pq.offer(new Node(nextRow, nextCol, cur.count+1));

                // 달리는 경우
                // - 범위를 벗어나는 경우
                // - -1인 경우
                // - 7인 경우
                while(nextRow >= 0 && nextRow < 5 && nextCol >= 0 && nextCol < 5 &&
                        MAP[nextRow][nextCol] != -1 && MAP[nextRow][nextCol] != 7){
                    nextRow += d[0];
                    nextCol += d[1];
                }

                // 범위를 벗어나거나 좌표 값이 -1인 경우 위치 조정
                if(nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5 ||
                        MAP[nextRow][nextCol] == -1){
                    nextRow -= d[0];
                    nextCol -= d[1];
                }

                // 방문하지 않은 경우 : 노드 추가
                if(!visited[nextRow][nextCol]) pq.offer(new Node(nextRow, nextCol, cur.count+1));
            }
        }
    }
}
