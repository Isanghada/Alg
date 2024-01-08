package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22862
// - 투 포인터 : 홀수, 짝수의 수를 체크하며 최대값 탐색.
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 배열 길이
        int K = Integer.parseInt(st.nextToken());   // 제거할 수 있는 홀수의 수

        // 배열 정보
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        int answer = 0;
        // left, right 포인터 초기화 : 배열의 인덱스
        int left = 0, right = 0;
        // 홀수, 짝수 카운트는 첫 번째 인덱스로 초기화
        // 홀수 카운트
        int oddCount = arr[0] % 2 == 1 ? 1 : 0;
        // 짝수 카운트
        int evenCount = arr[0] % 2 == 0 ? 1 : 0;

        answer = evenCount;
        while(true){
            // 제거한 홀수의 수가 K 초과일 경우
            if(oddCount > K){
                // left 인덱스의 값을 확인하여 카운트 감소
                // - 짝수일 경우 : 짝수 감소
                if(arr[left++] % 2 == 0) evenCount--;
                // - 홀수일 경우 : 홀수 감소
                else oddCount--;
            }else{
                // right 증가
                right++;
                // 배열 길이를 넘을 경우 종료
                if(right >= N) break;
                // 짝수일 경우
                if(arr[right] % 2 == 0) {
                    // 짝수 카운트 증가
                    evenCount++;
                    // 정답을 최대값으로 변경
                    answer = Math.max(answer, evenCount);
                // 홀수일 경우 : 홀수 카운트 증가
                }else oddCount++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
