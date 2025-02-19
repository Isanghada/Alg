package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://www.acmicpc.net/problem/11559
// - 구현 : BFS를 통해 4개 이상 인접한 뿌요를 찾아서 제거!
//          제거한 뿌요가 있다면 빈 공간의 뿌요 갱신!
public class _19_Solution_1 {
    // 노드 클래스
    private static class Node{
        int row;    // 행 좌표
        int col;    // 열 좌표

        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 이동 변수
    static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 필드 크기
    static final int ROW = 12, COL = 6;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 필드 정보 입력
        char[][] board = new char[ROW][COL];
        for(int r = 0; r < ROW; r++) board[r] = in.readLine().toCharArray();

        // 정답 초기화
        int answer = 0;
        while(true){
            // 뿌요 제거 여부
            boolean flag = true;
            // 모든 좌표 탐색
            for(int r = 0; r < ROW; r++){
                for(int c = 0; c < COL; c++){
                    // 빈 공간일 경우 패스
                    if(board[r][c] == '.') continue;

                    // BFS를 통해 제거할 좌표 체크
                    List<Node> removeList = new ArrayList<>();
                    removeList.addAll(bfs(board, r, c));
                    // 제거할 수 있는 경우
                    if(removeList.size() != 0){
                        // flag 갱신
                        flag = false;
                        // 뿌요 제거
                        for(Node node : removeList) board[node.row][node.col] = '.';
                    }
                }
            }

            // 뿌요를 제거하지 못한 경우 종료
            if(flag) break;

            // 정답 증가
            answer++;
            // 필드 갱신
            fallPuyo(board);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    
    /**
        중력에 따라 빈 공간을 위에 있는 뿌요로 변경!
    **/
    private static void fallPuyo(char[][] board) {
        for(int c = 0; c < COL; c++){
            for(int r = ROW - 1; r > 0; r--){
                // 빈 공간 탐색
                if(board[r][c] == '.'){
                    // 위의 공간에 뿌요가 있다면 변경
                    for(int change = r-1; change >= 0; change--){
                        if(board[change][c] != '.'){
                            board[r][c] = board[change][c];
                            board[change][c] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     BFS를 통해 기준 좌표(r, c)와 색이 동일한 인접한 뿌요 체크
     4개 이상이라면 좌표 반환, 4개 미만이라면 초기화 후 반환
    **/
    private static List<Node> bfs(char[][] board, int r, int c) {
        // 제거할 좌표 리스트
        List<Node> removeList = new ArrayList<>();

        // 목표 색깔
        char target = board[r][c];
        
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[ROW][COL];

        deque.offerLast(new Node(r, c));
        visited[r][c] = true;
        while(!deque.isEmpty()){
            // 현재 좌표를 removeList에 추가
            Node cur = deque.pollFirst();
            removeList.add(cur);

            for(int[] d : DELTA){
                // 다음 좌표 계산
                Node next = new Node(cur.row+d[0], cur.col+d[1]);

                // 아래의 경우 패스
                // 1. 범위를 벗어난 경우
                // 2. 이미 방문한 경우
                // 3. 색깔이 다른 경우
                if(next.row < 0 || next.row >= ROW ||
                        next.col < 0 || next.col >= COL ||
                        visited[next.row][next.col] ||
                        board[next.row][next.col] != target
                ) continue;

                // 새로운 좌표 추가
                deque.offerLast(next);
                visited[next.row][next.col] = true;
            }
        }

        // 인접한 좌표가 4개 미만인 경우 초기화
        if(removeList.size() < 4) removeList.clear();
        return removeList;
    }
}
