package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22953
// - 이분 탐색 & 백트래킹 : 가능한 모든 격려를 한 경우에서 이분 탐색을 통해 최소 시간 계산
public class _10_Solution_1 {
    static final long MAX = 1_000_000_000_000L; // 최대 시간
    static long ANSWER; // 정답
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 요리사의 수
        int K = Integer.parseInt(st.nextToken());   // 음식 개수
        int C = Integer.parseInt(st.nextToken());   // 격려 횟수

        // 조리 시간 입력
        int[] times = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int n = 0; n < N; n++) times[n] = Integer.parseInt(st.nextToken());

        // 빠른 계산을 위해 오름차순 정렬
        Arrays.sort(times);

        // 정답 초기화
        ANSWER = MAX;
        // 백트래킹을 통해 가능한 모든 경우의 최소 시간 계산
        backtracking(C, 0, N, K, times, 0);


        // 정답 반환
        sb.append(ANSWER);
        System.out.println(sb.toString().trim());
    }

    // 백트래킹 함수 : 가능한 모든 격려 경우 탐색(중복 조합!)
    private static void backtracking(int c, int step, int n, int k, int[] times, int idx) {
        // 격려를 모두 한 경우
        if(c == step){
            // 최소 조리 시간 계산
            long minTime = binarySearch(times, n, k);
            // 정답 갱신
            ANSWER = Math.min(ANSWER, minTime);
        }else{
            for(int i = idx; i < n; i++){
                int temp = times[i];                // 현재 조리 시간
                times[i] = Math.max(1, times[i]-1); // 격려 후 조리 시간
                backtracking(c, step+1, n, k, times, i);
                times[i] = temp;                    // 조리 시간 복귀
            }
        }
    }
    // 최소 조리 시간 계산 함수 : 이분 탐색을 통해 현재 조리 시간을 토대로 최소 조리 시간 계산
    private static long binarySearch(int[] times, int n, int k) {
        long left = 1;                        // 최소
        long right = (long)times[n-1] * k;    // 최대
        while(left <= right){
            long mid = (left + right) / 2;

            // 조리가 가능한 경우 right 갱신
            if(check(times, mid, k)) right = mid - 1;
            // 조리가 불가능한 경우 left 갱신
            else left = mid + 1;
        }

        // 최소 조리 시간 반환(left)
        return left;
    }
    // 조리 가능 여부 함수 : time 안에 k개의 요리를 조리 가능한지 반환
    private static boolean check(int[] times, long time, int k) {
        long count = 0;
        for(int t : times) count += time / t;
        return count >= k;
    }
}
