package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1354
// - DP :  Map을 이용해 Top-Down 방식의 DP로 구현
public class _02_Solution_1 {
    // 정보
    public static Long N, P, Q, X, Y;
    // Map
    public static Map<Long, Long> aMap;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Long.parseLong(st.nextToken()); // 수열 번호
        P = Long.parseLong(st.nextToken()); //
        Q = Long.parseLong(st.nextToken()); //
        X = Long.parseLong(st.nextToken()); //
        Y = Long.parseLong(st.nextToken()); //

        // Map 초기화
        aMap = new HashMap<>();

        // 정답 반환
        // - A[N] 계산
        sb.append(dp(N));
        System.out.println(sb);
    }

    private static long dp(Long n) {
        // 0 이하일 경우 1 반환
        if(n <= 0) return 1;
        // 값이 존재할 경우 값 반환
        if(aMap.containsKey(n)) return aMap.get(n);

        // 재귀를 통해 계산!
        long result = dp(n / P - X) + dp(n / Q - Y);
        // n에 값 추가
        aMap.put(n, result);

        // 값 반환

        return aMap.get(n);
    }
}
