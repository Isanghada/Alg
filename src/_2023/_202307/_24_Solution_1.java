package _2023._202307;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12936?language=java
// 레퍼런스 참고 안하고 하려니 어렵네..
public class _24_Solution_1 {
    public int[] solution(int n, long k) {
        // 오름차순 된 순서가 첫 순서이므로 -1
        k -= 1;
        // 정답 초기화
        int[] answer = new int[n];
        // 피보나치 배열 및 초기화
        long[] count = new long[n+1];
        count[0] = 1;
        for(int i = 1; i <= n; i++) count[i] = i * count[i-1];

        // 숫자를 담을 리스트
        // 1 ~ n까지 차례로 추가
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= n; i++) numbers.add(i);

        // 몫, 나머지를 구하여 계산
        // 몫 인덱스 위치의 값이 현재 값
        // 나머지 값이 k
        for(int i = 0; i < n; i++){
            // i 인덱스 제외 경우의 수
            long c = count[n-i-1];

            // 몫 계산
            int a = (int)(k / c);
            // 나머지 계산 : k 업데이트
            k = k % c;

            // i 인덱스 값 추가
            answer[i] = numbers.get(a);
            // i 인덱스 값 삭제
            numbers.remove(a);
        }
        return answer;
    }
}
