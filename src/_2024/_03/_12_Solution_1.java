package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/25556
// - 스택 : 4개의 스택에 모든 숫자가 추가될 수 있는지 확인
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 순열의 길이
        int N = Integer.parseInt(in.readLine());
        // 순열
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 덱 초기화 : 스택으로 활용
        Deque<Integer>[] deque = new LinkedList[4];
        for(int i = 0; i < 4; i++) deque[i] = new LinkedList<>();

        // 덱에 추가된 숫자의 개수
        int total = 0;
        // 숫자를 차례로 스택에 추가 : 덱에 추가될 때 (작은 값-> 큰 값) 순서인지 확인
        for(int num : arr){
            // 4개의 덱 중 가능한 경우에 추가
            // - 덱이 빈 경우
            // - 덱에 마지막으로 추가된 값이 num보다 작을 경우
            for(int stack = 0; stack < 4; stack++){
                if(deque[stack].isEmpty() || deque[stack].peekLast() < num) {
                    deque[stack].offerLast(num);
                    total++;
                    break;
                }
            }
        }

        // 결과 반환
        // - 덱에 추가된 숫자가 N개인 경우 YES, 아닌 경우 NO 반환
        sb.append(total == N ? "YES" : "NO");
        System.out.println(sb);
    }
}
