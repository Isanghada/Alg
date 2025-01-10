package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/13144
// - 투 포인터 : 투 포인터를 통해 동일한 값이 포함되지 않는 범위 탐색
//               이후, 해당 범위에서 가능한 경우 계산
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 크기
        int N = Integer.parseInt(in.readLine());
        // 수열 정보
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 정답 초기화
        long answer = 0;

        // 중복 체크 Set
        Set<Integer> set = new HashSet<>();

        // 시작 범위
        int start = 0;
        // 끝 범위
        int end = 0;
        while(end < N){
            // 중복된 숫자인 경우 : 중복된 값이 나올 때까지 모든 경우만큼 증가!
            if(set.contains(numbers[end])){
                while(start < end){
                    answer += end - start;
                    set.remove(numbers[start]);

                    if(numbers[start++] == numbers[end]) break;
                }
            }
            // 수열 값 추가
            set.add(numbers[end++]);
        }

        // 남은 모든 시작점에서 끝 범위까지의 경우 구하기
        while(start < N){
            answer += N - start;
            start++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
