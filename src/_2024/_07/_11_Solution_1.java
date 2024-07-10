package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9024
// - 투 포인터 : 값에 따라 포인터의 위치를 변경하며 탐색!
//               1. 값이 같을 경우 양쪽 모두 이동
//               2. 작을 경우 left만 이동 (값 증가)
//               3. 클 경우 right만 이동 (값 감소)
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 수열 크기
            int K = Integer.parseInt(st.nextToken());   // 타겟

            // 수열 입력
            st = new StringTokenizer(in.readLine());
            int[] S = new int[N];
            for(int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
            // 수열 정렬
            Arrays.sort(S);

            // 투 포인터를 이용해 조합의 수 계산
            sb.append(getCount(N, K, S)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int getCount(int n, int k, int[] s) {
        // 왼쪽 포인터
        int left = 0;
        // 오른쪽 포인터
        int right = n-1;
        // 현재까지 최상의 값
        int best = Integer.MAX_VALUE;
        // 개수
        int count = 1;
        while(left < right){
            // 현재 값 계산
            int sum = s[left] + s[right];
            // k와 차이 계산
            int diff = Math.abs(sum - k);
            // best와 같을 경우 count 증가
            if(diff == best) count++;
            // 차이가 더 적을 경우 변경!
            else if(diff < best){
                count = 1;
                best = diff;
            }

            // 타겟과 같은 경우 left, right 모두 이동
            if(sum == k){
                left++;
                right--;
            // 타겟보다 작은 경우 left만 이동 (증가)
            }else if(sum < k) left++;
            // 타겟보다 클 경우 right만 이동 (감소)
            else right--;
        }

        return count;
    }
}
