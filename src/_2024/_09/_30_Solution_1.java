package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2015
// - 누적합 : 누적합을 저장하며 K를 만들 수 있는 개수 탐색
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수열 길이
        int K = Integer.parseInt(st.nextToken());   // 목표 값

        // 이전 값을 담을 Map
        Map<Integer, Long> map = new HashMap<>();
        // 정답 초기화
        long answer = 0;
        // 수열 초기화
        int[] A = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++){
            // 수열 값을 누적합으로 입력
            A[i] = Integer.parseInt(st.nextToken()) + A[i-1];

            // 현재 값이 K인 경우 정답 증가
            if(A[i] == K) answer++;

            // K가 되기 위해 필요한 값이 있는 경우
            // - (A[i] - K)값의 개수 만큼 개수 증가
            if(map.containsKey(A[i] - K)) answer += map.get(A[i] - K);
            // A[i]의 값 개수 증가!
            map.put(A[i], map.getOrDefault(A[i], 0L)+1);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}