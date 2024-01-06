package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/9084
// - DP : 각 동전을 차례로 사용하며 경우의 수 계산
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 수
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 동전의 수
            int N = Integer.parseInt(in.readLine());
            // 동전 배열
            int[] numArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
            // 목표 금액
            int goal = Integer.parseInt(in.readLine());
            
            // DP 초기화
            int[] dp = new int[goal+1];
            // 0원을 만드는 경우는 1가지 밖에 없음.
            dp[0] = 1;

            // 동전 기준으로 모든 경우의 수 계산
            for(int m : numArr){
                // 0원부터 차례로 모든 경우에 만들 수 있는 경우 계산
                for(int money = 0; money < goal; money++){
                    // 다음 금액 계산
                    int next = money+m;
                    // 목표를 넘어설 경우 종료
                    if(next > goal) break;
                    // 이전 경우의 수 만큼 증가
                    dp[next] += dp[money];
                }
            }
            // 목표 금액을 만들 수 있는 경우의 수 반환
            sb.append(dp[goal]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
