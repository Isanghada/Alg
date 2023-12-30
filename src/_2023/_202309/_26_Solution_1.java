package _2023._202309;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/214289
// - DP를 통해 해결
// - 각 시간대별로 가능한 모든 온도에서 다음 시간대의 소비 전력을 계산 : 최소값으로 갱신
// - 마지막 시간대에서 최소값 반환
public class _26_Solution_1 {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        
        // 인덱스를 0부터 사용하기 위해 각 온도 10 증가
        // - 0 ~ 50 인덱스 활용
        temperature += 10;  // 실외 온도
        t1 += 10;   // 최소 희망 온도
        t2 += 10;   // 최대 희망 온도

        // 각 시간별 해당 온도가 되기 위한 최소 소비전력
        // - dp[time][temp] : time 시간대에 temp 온도가 되기 위한 최소 소비전력
        // - 초기값은 최대값으로 설정
        int[][] dp = new int[onboard.length][51];
        for(int time = 0; time < onboard.length; time++) Arrays.fill(dp[time], Integer.MAX_VALUE);

        // 시작 온도를 0으로 설정
        dp[0][temperature] = 0;

        // 각 시간대별로 모든 온도에 대해 다음 시간의 온도 계산
        for(int time = 0; time < onboard.length-1; time++){
            for(int t = 0; t <= 50; t++){
                // 최대값일 경우 패스 : 도달할 수 없는 경우
                if(dp[time][t] == Integer.MAX_VALUE) continue;
                // 현재 시간에 탑승객이 있지만 적정 온도가 아닌 경우 패스
                if(onboard[time] == 1 && (t < t1 || t > t2)) continue;

                // 에어컨을 사용하지 않는 경우
                int nextTemp = t;
                // - 현재 온도가 실외 온도보다 높으면 감소
                if(t > temperature) nextTemp--;
                // - 현재 온도가 실외 온도보다 낮으면 증가
                else if(t < temperature) nextTemp++;

//                System.out.println(nextTemp+", "+t1+", "+t2+", "+onboard[time+1]+", "+time);
                // 다음 시간에 승객이 없거나 적정 온도인 경우 최소 소비 전력 변경
                if(onboard[time+1] == 0 || (onboard[time+1] == 1 && nextTemp >= t1 && nextTemp <= t2))
                        dp[time+1][nextTemp] = Math.min(dp[time+1][nextTemp], dp[time][t]);

                // 에어컨을 사용하는 경우
                // - 희망 온도와 동일한 경우
                // - 다음 시간에 승객이 없거나 적정 온도인 경우 최소 소비 전력 변경
                if(onboard[time+1] == 0 || (onboard[time+1] == 1 && t >= t1 && t <= t2))
                    dp[time+1][t] = Math.min(dp[time+1][t], dp[time][t] + b);
                // - 희망 온도보다 낮은 경우 : 최대 온도가 50(40)이므로 50 미만인 경우만
                // - 다음 시간에 승객이 없거나 적정 온도인 경우 최소 소비 전력 변경
                if(t < 50) {
                    nextTemp = t + 1;
                    if (onboard[time + 1] == 0 || (onboard[time + 1] == 1 && nextTemp >= t1 && nextTemp <= t2))
                        dp[time + 1][nextTemp] = Math.min(dp[time+1][nextTemp], dp[time][t] + a);
                }
                // - 희망 온도보다 높은 경우 : 최소 온도가 0(-10)이므로 0 초과인 경우만
                // - 다음 시간에 승객이 없거나 적정 온도인 경우 최소 소비 전력 변경
                if(t > 0) {
                    nextTemp = t - 1;
                    if (onboard[time + 1] == 0 || (onboard[time + 1] == 1 && nextTemp >= t1 && nextTemp <= t2))
                        dp[time + 1][nextTemp] = Math.min(dp[time+1][nextTemp], dp[time][t] + a);
                }
            }
        }

//        for(int[] d : dp) {
//            for (int aa : d) System.out.print((aa == Integer.MAX_VALUE? -1 : aa) + " ");
//            System.out.println();
//        }
        // 마지막 시간대의 값 중 최소값 선택!
        for(int value : dp[onboard.length-1]) if(value != Integer.MAX_VALUE) answer = Math.min(answer, value);

        return answer;
    }
}
