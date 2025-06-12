package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20166
// - DP : 모든 좌표를 기준으로 가능한 경우의 수 계산
public class _12_Solution_1 {
    // 노드 클래스
    static class Node{
        String word;    // 완성된 문자열
        int row;        // 마지막 행 좌표
        int col;        // 마지막 열 좌표
        public Node(String word, int row, int col){
            this.word = word;
            this.row = row;
            this.col = col;
        }
    }
    // 이동 변수
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int K = Integer.parseInt(st.nextToken());   // 좋아하는 문자열의 수

        // 격자 정보 입력
        char[][] board = new char[N+1][M+1];
        for(int n = 1; n <= N; n++){
            String input = in.readLine();
            for(int m = 1; m <= M; m++) board[n][m] = input.charAt(m-1);
        }

        // 만들 수 있는 문자열 DP!
        Map<String, Long> dp = getDP(N, M, board, 5);

        // target의 경우의 수 반환!
        while(K-- > 0){
            String target = in.readLine();
            sb.append(dp.getOrDefault(target, 0L)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static Map<String, Long> getDP(int n, int m, char[][] board, int limit) {
        Map<String, Long> dp = new HashMap<>();

        // 모든 좌표를 시작점으로 추가!
        Deque<Node> deque = new LinkedList<>();
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= m; c++) deque.offerLast(new Node(String.valueOf(board[r][c]), r, c));
        }

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            // dp에 없는 문자열인 경우 추가
            if(!dp.containsKey(cur.word)) dp.put(cur.word, 0L);
            // 문자열 경우의 수 증가
            dp.put(cur.word, dp.get(cur.word)+1);

            // limit 미만인 경우 추가 탐색!
            if(cur.word.length() < limit){
                for(int[] d : DELTA){
                    // 다음 좌표 계산
                    int row = cur.row + d[0];
                    int col = cur.col + d[1];

                    if(row == 0) row = n;
                    else if(row > n) row = 1;

                    if(col == 0) col = m;
                    else if(col > m) col = 1;

                    deque.offerLast(new Node(cur.word+board[row][col], row, col));
                }
            }
        }

        return dp;
    }
}
