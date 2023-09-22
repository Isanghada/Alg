package _202309;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/60063
// - BFS 활용!
// - 구현 조건이 까다로웠다!
public class _22_Solution_1 {
    // 입출력값 static 선언
    public static int[][] board;
    public static int N;
    public static int answer;

    // 좌표를 담을 클래스
    public class Node{
        int r;  // 행 좌표
        int c;  // 열 좌표
        int type;   // 상태 (0 : 가로, 1 : 세로)
        int step;   // 이동 시간

        public Node(int r, int c, int type, int step){
            this.r = r;
            this.c = c;
            this.type = type;
            this.step = step;
        }
    }
    public int solution(int[][] board) {
        // 정답 최대값으로 초기화
        this.answer = Integer.MAX_VALUE;

        // 입력값 static으로 사용
        this.N = board.length;
        this.board = new int[N+1][N+1];
        
        // 인덱스 1 ~ N으로 사용
        for(int r = 1; r <= N; r++){
//            System.out.println(Arrays.toString(board[r-1]));
            for(int c = 1; c <= N; c++){
                this.board[r][c] = board[r-1][c-1];
            }
        }

        // 최소 이동 시간 계산
        getMinTime();
//        System.out.println("-------------");

        return this.answer;
    }

    // 기본 이동 : 상하좌우 이동
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 회전 이동
    // - __ROTATE : 중심 좌표 회전 후 좌표
    // - __TEMP : 회전 시 거쳐가는 좌표
    // - 가로일 경우 회전
    public static int[][] type0ROTATE = new int[][] {{-1, 0}, {0, 0}, {-1, 1}, {0 , 1}};
    public static int[][] type0TEMP = new int[][] {{-1, 1}, {1, 1}, {-1, 0}, {1, 0}};
    // - 세로일 경우 회전
    public static int[][] type1ROTATE = new int[][] {{0, -1}, {0, 0}, {1, -1}, {1 , 0}};
    public static int[][] type1TEMP = new int[][] {{1, -1}, {1, 1}, {0, -1}, {0, 1}};
    // 최소 이동 시간 계산 함수 : BFS 활용
    private void getMinTime() {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 행렬 초기화
        // - visited[type][r][c] : type에 따른 r, c 방문 체크 (r, c)는 중심 좌표.
        boolean[][][] visited = new boolean[2][N+1][N+1];

        // 초기값 설정
        // - (1, 1)에서 시작
        deque.offerLast(new Node(1, 1, 0, 0));
        visited[0][1][1] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 좌표 반환
            Node cur = deque.pollFirst();
//            System.out.println(cur.r+", "+cur.c+", "+ (cur.type == 0 ? "가로" : "세로")+", "+cur.step);
            
            // 차의 type에 따라 최종 좌표와 비교하여 정답일 경우 최소값으로 변경 
            if(cur.type == 0){
                if(cur.r == N && cur.c == (N-1)){
                    answer = Math.min(answer, cur.step);
                    continue;
                }
            }else {
                if (cur.r == (N - 1) && cur.c == N) {
                    answer = Math.min(answer, cur.step);
                    continue;
                }
            }

            // 상, 하, 좌, 우 이동 추가
            for(int[] d : DELTA){
                Node next = new Node(cur.r + d[0], cur.c + d[1], cur.type, cur.step+1);
                // next 기준 남은 차의 좌표!
                int[] remain = new int[] {next.r + (next.type == 0 ? 0 : 1), next.c + (next.type == 0 ? 1 : 0)};
                // 아래의 경우 패스
                // - 범위를 벗어나는 경우(next, remain)
                // - next 기준 방문한 경우
                // - 벽인 경우(next, remain)
                if(next.r <= 0 || next.r > N || next.c <= 0 || next.c > N ||
                        remain[0] <= 0 || remain[0] > N || remain[1] <= 0 || remain[1] > N ||
                        visited[next.type][next.r][next.c] ||
                        board[next.r][next.c] == 1 ||
                        board[remain[0]][remain[1]] == 1
                ) continue;
    
                // 새로운 좌표 추가
                deque.offerLast(next);
                visited[next.type][next.r][next.c] = true;
            }

            // 회전하는 경우의 이동 방향 설정
            int rotateDELTA[][] = null;
            int tempDELTA[][] = null;
            // - type에 따라 값 설정
            if(cur.type == 0){
                rotateDELTA = type0ROTATE;
                tempDELTA = type0TEMP;
            }else{
                rotateDELTA = type1ROTATE;
                tempDELTA = type1TEMP;
            }
            // 회전 후 type 계산
            int newType = cur.type == 0 ? 1 : 0;
            
            // 회전에 따라 좌표 계산
            for(int idx = 0; idx < 4; idx++){
                // 회전 후 중심 좌표 계산
                Node next = new Node(cur.r + rotateDELTA[idx][0], cur.c+rotateDELTA[idx][1], newType, cur.step+1);
                // 회전 시 지나갈 좌표 계산
                int[] temp = new int[] {cur.r + tempDELTA[idx][0], cur.c+tempDELTA[idx][1]};
                // 중심 좌표 기준 남은 차의 좌표 계산
                int[] remain = new int[] {next.r + (next.type == 0 ? 0 : 1), next.c + (next.type == 0 ? 1 : 0)};

//                System.out.println(next.r+", "+next.c+", "+ (next.type == 0 ? "가로" : "세로"));
                // 아래의 경우 패스
                // - 범위를 벗어나는 경우 : next, temp, remain
                // - next를 이미 방문한 경우
                // - 벽인 경우 : next, temp, remain
                if(next.r <= 0 || next.r > N || next.c <= 0 || next.c > N ||
                        temp[0] <= 0 || temp[0] > N || temp[1] <= 0 || temp[1] > N ||
                        remain[0] <= 0 || remain[0] > N || remain[1] <= 0 || remain[1] > N ||
                        visited[newType][next.r][next.c] ||
                        board[next.r][next.c] == 1 ||
                        board[temp[0]][temp[1]] == 1 ||
                        board[remain[0]][remain[1]] == 1
                ) continue;

                // 새로운 좌표 추가
                deque.offerLast(next);
                visited[next.type][next.r][next.c] = true;
            }
        }
    }
}
