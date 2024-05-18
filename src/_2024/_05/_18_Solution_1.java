package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22943
// - 브루트포스 : 에라토스테네스의 체로 소수를 판변하여 모두 확인
public class _18_Solution_1 {
    // 최대값
    public static int MAX = 99999;
    // 정답, K, M
    public static int ANSWER, K, M;
    // 비트값
    public static int[] BIT_VALUE;
    // 에라토스테네스의 체 : 소수 판별
    public static boolean[] IS_PRIME;
    // 소수 리스트
    public static List<Integer> PRIME_LIST;

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 비트 값 계산
        BIT_VALUE = new int[10];
        BIT_VALUE[0] = 1;
        for(int i = 1; i < 10; i++) BIT_VALUE[i] = BIT_VALUE[i-1] << 1;

        // 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        K = Integer.parseInt(st.nextToken());   // 숫자 사용 횟수
        M = Integer.parseInt(st.nextToken());   // 기준값 M

        // 에라토스테네스의 체 초기화
        IS_PRIME = new boolean[MAX+1];
        IS_PRIME[0] = IS_PRIME[1] = true;

        // 소수 리스트 초기화
        PRIME_LIST = new ArrayList<>();
        // 2부터 모든 소수 확인
        for(int num = 2; num <= MAX; num++){
            if(IS_PRIME[num]) continue;

            PRIME_LIST.add(num);

            int next = num + num;
            while(next <= MAX){
                IS_PRIME[next] = true;
                next += num;
            }
        }

        // 정답 초기화
        ANSWER = 0;
        // 첫 자리는 1 ~ 9만 가능하므로 반복을 통해 모든 값 계산
        for(int start = 1; start < 10; start++){
            getNumberCount(1, start, BIT_VALUE[start]);
        }

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }

    // 숫자 탐색 함수 : 재귀를 통해 K자리수의 값을 만들어 조건 만족하는지 확인
    private static void getNumberCount(int step, int value, int bitmask) {
        if(step == K){
            if(isPossible(value)) {
//                System.out.println(value+"-----------");
                ANSWER++;
            }
            return;
        }
        value *= 10;
        for(int num = 0; num < 10; num++){
            if((bitmask & BIT_VALUE[num]) >= 1) continue;
            getNumberCount(step+1, value+num, bitmask | BIT_VALUE[num]);
        }
    }
    // 조건 판별 함수 : 1번, 2번 조건 확인
    private static boolean isPossible(int value) {
        if(!isSumOfPrimes(value)) return false;
        if(!isMulOfPrimes(value)) return false;

        return true;
    }
    private static boolean isSumOfPrimes(int value) {
        int limit = value / 2;
        int index = 0;
        while(PRIME_LIST.get(index) <= limit){
            int diff = value - PRIME_LIST.get(index);
            if(!IS_PRIME[diff] && PRIME_LIST.get(index) != diff) return true;
            index++;
        }

        return false;
    }

    private static boolean isMulOfPrimes(int value) {
        while(value % M == 0) {
            value /= M;
        }

        int limit = (int) Math.sqrt(value);
        int index = 0;
        while(PRIME_LIST.get(index) <= limit){
            if(value % PRIME_LIST.get(index) == 0){
                int v = value / PRIME_LIST.get(index);
                if(!IS_PRIME[v]) return true;
            }
            index++;
        }

        return false;
    }
}
