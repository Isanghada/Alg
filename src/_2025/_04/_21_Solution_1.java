package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20164
// - BFS : 다오의 위치에서 가능한 이동 모두 체크!
//          디즈니의 위치에 도달할 경우 이동한 방식 출력
public class _21_Solution_1 {
    // 노드 클래스 : 다오의 좌표
    static class Node{
        int h;          // 행 좌표
        int w;          // 열 좌표
        String move;    // 이동 방식
        public Node(int h, int w, String move){
            this.h = h;
            this.w = w;
            this.move = move;
        }
    }
    // 정답 클래스
    static class Answer{
        boolean flag;   // 도달 여분
        String move;    // 이동 방식
        public Answer(){
            this.flag = false;
            this.move = "";
        }
        public Answer(String move){
            this.flag = true;
            this.move = move;
        }
    }
    // 이동 방향
    static final int[][] DELTA = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 방향 변수
    static final int W = 0, A = 1, S = 2, D = 3;
    static final char[] MOVE = new char[]{'W', 'A', 'S', 'D'};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int H = Integer.parseInt(st.nextToken());   // 행
        int W = Integer.parseInt(st.nextToken());   // 열

        // 격자 정보 입력
        char[][] map = new char[H][W];
        // 다오 초기화
        Node dao = null;
        for(int h = 0; h < H; h++){
            String input = in.readLine();
            for(int w = 0; w < W; w++){
                map[h][w] = input.charAt(w);
                if(map[h][w] == 'D') dao = new Node(h, w, "");
            }
        }

        // 이동 횟수
        int N = Integer.parseInt(in.readLine());
        // 이동 제한 정보
        int[][] moveInfo = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            moveInfo[i] = new int[] {
                    getMove(st.nextToken()),
                    getMove(st.nextToken())
            };
        }

        // 정답 : bfs를 통해 다오 -> 디즈니 이동 체크
        Answer answer = bfs(map, dao, moveInfo, H, W, N);

        // 가능한 경우 이동 방식 출력
        if(answer.flag) sb.append("YES\n").append(answer.move);
        // 불가능한 경우
        else sb.append("NO");

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static int getMove(String move) {
        if(move.equals("W")) return W;
        else if(move.equals("A")) return A;
        else if(move.equals("S")) return S;
        else return D;
    }

    private static Answer bfs(char[][] map, Node dao, int[][] moveInfo, int h, int w, int n) {
        Deque<Node> deque = new LinkedList<>();
        boolean[][][] visited = new boolean[n+1][h][w];

        deque.offerLast(dao);
        visited[dao.move.length()][dao.h][dao.w] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            // System.out.println(cur.h+", "+cur.w+", "+cur.move+"---");

            // 디즈니에 도달한 경우 이동 방식 반환
            if(map[cur.h][cur.w] == 'Z') return new Answer(cur.move);
            // 이동 횟수를 모두 사용한 겨웅 패스
            if(cur.move.length() == n) continue;

            // 다음 방향
            for(int nextMove : moveInfo[cur.move.length()]){
                // 다음 노드
                Node next = new Node(
                        cur.h + DELTA[nextMove][0],
                        cur.w + DELTA[nextMove][1],
                        cur.move + MOVE[nextMove]
                );
    
                // 아래의 경우 패스
                // - 좌표를 벗어날 경우
                // - 이미 방문한 경우
                // - 벽인 경우
                if(next.h < 0 || next.h >= h ||
                        next.w < 0 || next.w >= w ||
                        visited[next.move.length()][next.h][next.w] ||
                        map[next.h][next.w] == '@'
                ) continue;

                deque.offerLast(next);
                visited[next.move.length()][next.h][next.w] = true;
            }
        }

        return new Answer();
    }
}