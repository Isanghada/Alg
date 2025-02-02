package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/22945
// - 투 포인터 : 양 끝에서 시작하여 작은 값을 갱신하며 최대값 탐색
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 개발자의 수
        int N = Integer.parseInt(in.readLine());

        // 개발자 정보
        int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 정답 초기화
        int answer = 0;

        // left, right가 팀을 만들 때 능력치 계산!
        int left = 0;       // left 초기화
        int right = N-1;    // right 초기화
        while(left < right){
            // 최소값 계산
            int min = Math.min(X[left], X[right]);
            // 정답 갱신
            answer = Math.max(answer, (right-left-1) * min);

            // left가 right 이하일 경우 left 변경
            if(X[left] <= X[right]) left++;
            // left가 right 초과일 경우 right 변경
            else right--;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
