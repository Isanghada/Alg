package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28127
// - 이분 탐색 : x가 포함된 층을 이분 탐색을 통해 계산!
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 쿼리의 개수
        int Q = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 초기값
            int d = Integer.parseInt(st.nextToken());   // 공차
            int x = Integer.parseInt(st.nextToken());   // 타겟

            // x가 포함된 층 계산
            int floor = binarySearch(a, d, x);
            sb.append(floor).append(" ").append(x - getSum(floor-1, a, d)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int binarySearch(int a, int d, int x) {
        int left = 1;
        int right = 1_000_000;
        while(left <= right){
            int mid = (left + right) / 2;
            if(getSum(mid, a, d) >= x) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    private static long getSum(long n, long a, long d) {
        return (n * (2 * a + (n-1) * d) / 2 );
    }
}
