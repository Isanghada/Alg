package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/14677
// - DP : 가능한 경우를 차례로 탐색!
public class _09_Solution_1 {
    public static int N;        // 약을 먹어야하는 날짜
    public static char[] BLD;   // 약의 순서
    public static int[][] DP;   // DP 배열
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());    // 약을 먹어야하는 날짜 입력
        BLD = in.readLine().toCharArray();      // 약의 순서 입력
        DP = new int[3*N][3*N];                 // DP 초기화
        // DP -1로 초기화
        for(int idx = 0; idx < DP.length; idx++) Arrays.fill(DP[idx], -1);

        // 정답 출력
        // - 최대로 먹을 수 있는 약의 개수
        sb.append(getMaxCount(0, 3*N-1, ' '));
        System.out.println(sb);
    }
    private static int getMaxCount(int left, int right, char prev) {
        // 약을 다 먹은 경우 0 반환
        if(left > right) return 0;
        // 계산된 값이 있는 경우 값 반환
        if(DP[left][right] != -1) return DP[left][right];

        // 먹을 수 있는 약의 수 초기화
        DP[left][right] = 0;
        // 왼쪽 약을 먹을 수 있는 경우
        if(BLD[left] == 'B' && prev == ' ' ||
                BLD[left] == 'B' && prev == 'D' ||
                BLD[left] == 'L' && prev == 'B' ||
                BLD[left] == 'D' && prev == 'L')
            DP[left][right] = Math.max(DP[left][right] , getMaxCount(left+1, right, BLD[left]) + 1);
        // 오른쪽 약을 먹을 수 있는 경우
        if(BLD[right] == 'B' && prev == ' ' ||
                BLD[right] == 'B' && prev == 'D' ||
                BLD[right] == 'L' && prev == 'B' ||
                BLD[right] == 'D' && prev == 'L')
            DP[left][right] = Math.max(DP[left][right] , getMaxCount(left, right-1, BLD[right]) + 1);
    
        // 최대값 반환
        return DP[left][right] ;
    }
}
