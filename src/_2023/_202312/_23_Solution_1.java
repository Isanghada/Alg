package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// hhttps://www.acmicpc.net/problem/1456
// - 에라토스테네스의 체
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 범위 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        long A = Long.parseLong(st.nextToken());    // 하한
        long B = Long.parseLong(st.nextToken());    // 상한

        // 소수 판별 : false인 경우 소수
        boolean[] isPrime = new boolean[10000001];
        isPrime[0] = true;
        isPrime[1] = true;

        // 에라토스테네스의 체 : 10^14의 제곱근인 10^7까지 중 소수 판별
        for(int num = 2; num < isPrime.length; num++){
            if(!isPrime[num]){
                int next = num + num;
                while(next < isPrime.length){
                    isPrime[next] = true;
                    next += num;
                }
            }
        }

        // 정답 초기화
        long answer = 0;
        // 2 ~ 10^7 중 소수로 모든 경우 탐색
        for(int num = 2; num < isPrime.length; num++){
            // 소수가 아닌 경우 패스
            if(isPrime[num]) continue;
            // 제곱수 계산!
            long square = (long)num * num;
            // B 이하
            while(square <= B) {
                // A 이상인 경우 정답 증가
                if(square >= A) answer++;
                // (Long의 최대값)^(1/3)인 2097151 이상인 경우 제곱까지만 계산
                if(num > 2097151) break;
                // 다음 제곱수
                square *= num;
            }
        }
        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
