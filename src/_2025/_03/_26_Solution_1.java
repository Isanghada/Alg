package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/27440
// - DP : 숫자가 크므로 Map을 이용해 가능한 경우 탐색!
//        2로 나누기, 3으로 나누기를 기준으로 진행 => 1로 빼는 경우는 나머지로 계산
public class _26_Solution_1 {
    // dp 선언
    static Map<Long, Long> dp;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 시작 숫자
        long N = Long.parseLong(in.readLine());

        // dp 초기화 : 0, 1인 경우 초기화
        dp = new HashMap<>();
        dp.put(0L, 0L);
        dp.put(1L, 0L);
        make1(N);

        // 정답 반환
        sb.append(dp.get(N));
        System.out.println(sb);
    }

    private static long make1(long n) {
        // 이미 탐색한 숫자는 그대로 반환
        if(dp.containsKey(n)) return dp.get(n);
                            
        // 1을 빼는 경우는 나머지를 이용해 계산
        long value = Math.min(make1(n / 2)+ n % 2,  // 2로 나누는 경우
                              make1(n / 3)+ n % 3)  // 3으로 나누는 경우
                              + 1;                      // 나누기 연산 횟수 추가
        // n의 값 계산
        dp.put(n, value);

        return dp.get(n);
    }
}
