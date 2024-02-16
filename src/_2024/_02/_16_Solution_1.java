package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1351
// - DP : N의 크기가 크므로 Map으로 값 저장!
public class _16_Solution_1 {
    public static Long N, P, Q;
    public static Map<Long, Long> DP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Long.parseLong(st.nextToken()); // N번쨰 수열의 값
        P = Long.parseLong(st.nextToken()); // 계산을 위한 P
        Q = Long.parseLong(st.nextToken()); // 계산을 위한 Q

        // DP 초기화
        DP = new HashMap<>();
        // - 초기값 설정 : 0번째 수열 값은 1
        DP.put(Long.valueOf(0), Long.valueOf(1));

        // 정답 출력
        sb.append(memoization(N));
        System.out.println(sb);
    }
    
    // DP 함수
    private static long memoization(Long n) {
        // 계산된 값이 아닌 경우
        // - 재귀를 통해 값 계산!
        if(!DP.containsKey(n))
            DP.put(n, memoization(n / P) + memoization(n / Q));

        // 값 반환
        return DP.get(n);
    }
}
