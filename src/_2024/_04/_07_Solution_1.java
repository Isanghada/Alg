package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3079
// - 이분 탐색 : 시간을 변수로 모든 심사관이 해당 시간동안 체크 가능한 사람의 합 계산!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 심사관의 수
        int M = Integer.parseInt(st.nextToken());   // 친구의 수

        // 시간 최대값! : 가장 빠른 시간을 가진 심사관이 모든 친구를 검사하는 경우
        long LIMIT = 1_000_000_000;

        // 심사관 정보 입력
        int[] T = new int[N];
        for(int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(in.readLine());
            // 모든 심사관 중 최소값인 경우로 LIMIT 갱신
            LIMIT = Math.min(LIMIT, T[i]);
        }
        // 친구의 수 만큼 체크
        LIMIT *= M;

        // 정답 출력
        // - 이분 탐색을 통해 최소 시간 탐색
        sb.append(getMinTime(T, M, LIMIT));
        System.out.println(sb);
    }
    // 이분 탐색 함수 : 시간을 변수로 이분 탐색 진행!
    // - 시간에 대해 각 심사관이 검사할 수 있는 승객의 수를 합하여 m보다 큰 경우 가능!
    private static long getMinTime(int[] t, int m, long limit) {
        long answer = Long.MAX_VALUE;

        long left = 1;
        long right = limit;
        while(left <= right){
            long mid = (left + right) / 2;

            if(isPossible(t, m, mid)){
                answer = mid;
                right = mid - 1;
            }else left = mid + 1;
        }

        return answer;
    }

    private static boolean isPossible(int[] t, int m, long time){
        long total = 0;
        for(int i = 0; i < t.length; i++) total += time / t[i];

        return (total >= m) ? true : false;
    }
}
