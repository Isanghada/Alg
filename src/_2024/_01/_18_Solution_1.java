package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2118
// - 투 포인터 : 두 지점을 투 포인터로 탐색!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지점의 수
        int N = Integer.parseInt(in.readLine());

        int sum = 0;    // 전체 길이
        // 지점 거리 배열
        int[] lengths = new int[N];
        for(int idx = 0; idx < N; idx++) {
            lengths[idx] = Integer.parseInt(in.readLine());
            sum += lengths[idx];
        }

        int answer = 0; // 정답 초기화
        int left = 0;   // 지점 1
        int right = 1;  // 지점 2
        int leftSum = lengths[left];        // left 기준 시계 방향
        int rightSum = sum - lengths[left]; // left 기준 반시계 방향
        // left가 모든 지점이 될 때까지 반복
        while(left < N){
            // 최대값 변경
            answer = Math.max(answer, Math.min(leftSum, rightSum));
            // 합이 동일한 경우 : 최대값이 나온 것이므로 종료
            if(leftSum == rightSum) break;
            // leftSum이 클 경우 : left 증가
            else if(leftSum > rightSum){
                leftSum -= lengths[left];
                rightSum += lengths[left];
                left++;
            // rightSum이 클 경우 : right 증가
            }else{
                leftSum += lengths[right];
                rightSum -= lengths[right];
                right++;
                right %= N;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
