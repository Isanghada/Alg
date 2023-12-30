package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23978
// - 이분 탐색 : 코인 가격을 기준으로 빠르게 현금화 합을 탐색
public class _21_Solution_1 {
    // 최대값 설정
    public static final long LIMIT = 1500000000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 코인 정보 설정
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 날짜의 수
        long K = Long.parseLong(st.nextToken());  // 현금화 목표 가격

        // 날짜 정보 입력
        st = new StringTokenizer(in.readLine());
        long[] A = new long[N];
        for(int i = 0; i < N; i++) A[i] = Long.parseLong(st.nextToken());

        // 이분 탐색 설정
        long left = 1;
        long right = LIMIT;

        // 정답 초기화 : 최대값 설정
        long answer = LIMIT;
        // 이분 탐색
        while(left <= right){
            // 코인 가격 상승 기준
            long mid = (left + right) / 2;

            // mid로 가격이 상승할 때 현금화 계산
            long sum = getSumCoin(mid, A);
//            System.out.println(mid+", "+sum);
            // K 이상일 경우
            if(sum >= K){
                // 코인 가격 최소값으로 변경!
                answer = Math.min(answer, mid);
                // right 감소
                right = mid - 1;
            // K 미만일 경우 : left 증가
            }else left = mid + 1;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 현금화 계산 함수
    // - x : 코인 상승 기준
    // - a : 코인 가격 상승 날짜
    private static long getSumCoin(long x, long[] a) {
        // 현금화의 합 초기화
        long sum = x * (x + 1) / 2;

        // 모든 날짜 기준 합 계산
        for(int idx = 0; idx < a.length-1; idx++){
            // 날짜 차이 계산
            long diff = Math.min(x, a[idx + 1] - a[idx]);;
            sum += x * diff - (diff - 1) * diff / 2;
        }

        return sum;
    }
}