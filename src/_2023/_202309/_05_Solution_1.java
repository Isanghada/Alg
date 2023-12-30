package _2023._202309;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1562
// - bitmask와 dp를 활용하여 해결!
// - bitmask로 사용한 숫자 체크하고 dp[자릿수][bitmask][last]로 마지막 숫자를 확인하여
//   다음 자릿수로 들어갈 숫자 확인
// - 한 자리수부터 차례로 모든 숫자를 확인하며 진행하여 최종적으로 모든 숫자가 들어간 경우 계산
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        // System.setIn(new FileInputStream("src/_2023/_202309/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 길이 N 입력
        int N = Integer.parseInt(in.readLine());

        // 각 숫자별 bit 계산 : 미리 계산해두어 빠르게 확인
        // - 0 => 2^0 = 1
        // - 1 => 2^1 = 2
        // ...
        int[] bit = new int[10];
        bit[0] = 1;
        for(int idx = 1; idx < 10; idx++) bit[idx] = bit[idx-1] * 2;

        // dp 초기화
        // - 0이 첫 자리로 들어갈 수 없으므로 첫 자리가 1 ~ 9인 경우만 1로 변환
        int[][][] dp = new int[N+1][1024][10];
        for(int idx = 1; idx < 10; idx++) dp[1][bit[idx]][idx] = 1;

        // 길이 1부터 차례로 가능한 모든 경우를 확인하며 다음 길이의 경우의 수 계산
        // - numCount : 길이
        // - bitmask : 사용된 숫자의 비트마스크
        // - last : 마지막에 사용된 숫자
        for(int numCount = 1; numCount < N; numCount++){
            for(int bitmask = 1; bitmask < 1024; bitmask++){
                for(int last = 0; last < 10; last++){
                    // 0인 경우 만들 수 없는 것이므로 패스.
                    if(dp[numCount][bitmask][last] == 0) continue;
                    // 마지막이 0인 경우 0보다 1 작은 수는 -1이므로 사용할 수 없음 패스.
                    if(last != 0){
                        // 현재 bitmask에 last-1의 비트를 '비트 or 연산'으로 다음 bitmask 계산
                        // - 현재 상태의 경우의 수만큼 증가
                        dp[numCount+1][bitmask | bit[last-1]][last-1] += dp[numCount][bitmask][last];
                        // - 1000000000 나머지로 변환
                        dp[numCount+1][bitmask | bit[last-1]][last-1] %= 1000000000;
                    }
                    // 마지막이 9인 경우 9보다 1 큰 수는 10이므로 사용할 수 없음 패스.
                    if(last != 9) {
                        // 현재 bitmask에 last+1의 비트를 '비트 or 연산'으로 다음 bitmask 계산
                        // - 현재 상태의 경우의 수만큼 증가
                        dp[numCount + 1][bitmask | bit[last + 1]][last + 1] += dp[numCount][bitmask][last];
                        // - 1000000000 나머지로 변환
                        dp[numCount + 1][bitmask | bit[last + 1]][last + 1] %= 1000000000;
                    }
                }
            }
        }
    
        // 정답 초기화
        int answer = 0;
        // 마지막 숫자 0 ~ 9인 경우 모두 합산
        // - 길이는 N, bitmask는 1023(0 ~ 9가 모두 사용된 경우)으로 고정
        for(int last = 0; last < 10; last++){
            answer += dp[N][1023][last];
            // - 1000000000 나머지로 변환 
            answer %= 1000000000;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
