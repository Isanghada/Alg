package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/33678
// - 이분 탐색 & 누적합 : 일급을 누적합으로 계산하여 기간별 임금 계산에 활용
//                          휴가 일수를 기준으로 이분 탐색을 통해 가능한 최대 휴가일 계산
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 기간
        int K = Integer.parseInt(st.nextToken());   // 콘서트 비용
        int X = Integer.parseInt(st.nextToken());   // 일급 보너스

        // 일급 누적합 계산
        long[] A = new long[N+1];
        st = new StringTokenizer(in.readLine());
        for(int n = 1; n <= N; n++) A[n] = A[n-1] + Long.parseLong(st.nextToken());

        // 이분 탐색을 통해 휴가 기간 계산
        int answer = binarySearch(N, K, X, A);

        // 불가능한 경우(0) -1 반환, 가능한 경우 휴가일 반환
        sb.append(answer == 0 ? -1 : answer);

        // 정답 반환
        System.out.println(sb);
    }

    // 휴가 일수 이분 탐색
    private static int binarySearch(int n, int k, int x, long[] a) {
        int left = 1;
        int right = n;
        while(left <= right){
            // 휴가일 계산
            int mid = (left + right) / 2;
            // 휴가일이 가능한지 체크
            // - 가능하다면 left 증가
            if(check(n, k, x, a, mid)) left = mid + 1;
            // - 불가능하다면 right 감소
            else right = mid - 1;
        }
        return right;
    }
    // 휴가일 체크 함수
    // - target 휴가일이 가능한지 체크
    private static boolean check(int n, int k, int x, long[] a, int target) {
        // 가능한 모든 경우 중 
        // - 가능한 경우가 있다면 true
        // - 불가능한 경우 false 반환
        for(int day = n - target; day >= 0; day--){
            long sum = a[day] * x + (a[n]-a[day+target]);
            if(sum >= k) return true;
        }
        return false;
    }
}
