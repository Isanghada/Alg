package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1630
// - 에라토스테네스의 체 : 소수만을 사용하여 값 게산!
//                         각 소수의 거듭제곱 중 (1, N) 범위 중 가장 큰 숫자만 활용!
public class _12_Solution_1 {
    public static final int MOD = 987654321;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 범위!
        int N = Integer.parseInt(in.readLine());

        // 정답 초기화
        long answer = 1L;

        // 에라토스테네스 배열!
        boolean[] isPrime = new boolean[N+1];
        // 모든 소수 확인!
        for(int num = 2; num <= N; num++){
            // 소수인 경우
            if(!isPrime[num]){
                // 로그를 통해 최대 거듭 제곱 확인!
                int p = (int) (Math.log10(N) / Math.log10(num));
                // 거듭제곱값 곱하기
                answer *= (int) Math.pow(num, p);
                // MOD로 나머지 계산
                answer %= MOD;

                // 소수의 배수 체크!
                int next = num+num;
                while(next <= N){
                    isPrime[next] = true;
                    next += num;
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
