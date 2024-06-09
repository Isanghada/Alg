package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/18291
// - 분할 정복 : 가능한 경우의 수는 2의 거듭제곱으로 표현되므로, 분할 정복을 통해 빠르게 계산!
//               ex) 1, 1, 2, 4, 8, ... 으로 증가!
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int MOD = 1_000_000_007;
        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 징검다리 개수
            int N = Integer.parseInt(in.readLine());
            // 정답 초기화
            long answer = 0;

            // 2 이하인 경우 1 반환
            if(N <= 2) answer = 1;
            // 2 초과인 경우 분할 정복을 통해 계산
            else{
                N -= 2;
                answer = pow(N, MOD);
            }
            sb.append(answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static long pow(int n, int mod) {
        if(n == 0) return 1;
        if((n & 1 ) == 1) return 2*pow(n-1, mod) % mod;
        else{
            long result = pow(n / 2, mod);
            return result * result % mod;
        }
    }
}
