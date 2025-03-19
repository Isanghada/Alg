package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23830
// - 이분 탐색 : K의 값을 기준으로 이분 탐색 진행!
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 전교생 수
        int N = Integer.parseInt(in.readLine());
        // 제기차기 점수
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        long S = Long.parseLong(st.nextToken());   // 목표 점수

        // 정답 출력
        // - 이분 탐색을 통해 최소 K 탐색
        sb.append(getMinK(A, p, q, r, S));
        System.out.println(sb);
    }

    private static int getMinK(int[] a, int p, int q, int r, long s) {
        // K 초기화
        int K = -1;
        // left, right 초기화
        int left = 1;           // K는 양의 정수이므로 최소값은 1로 설정
        int right = 100_001;    // 제기 차기 점수의 최대값보다 1크게 설정
        while(left <= right){
            // mid 계산 => mid값이 K로 가능한지 체크
            int mid = (left + right) / 2;

            // mid가 k일 경우 점수 계산
            long score = getScore(a, p, q, r, mid);
            // 미만인 경우 left 갱신
            if(score < s) left = mid + 1;
            // 이상인 경우 right, K 갱신
            else {
                right = mid - 1;
                K = mid;
            }
        }

        return K;
    }

    private static long getScore(int[] a, int p, int q, int r, int k) {
        long sum = 0L;
        for(int score : a){
            sum += score;
            if(score > (k+r)) sum -= p;
            else if(score < k) sum += q;
        }

        return sum;
    }
}
