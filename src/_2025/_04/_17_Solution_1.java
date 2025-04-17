package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/25635
// - 그리디 : 가장 많이 이용할 수 있는 놀이기구의 이용 횟수를 조정하여 최대값 계산
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 놀이기구 개수
        int N = Integer.parseInt(in.readLine());
        // 놀이기구 이용 횟수 제한
        long[] A = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long maxCount = 0;  // 최대 이용 횟수
        long answer = 0;    // 놀이기구 이용 횟수
        for(long count : A){
            // 최대 이용 횟수 탐색
            if(count > maxCount) maxCount = count;
            // 모든 놀이기구의 이용 횟수만큼 증가!
            answer += count;
        }

        // 최대 이용 횟수만큼 감소
        answer -= maxCount;
        // 최대 이용 횟수 조정 : 다른 이용 횟수의 합보다 최대 이용 횟수가 큰 경우 조정
        if(answer < maxCount) maxCount = answer + 1;
        // 조정한 최대 이용 횟수만큼 증가
        answer += maxCount;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString().trim());
    }
}
