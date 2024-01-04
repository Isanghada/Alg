package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17394
// - 에라토스테네스의 체 : 소수 체크
// - BFS : 초기값에서 차례로 계산
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 소수 배열 : false인 경우 소수
        boolean[] isPrime = new boolean[100001];
        isPrime[0] = true;  // 0은 소수가 아니므로 true로 변경
        isPrime[1] = true;  // 1은 소수가 아니므로 true로 변경
        // 에라토스테네스의 체
        for(int num = 2; num < isPrime.length; num++){
            if(isPrime[num]) continue;
            for(int next = num + num; next < isPrime.length; next += num)
                isPrime[next] = true;
        }

        // 테스트케이스 입력
        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            // 정보 입력
            st = new StringTokenizer(in.readLine());

            int n = Integer.parseInt(st.nextToken());   // 생명체의 수
            int a = Integer.parseInt(st.nextToken());   // 목표 최소값
            int b = Integer.parseInt(st.nextToken());   // 목표 최대값

            // 가능한 소수 탐색
            Set<Integer> prime = getPossiblePrime(isPrime, a, b);

            // 가능한 소수가 없는 경우 : -1 반환
            if(prime.isEmpty()) sb.append("-1\n");
            // 가능한 소수가 있는 경우 : BFS를 통해 계산 후 반환
            else sb.append(getMinCount(prime, n)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
    // 생명체 정보 클래스
    private static class Life{
        int count;  // 생명체의 수
        int snap;   // 스냅 횟수
        public Life(int count, int snap){
            this.count = count;
            this.snap = snap;
        }
    }
    private static int getMinCount(Set<Integer> prime, int n) {
        // 덱 초기화
        Deque<Life> deque = new LinkedList<>();
        // 방문 체크
        boolean[] visited = new boolean[1000001];

        // 초기값 설정 : n 기준 초기값 설정!
        deque.add(new Life(n, 0));
        visited[n] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 생명체 반환
            Life cur = deque.pollFirst();
            // 가능한 소수 중 하나일 경우 스냅 횟수 반환
            if(prime.contains(cur.count)) return cur.snap;
            // 다음 생명체 정보 탐색
            for(int i = 0; i < 4; i++){
                Life next = new Life(cur.count, cur.snap+1);
                // 순서에 따라 생명체 계산
                if(i == 0) next.count /= 2;         // 2로 나눈 몫으로 변경
                else if(i == 1) next.count /= 3;    // 3으로 나눈 몫으로 변경
                else if(i == 2) next.count += 1;    // 생명체 1 증가
                else next.count -= 1;               // 생명체 1 감소

                // 범위를 벗어나거나 이미 방문한 경우 패스
                if(next.count < 0 || next.count > 1000000 || visited[next.count]) continue;

                // 생명체 정보 추가!
                deque.add(next);
                visited[next.count] = true;
            }
        }

        return -1;
    }

    private static Set<Integer> getPossiblePrime(boolean[] isPrime, int a, int b) {
        // Set 초기화
        Set<Integer> prime = new HashSet<>();

        // a, b 범위의 소수 추가
        for(int num = a; num <= b; num++) if(!isPrime[num]) prime.add(num);

        return prime;
    }
}
