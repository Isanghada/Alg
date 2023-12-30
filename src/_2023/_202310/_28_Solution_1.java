package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2230
// - 투 포인터를 활용해 해결
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 숫자의 수
        int N = Integer.parseInt(st.nextToken());
        // 최소 차이
        int M = Integer.parseInt(st.nextToken());

        // 수열 입력
        int[] numbers = new int[N];
        for(int i = 0; i < N; i++) numbers[i] = Integer.parseInt(in.readLine());

        // 오름차순 정렬
        Arrays.sort(numbers);

        // 투 포인터 초기화
        int left = 0;
        int right = 1;
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 범위를 벗어나지 않은 경우 반복
        while(right < N){
            // right, left의 값 차이 계산
            int diff = numbers[right] - numbers[left];
            // M보다 큰 경우
            if(diff > M){
                // 최소값으로 변경
                answer = Math.min(answer, diff);
                // left 증가
                left++;
            // M과 같은 경우 : 값 변경 후 종료
            }else if(diff == M){
                answer = diff;
                break;
            // M보다 작은 경우 : right 증가
            }else right++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
