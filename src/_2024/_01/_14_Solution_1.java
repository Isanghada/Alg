package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3933
// - 백트래킹 : 재귀를 통해 모든 경우의 수 탐색
//public class _14_Solution_1 {
//    public static int COUNT;    // 경우의 수
//    public static int[] SQUARE; // 각 양수의 제곱수!
//    public static void main(String[] args) throws Exception {
//        // 입출력 설정
//        System.setIn(new FileInputStream("src/_2024/_01/_14_input.txt"));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        SQUARE = new int[182];  // 최대값 [2^15 == 32768]을 위해 181까지 계산
//        for(int num = 1; num < 182; num++) SQUARE[num] = num * num;
//
//        // 테스크케이스 반복
//        while(true){
//            // 타겟값 입력
//            int N = Integer.parseInt(in.readLine());
//            // 0인 경우 종료
//            if(N == 0) break;
//
//            COUNT = 0;  // 경우의 수 초기화
//            // 라그랑주 네 제곱수 정리인 경우의 수 계산
//            getLagrangeCount(N, 0, 1, 0);
//            sb.append(COUNT).append("\n");
//        }
//
//        // 정답 반환
//        System.out.println(sb);
//    }
//    // 라그랑주 네 제곱수 정리인 경우 탐색 함수 : 재귀 활용
//    // - target : 기준 값
//    // - step : 재귀 횟수 (최대 4회 반복)
//    // - num : 이전에 선택한 값
//    // - total : 현재까지의 합
//    private static void getLagrangeCount(int target, int step, int num, int total) {
//        if(target == total) COUNT++;
//        // 4회 미만의 반복인 경우
//        if(step < 4){
//            // num 이상의 모든 수 탐색
//            for(int cur = num; cur < 182; cur++){
//                int next = total + SQUARE[cur];
//                if(target < next) break;
//                getLagrangeCount(target, step+1, cur, total + SQUARE[cur]);
//            }
//        }
//    }
//}

// https://www.acmicpc.net/problem/3933
// - DP : 각 경우를 차례로 계산
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 최대값
        final int LIMIT = pow(2, 15);

        int[] square = new int[182];  // 최대값 [2^15 == 32768]을 위해 181까지 계산
        int[][] dp = new int[LIMIT][5]; // DP 초기화
        // 작은 값부터 차례로 계산
        for(int num = 1; num < 182; num++) {
            // 현재 제곱값 계산
            square[num] = num * num;

            // DP 계산
            dp[square[num]][1] = 1;
            for(int target = square[num]; target < LIMIT; target++){
                dp[target][2] += dp[target-square[num]][1];
                dp[target][3] += dp[target-square[num]][2];
                dp[target][4] += dp[target-square[num]][3];
            }
        }

        // 테스크케이스 반복
        while(true){
            // 타겟값 입력
            int N = Integer.parseInt(in.readLine());
            // 0인 경우 종료
            if(N == 0) break;

            // 정답 초기화
            int answer = 0;
            // 모든 경우 합 계산
            for(int step = 1; step < 5; step++) answer += dp[N][step];
            
            // 정답 출력
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static int pow(int num, int step) {
        int total = 1;
        for(int i = 0; i < step; i++) total *= num;

        return total;
    }
}

