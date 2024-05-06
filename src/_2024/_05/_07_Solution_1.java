package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20002
// - 누적합 : 과수원의 이득을 누적합으로 계산하여 모든 경우 계산
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 과수원의 크기
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        // 과수원 정보 입력 : 누적합 계산!
        int[][] map = new int[N+1][N+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            // 입력 + 열 누적합 계산
            for(int c = 1; c <= N; c++){
                map[r][c] = Integer.parseInt(st.nextToken()) + map[r][c-1];
            }
            // 행 누적합 계산
            for(int c = 1; c <= N; c++) map[r][c] += map[r-1][c];
        }

        // 정답 초기화
        int answer = Integer.MIN_VALUE;

        // 행, 열 최대값! : k에 따라 가능한 값이 달라짐!
        int LIMIT = N;
        for(int k = 0; k < N; k++, LIMIT--) {
            for (int r = 1; r <= LIMIT; r++) {
                for (int c = 1; c <= LIMIT; c++) {
                    // 정답 갱신!
                    answer = Math.max(answer, getMoney(map, r, c, k));
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int getMoney(int[][] map, int r, int c, int k) {
        return map[r+k][c+k] - map[r-1][c+k] - map[r+k][c-1] + map[r-1][c-1];
    }
}
