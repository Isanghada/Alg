package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16197
// - BFS : 가능한 모든 경우를 탐색하며 최소값 계산!
public class _13_Solution_1 {
    // 이동 변수
    static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 동전 클래스
    static class Coin{
        int row;    // 행 좌표
        int col;    // 열 좌표
        public Coin(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 두 개의 동전 클래스
    static class TwoCoin{
        Coin a;
        Coin b;
        int count;  // 이동 횟수
        public TwoCoin(Coin a, Coin b, int count){
            this.a = a;
            this.b = b;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 보드 크기!
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 동전 위치
        List<Coin> coinList = new ArrayList<>();

        // 보드 정보 입력
        char[][] board = new char[N][M];
        for(int r = 0; r < N; r++){
            String input = in.readLine();
            for(int c = 0;c < M; c++) {
                board[r][c] = input.charAt(c);
                // 동전일 경우 리스트에 추가
                if(board[r][c] == 'o') coinList.add(new Coin(r, c));
            }
        }

        // 정답 출력
        // - BFS를 통해 모든 경우 탐색!
        sb.append(bfs(board, coinList, N, M));
        System.out.println(sb.toString());
    }

    private static int bfs(char[][] board, List<Coin> coinList, int n, int m) {
        // 덱 초기화
        Deque<TwoCoin> deque = new LinkedList<>();
        // 방문 배열 초기화
        // - visited[a.row][a.col][b.row][b.col] : 동전 a, 동전 b의 위치에 따른 방문 표시
        boolean[][][][] visited = new boolean[n][m][n][m];

        // 초기값 설정
        deque.offerLast(new TwoCoin(coinList.get(0), coinList.get(1), 0));
        visited[coinList.get(0).row][coinList.get(0).col][coinList.get(1).row][coinList.get(1).col]  = true;
        
        while(!deque.isEmpty()){
            // 현재 값 반환
            TwoCoin cur = deque.pollFirst();
            // 이동 횟수가 10회 이상인 경우 종료
            if(cur.count >= 10) break;
            for(int[] d : DELTA){
                // 다음 동전 위치 계산
                Coin a = new Coin(cur.a.row+d[0], cur.a.col+d[1]);
                Coin b = new Coin(cur.b.row+d[0], cur.b.col+d[1]);

                // 움직일 수 없는 경우 체크
                if(!isMove(board, a, n, m)){
                    a.row -= d[0];
                    a.col -= d[1];
                }
                if(!isMove(board, b, n, m)){
                    b.row -= d[0];
                    b.col -= d[1];
                }

                // 범위를 벗어나는지 체크
                int flag = 0;
                if(a.row < 0 || a.row >= n || a.col < 0 || a.col >= m) flag++;
                if(b.row < 0 || b.row >= n || b.col < 0 || b.col >= m) flag++;

                // 1개만 범위를 벗어나는 경우 횟수 반환
                if(flag == 1) return cur.count+1;
                // 모든 동전이 범위를 벗어나지 않는 경우 덱에 정보 추가!
                else if(flag == 0 && !visited[a.row][a.col][b.row][b.col]){
                    deque.offerLast(new TwoCoin(a, b, cur.count+1));
                    visited[a.row][a.col][b.row][b.col] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isMove(char[][] board, Coin a, int n, int m) {
        if(a.row >= 0 && a.row < n
                && a.col >= 0 && a.col < m
                && board[a.row][a.col] == '#') return false;

        return true;
    }
}
