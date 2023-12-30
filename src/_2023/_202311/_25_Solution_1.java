package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/5557
// - DP를 활용해 해결
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 숫자의 개수
        int N = Integer.parseInt(in.readLine());
        // 정수 배열
        int[] num = Arrays.stream((in.readLine()).split(" ")).mapToInt(Integer::new).toArray();

        // DP 초기화
        // - dp[idx][result] = idx번째 숫자까지 활용해 result가 나오는 경우의 수
        long[][] dp = new long[N-1][21];
        // - 첫 숫자를 사용한 결과는 1
        dp[0][num[0]] = 1L;

        // 마지막 숫자를 제외하고 나머지 숫자로 모든 경우의 수 계산
        for(int idx = 1; idx < N-1; idx++){
            for(int sum = 0; sum < 21; sum++){
                // 이전 결과가 0이라면 패스
                if(dp[idx-1][sum] > 0){
                    // 0은 +, 1은 - 동작
                    for(int flag = 0; flag < 2; flag++){
                        // 새로운 값 게산
                        int next = sum;
                        if(flag == 0)  next += num[idx];
                        else next -= num[idx];

                        // 범위를 벗어날 경우 패스
                        if(next < 0 || next > 20) continue;
                        
                        // 이전 경우의 수 만큼 증가
                        dp[idx][next] += dp[idx-1][sum];
                    }
                }
            }
        }
//        for(int[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }
        // 결과 반환
        sb.append(dp[N-2][num[N-1]]);
        System.out.println(sb);
    }
}
