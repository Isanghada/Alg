package _202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/1337
// - 현재 무게(right), 이전 무게(left) 투 포인터로 해결
// - 제곱의 차를 계산하여 나온 값에 따라 진행
//   - 같을 경우 : 정답 추가(right), 현재 무게(right) 증가
//   - 작을 경우 : 현재 무게(right) 증가
//   - 큰 경우 : 이전 무게(left) 증가
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202309/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // (현재 무게)^2 - (이전 무게)^2
        int G = Integer.parseInt(in.readLine());
        long left = 1;  // 이전 무게
        long right = 2; // 현재 무게
        
        // 정답을 담을 리스트
        List<Long> answerList = new ArrayList<>();
        // 이전 무게가 현재 무게보다 작을 경우 반복
        while(left < right){
            // 제곱의 차이 계산
            long diff = square(right) - square(left);
            // 출력된 무게 G와 같을 경우 정답에 추가
            if(diff == G) answerList.add(right);
            // 크거나 같은 경우 right 증가
            if(diff <= G) right += 1;
            // 작을 경우 left 증가
            else left += 1;
        }

        // 정답 반환
        // - 가능한 현재 무게가 없는 경우 -1 추가
        if(answerList.size() == 0) sb.append(-1);
        // - 정답 리스트에서 모든 현재 무게 추가!
        else{
            for(long answer : answerList) sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    // 제곱 계산 함수 : 전달받은 value의 제곱을 반환
    private static long square(long value) {
        return value * value;
    }
}
