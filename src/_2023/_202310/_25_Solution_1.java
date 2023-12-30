package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/1241
// - 모든 경우를 구하여 해결
//   - 특정 숫자의 제곱근 이하의 값들로 가능한 경우를 모두 찾을 수 있음
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사람의 수
        int N = Integer.parseInt(in.readLine());
        // 숫자 입력 : 각 숫자에 대한 사람 수 체크
        int[] numbers = new int[N];
        // - key : 숫자, value : key를 적은 사람의 수
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
            if(!countMap.containsKey(numbers[i])) countMap.put(numbers[i], 0);
            countMap.put(numbers[i], countMap.get(numbers[i]) + 1);
        }

        // 각 숫자별 머리 톡톡 횟수
        int[] dp = new int[1000001];
        // - -1로 초기화
        Arrays.fill(dp, -1);
        // 모든 사람에 대해 반복
        for(int num : numbers){
            // 이미 횟수를 구한 경우 패스
            if(dp[num] < 0){
                // 제곱근 계산
                int limit = (int)Math.sqrt(num);
                // 횟수 초기화
                // - 제곱근으로 나누어 떨어지는 경우 : -제곱근을 적은 사람의 수
                //   - num = limit * limit => count += (countMap(limit) + countMap(limit))
                //   - 제곱근을 적은 사람의 수가 2번 증가하므로 미리 감소
                // - 제곱근으로 나누어 떨어지지 않는 경우 : 0
                int count = limit * limit == num ? -countMap.getOrDefault(limit, 0) : 0;
                // 1 ~ 제곱근 사이의 모든 가능한 경우 체크
                for(int diffNum = 1; diffNum <= limit; diffNum++){
                    // 나누어 떨어지는 경우에만 횟수 증가
                    if(num % diffNum == 0)
                        count += countMap.getOrDefault(diffNum, 0) + countMap.getOrDefault(num / diffNum, 0);
                }
                // 자기 자신을 제외해야하므로 계산된 count에 1을 빼고 입력
                dp[num] = count - 1;
            }
            // 횟수 반환
            sb.append(dp[num]).append("\n");
        }

        System.out.println(sb);
    }
}
