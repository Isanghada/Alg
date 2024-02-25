package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/10472
// - 브루트포스 : 가능한 모든 경우 탐색
public class _25_Solution_1 {
    // 십자 변수!
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {0, 0}};
    // SIZE : 보드 크기
    // INF : 최대값
    public static final int SIZE = 3, INF = 10000000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 보드 초기화
            char[][] board = new char[SIZE][SIZE];
            // 보드 입력
            for(int r = 0; r < 3; r++){
                String input = in.readLine();
                for(int c = 0; c < 3; c++) board[r][c] = input.charAt(c);
            }
            // 최소 클릭 횟수!
            int answer = getMinCount(board);
            sb.append(answer).append('\n');
        }

        // 결과 반환
        System.out.println(sb);
    }
    // 최소 클릭 횟수 계산 함수 : 모든 경우 탐색!
    // - 9칸을 누르거나 누르지 않는 경우 총 512개
    private static int getMinCount(char[][] origin) {
        // 정답 초기화
        int answer = INF;
        // 비트 마스킹을 통해 각 인덱스 클릭 여부 체크!
        for(int bit = 0; bit < 512; bit++){
            char[][] board = new char[SIZE][SIZE];
            for(int r = 0; r < SIZE; r++){
                for(int c = 0; c < SIZE; c++) board[r][c] = origin[r][c];
            }
            // 비트 마스킹
            char[] bitmask = String.format("%09d", Integer.valueOf(Integer.toBinaryString(bit))).toCharArray();
            // 클릭 횟수
            int count = 0;
            // 클릭!
            for(int idx = 0; idx < 9; idx++){
                // 클릭하는 칸인 경우 클릭
                if(bitmask[idx] == '1') {
                    reverse(idx / SIZE, idx % SIZE, board);
                    count++;
                }
            }
            // 모든 칸이 흰색인 경우 최소값 갱신
            if(isWhite(board)) answer = Math.min(answer, count);
        }
        // 흰색으로 바꿀 수 없는 경우 -1 반환
        //          바꿀 수 있는 경우 정답 반환
        return answer == INF ? -1 : answer;
    }

    private static boolean isWhite(char[][] board) {
        for(int r = 0; r < SIZE; r++){
            for(int c = 0; c < SIZE; c++){
                if(board[r][c] == '*') return false;
            }
        }

        return true;
    }

    private static void reverse(int r, int c, char[][] board) {
        for(int[] d : DELTA){
            int nextR = r + d[0];
            int nextC = c + d[1];

            if(nextR < 0 || nextR >= SIZE || nextC < 0 || nextC >= SIZE) continue;

            if(board[nextR][nextC] == '*') board[nextR][nextC] = '.';
            else board[nextR][nextC] = '*';
        }
    }
}
