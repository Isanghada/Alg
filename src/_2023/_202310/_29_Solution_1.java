package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2229
// - dp를 활용해 해결
// - 인접한 사람과의 팀만 가능하므로, 차례로 계산
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사람의 수
        int N = Integer.parseInt(in.readLine());
        // 나이순서대로 주어지는 점수 입력
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // dp 초기화
        int[] dp = new int[N];
        // 모든 사람에 대해 반복
        // - i : 팀의 시작이 될 사람의 인덱스 (i이하의 사람과의 팀만 맺음!)
        for(int i = 0; i < N; i++){
            // 최소값, 최대값 초기화
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            // i 이하의 사람과의 팀을 맺었을 때의 값 계산
            for(int j = i; j >= 0; j--){
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);

                // i ~ j의 사람이 팀을 맺었을 때의 값과 기존 값 중 최대값 선택
                dp[i] = Math.max(dp[i], max - min + (j > 0 ? dp[j-1] : 0));
            }
        }
        // 정답 반환
        sb.append(dp[N-1]);
        System.out.println(sb);
    }
}
