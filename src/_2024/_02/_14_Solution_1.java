package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25513
// - BFS : 너비 우선 탐색을 통해 순서대로 이동하는 최소 횟수 탐색!
public class _14_Solution_1 {
    // 좌표 클래스
    private static class Node{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int num;    // 방문한 숫자!
        int count;  // 이동 횟수
        public Node(int row, int col, int num, int count){
            this.row = row;
            this.col = col;
            this.num = num;
            this.count = count;
        }
    }
    public static final int SIZE = 5;   // 보드 크기
    public static int[][] BOARD;        // 보드 배열
    // 이동 변수
    private static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 보드 초기화
        BOARD = new int[SIZE][SIZE];
        StringTokenizer st = null;
        // 보드 입력
        for(int row = 0; row < SIZE; row++){
            st = new StringTokenizer(in.readLine());
            for(int col = 0; col < SIZE; col++) BOARD[row][col] = Integer.parseInt(st.nextToken());
        }

        // 시작 위치 입력
        st = new StringTokenizer(in.readLine());
        int[] startPoint = new int[]{Integer.parseInt(st.nextToken()),
                                     Integer.parseInt(st.nextToken())};

        // 정답 반환
        // - BFS를 통해 최소 이동 횟수 계산
        sb.append(getMinCount(startPoint));
        System.out.println(sb);
    }
    // BFS : 너비 우선 탐색 함수
    private static int getMinCount(int[] startPoint) {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[SIZE][SIZE];

        // startPoint로 초기값 설정
        deque.offerLast(new Node(startPoint[0], startPoint[1], 0, 0));
        visited[startPoint[0]][startPoint[1]] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 노드 반환
            Node cur = deque.pollFirst();
//            System.out.println("[ "+cur.row+", "
//                                   +cur.col +", "
//                                   +cur.num +", "
//                                   +cur.count +", "
//                                   +BOARD[cur.row][cur.col] +" ]");

            // 다음 순서에 도달한 경우!
            if(BOARD[cur.row][cur.col] == (cur.num+1)){
                // 덱 초기화
                deque.clear();
                // 방문 배열 초기화
                visited = new boolean[SIZE][SIZE];
                // 현재 위치 방문 표시
                visited[cur.row][cur.col] = true;

                // 숫자 변경!
                cur.num += 1;
                // 6번까지 방문한 경우 횟수 반환
                if(cur.num == 6) return cur.count;
            }
            // 4방향 이동하며 탐색
            for(int[] d : DELTA){
                // 다음 노드 생성
                Node next = new Node(cur.row+d[0], cur.col+d[1], cur.num, cur.count+1);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 이미 방문한 경우
                // - 방문할 수 없는 지역인 경우
                if(next.row < 0 || next.row >= 5 || next.col < 0 || next.col >= 5 ||
                        visited[next.row][next.col] ||
                        BOARD[next.row][next.col] == -1
                ) continue;
                
                // 덱에 노드 추가
                deque.offerLast(next);
                // 방문 표시
                visited[next.row][next.col] = true;
            }
        }
        // 순서대로 방문할 수 없는 경우 -1 반환
        return -1;
    }
}

