package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/3671
// - 백트래킹 : 가능한 모든 경우 탐색!
public class _17_Solution_1 {
    static boolean[] IS_PRIME;  // 에라토스테네스의 체
    static int[] BIT;           // 각 인덱스별 비트 값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 에라토스테네스의 체 입력
        IS_PRIME = eratosthenes(10_000_000);
        // 비트 값 입력
        BIT = bitmask(7);

        // 테스트케이스
        int c = Integer.parseInt(in.readLine());
        while(c-- > 0){
            // 숫자 입력
            char[] numbers = in.readLine().toCharArray();

            // 소수를 담을 Set 초기화
            Set<Integer> primeSet = new HashSet<>();
            // 백트래킹을 통해 가능한 모든 경우 탐색!
            for(int i = 0; i < numbers.length; i++){
                backtracking(1, ""+numbers[i], numbers, primeSet, BIT[i]);
            }


            // 소수의 개수 출력
            sb.append(primeSet.size()).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
    // 백트래킹 함수 : 재귀를 통해 만들 수 있는 모든 숫자 탐색
    private static void backtracking(int step, String cur, char[] numbers, Set<Integer> primeSet, int bitmask) {
        // 모두 탐색한 경우 종료
        if(step > numbers.length) return;
        // 현재 숫자
        int num = Integer.parseInt(cur);
//        System.out.println(num+"--------"+Integer.toBinaryString(bitmask));
        // 소수일 경우 set에 추가
        if (!IS_PRIME[num]) primeSet.add(num);

        // 다음 경우 탐색
        for(int i = 0; i < numbers.length; i++){
            if((bitmask & BIT[i]) > 0) continue;
            backtracking(step+1,
                    cur+numbers[i],
                    numbers,
                    primeSet,
                    bitmask | BIT[i]);
        }
    }
    // 에라토스테네스의 체 함수
    private static boolean[] eratosthenes(int limit) {
        boolean[] eratosthens = new boolean[limit];
        eratosthens[0] = true;
        eratosthens[1] = true;

        for(int num = 2; num < limit; num++){
            if(eratosthens[num]) continue;

            int next = num + num;
            while(next < limit) {
                eratosthens[next] = true;
                next += num;
            }
        }

        return eratosthens;
    }
    // 비트 함수
    private static int[] bitmask(int size) {
        int[] bit = new int[size];
        bit[0] = 1;

        for(int i = 1; i < size; i++) bit[i] = bit[i-1] << 1;

        return bit;
    }
}
