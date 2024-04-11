package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25565
// - 비트마스킹 : 완전 탐색을 통해 민우의 최대 점수 계산
//   1. 민우가 행을 고르는 모든 경우 반복
//   2. 민우가 체크할 행을 고른 후 종진이가 각 열 체크 혹은 미체크 중 최대값 선택
//   3. (전체 점수 - 종진이의 점수)로 민우의 점수를 구하고 최대값이면 갱신!
public class _11_Solution_1 {
    public static int[] BITVALUE;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 격자의 크기
        int N = Integer.parseInt(in.readLine());
        // 격자 전체 점수
        long total = 0L;
        // 격자 정보 입력
        int[][] board = new int[N][N];
        StringTokenizer st = null;
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                total += board[r][c];
            }
        }
        // 비트 정보 : 매번 계산하기에 시간이 아까워서 미리 계산
        BITVALUE = new int[N];
        BITVALUE[0] = 1;
        for(int bit = 1; bit < N; bit++) BITVALUE[bit] = BITVALUE[bit-1] << 1;

        // 정답 초기화 : Long의 최소값으로 설정
        // - 각 점수의 절대값이 10^9 이하이므로 음수가 나올 수도 있음
        long answer = Long.MIN_VALUE;
        // 민우가 선택할 경우의 비트 정보!만큼 반복
        final int bitLimit = (int) Math.pow(2, N);
        for(int bit = 0; bit < bitLimit; bit++){
            answer = Math.max(answer, getMaxScore(N, bit, board, total));
        }

        sb.append(answer);
        System.out.println(sb.toString());
    }
    // 점수 계산 함수 : 종진이의 최대값을 구하고 전체 점수에서 종진이 점수를 빼서 민우 점수 계산
    public static long getMaxScore(int n, int bit, int[][] board, long total){
        // 종진이의 점수 초기화
        long jongjinScore = 0;
        // 각 열마다 최선의 선택
        for(int c = 0; c < n; c++){
            // 체크하는 경우의 점수
            long checkScore = 0;
            // 체크하지 않는 경우의 점수
            long nonCheckScore = 0;
            // 모든 행 탐색
            for(int r = 0; r < n; r++){
                // 민우가 선택한 행이면 체크하는 경우의 점수 증가
                if((bit & BITVALUE[r]) > 0) checkScore += board[r][c];
                // 민우가 선택하지 않은 행이면 체크하지 않는 경우의 점수 증가
                else nonCheckScore += board[r][c];
            }
            // 두 점수 중 최대값으로 선택하여 증가
            jongjinScore += Math.max(checkScore, nonCheckScore);
        }
        // 민우 점수 반환
        return total - jongjinScore;
    }
}
