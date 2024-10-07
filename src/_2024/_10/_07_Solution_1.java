package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://www.acmicpc.net/problem/14856
// -  그리디 : 피보나치 수의 합으로 모든 수를 만들 수 있다!
//             피보나치 수가 큰 값부터 차례로 선택하며 탐색!
public class _07_Solution_1 {
    // 정답
    public static Deque<Long> ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    
        // 목표값
        long N = Long.parseLong(in.readLine());

        // 피보나치 수열 : N을 초과하지 않는 경우까지 생성!
        long[] fibonacci = getFibonacci(N);
        
        // 정답 초기화
        ANSWER = new LinkedList<>();
        // 피보나치 마지막 값(가장 큰 값)부터 차례로 탐색!
        bruteForce(fibonacci, fibonacci.length-1, N);

        // 피보나치 수의 개수
        sb.append(ANSWER.size()).append("\n");
        // 사용된 피보나치 수
        for(long f : ANSWER) sb.append(f).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    // 현재 인덱스 선택 후 다음 인덱스 선택!
    private static void bruteForce(long[] fibonacci, int index, long target) {
        // index 선택
        ANSWER.offerFirst(fibonacci[index]);
        target -= fibonacci[index];

        // 목표값에 도달하지 않은 경우 탐색!
        if(target != 0){
            // 연속되지 않은 값을 선택하기 위해 (현재 인덱스-2) 위치부터 시작
            for(int i = index-2; i >= 0; i--){
                // target보다 큰 경우 패스
                if(fibonacci[i] > target) continue;
                // target 이하인 경우 값 선택 후 재귀!
                bruteForce(fibonacci, i, target);
                break;
            }
        }
    }

    private static long[] getFibonacci(long n) {
        List<Long> fibList = new ArrayList<>();
        fibList.add(1L);
        fibList.add(1L);

        int size = 1;
        while(true){
            long next = fibList.get(size-1)+fibList.get(size);
            if(next > n) break;
            fibList.add(next);
            size++;
        }

        return fibList.stream().mapToLong(Long::new).toArray();
    }
}
