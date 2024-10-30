package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13302
// - DP : dp[day][coupon]을 통해 차례로 계산!
public class _30_Solution_1 {
    // 이용권 정보!
    public static int[][] DELTA = new int[][]{
            {-1, 0, 10000},     // 1일권
            {-3, -1, 25000},    // 3일권
            {-5, -2, 37000},    // 5일권
            {-1, 3, 0}          // 쿠폰 사용
    };
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 방학 일수
        int M = Integer.parseInt(st.nextToken());   // 갈 수 없는 날의 수

        Set<Integer> exception = new HashSet<>();
        if(M > 0){
            // 갈 수 없는 날 정보
            st = new StringTokenizer(in.readLine());
            while(st.hasMoreTokens()) exception.add(Integer.parseInt(st.nextToken()));
        }

        // 최대 비용
        final int limit = 10000 * N;
        // dp 초기화
        int[][] dp = new int[N+1][41];
        for(int n = 0; n <= N; n++) {
            Arrays.fill(dp[n], limit);
        }

        dp[0][0] = 0;
        // 1일 ~ N일까지 차례로 계산
        for(int day = 1; day <= N; day++){
            // 쿠폰 0개 ~ 40개 까지 차례로 계산
            for(int coupon = 0; coupon <= 40; coupon++){
                // 쉬는 날인 경우 이전 날짜의 값 적용
                if(exception.contains(day)) {
                    dp[day][coupon] = dp[day-1][coupon];
                }

                // 1일권, 3일권, 5일권, 쿠폰 중 최소 비용인 경우 선택!
                for(int[] d : DELTA){
                    int pastDay = day + d[0];
                    int pastCoupon = coupon + d[1];

                    if(pastDay < 0 || pastCoupon < 0 || pastCoupon > 40) continue;
                    dp[day][coupon] = Math.min(dp[day][coupon], dp[pastDay][pastCoupon] + d[2]);
                }
            }
        }

//        for(int[] d : dp) System.out.println(Arrays.toString(d));

        // 모든 N일차 값 중 최소값 선택!
        int answer = limit;
        for(int coupon = 0; coupon <= 40; coupon++) answer = Math.min(answer, dp[N][coupon]);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}