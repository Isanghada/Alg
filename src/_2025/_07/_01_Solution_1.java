package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8973
// - DP : 부분 수열의 중심을 선택하고, 모든 경우를 탐색하여 최대값 계산
//          1. 중심(pivot) 선택 : 수열의 길이가 홀수, 짝수인 경우 2가지
//          2. 중심을 기준으로 수열을 늘려가며 흐릿함의 최대값 탐색
public class _01_Solution_1 {
    static int B, E;
    static long MAX;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] arr1 = new int[N+1];
        int[] arr2 = new int[N+1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) arr1[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) arr2[i] = Integer.parseInt(st.nextToken());

        B = 0;
        E = 0;
        MAX = Long.MIN_VALUE;

        solution(arr1, arr2, N, true);
        solution(arr1, arr2, N, false);
        // 정답 입력
        sb.append(B).append(" ").append(E).append("\n").append(MAX);
        System.out.println(sb);
    }

    private static void solution(int[] arr1, int[] arr2, int n, boolean isOdd) {
        for(int pivot = 1; pivot <= n; pivot++){
            long sum = 0;
            int left = pivot;
            int right = pivot + (isOdd ? 0 : 1);
            while(left > 0 && right <= n){
                sum += arr1[left] * arr2[right] + arr1[right] * arr2[left];
                if(left == right) sum -= arr1[left] * arr2[right];
                if(sum > MAX){
                    MAX = sum;
                    B = left - 1;
                    E = n - right;
                }
                left--;
                right++;
            }
        }
    }
}
