package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/14921
// - 투 포인터 : 양 끝점을 기준으로 용액을 혼합하여 값 확인!
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 용액의 수
        int N = Integer.parseInt(in.readLine());
        
        // 용액 정보
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        int answer = -200_000_000;
        
        // 초기값 설정
        int left = 0;
        int right = N-1;
        while(left < right){
            // 두 용액 혼합
            int sum = A[left] + A[right];
            // 정답 갱신
            if(Math.abs(answer) > Math.abs(sum)) answer = sum;

            // 합이 음수인 경우 : left 증가 (최소값에서 차례로 증가)
            if(sum < 0) left++;
            // 합이 양수인 경우 : right 감소 (최대값에서 차례로 감소)
            else right--;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
