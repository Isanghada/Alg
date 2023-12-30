package _2023._202304;

import java.util.Deque;
import java.util.LinkedList;

// 전체 경우 탐색
public class _22_Solution_1 {
    public int[] solution(int[] numbers) {
        // 배열의 길이
        int n = numbers.length;

        // 정답 초기화
        // - 배열 요소의 최소값이 1이므로 0으로 초기화
        int[] answer = new int[n];

        // 배열의 끝에서부터 탐색하기 위해 덱을 사용해 이전 값 저장
        Deque<Integer> deque = new LinkedList<>();
        // 모든 배열 요소 탐색
        for(int i = n-1; i >= 0; i--){
            // 덱이 빌 때까지 반복
            // - 덱이 비었다면 현재값(인덱스 i)보다 큰 값이 없는 것이므로 -1 입력
            while(!deque.isEmpty()){
                int temp = deque.peek(); // 가장 가까운 값부터 확인.
                // 현재값보다 크다면 해당 값 입력 후 반복 종료.
                if(temp > numbers[i]){
                    answer[i] = temp;
                    break;
                }
                // 작거나 같다면 poll을 통해 버림.
                // - 현재값보다 앞에 있는 값들이 temp를 정답으로 가질 수 없기 때문이다.
                deque.poll();
            }

            // 현재 정답이 0이라면 큰 값이 없다는 뜻이므로 -1 입력
            if(answer[i] == 0) answer[i] = -1;
            
            // 현재값 덱에 추가(인덱스가 작은 것부터 확인해야하므로 offerFirst로 헤드에 추가)
            deque.offerFirst(numbers[i]);
        }

        return answer;
    }
}
