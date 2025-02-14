package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14622
// - 에라토스테네스의 체 : 소수 판별을 위해 5_000_000까지 에라토스테네스의 체를 통해 체크
//                         각 라운드마다 결과를 통해 점수 계산!
public class _14_Solution_1 {
    // 최대값
    static final int MAX = 5_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 에라토스테네스의 체
        boolean[] prime = new boolean[MAX+1];
        prime[0] = true;
        prime[1] = true;

        final int LIMIT = ((int)Math.sqrt(MAX))+1;
        for(int num = 2; num <= LIMIT; num++){
            if(prime[num]) continue;
            for(int next = num+num; next <= MAX; next += num) prime[next] = true;
        }
        /////////////////////////

        // 라운드 수
        int N = Integer.parseInt(in.readLine());
        long dwScore = 0;   // 대웅 점수
        long gsScore = 0;   // 규성 점수
        PriorityQueue<Integer> dwPq = new PriorityQueue<>(Collections.reverseOrder());  // 대웅
        PriorityQueue<Integer> gsPq = new PriorityQueue<>(Collections.reverseOrder());  // 규성

        // 전체 사용한 소수
        Set<Integer> primeSet = new HashSet<>();

        StringTokenizer st = null;
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int dw = Integer.parseInt(st.nextToken());  // 대웅 숫자
            int gs = Integer.parseInt(st.nextToken());  // 규성 숫자

            // 대웅의 결과부터 계산
            if(prime[dw]) gsScore += getTopTree(gsPq);
            else{
                if(primeSet.contains(dw)) dwScore -= 1000;
                else{
                    dwPq.offer(dw);
                    primeSet.add(dw);
                }
            }

            // 규성의 결과 계산
            if(prime[gs]) dwScore += getTopTree(dwPq);
            else{
                if(primeSet.contains(gs)) gsScore -= 1000;
                else{
                    gsPq.offer(gs);
                    primeSet.add(gs);
                }
            }
        }

        // 최종 점수에 따라 결과 출력
        if(dwScore == gsScore) sb.append("우열을 가릴 수 없음");
        else if(dwScore > gsScore) sb.append("소수의 신 갓대웅");
        else sb.append("소수 마스터 갓규성");

        // 정답 반환
        System.out.println(sb);
    }

    private static long getTopTree(PriorityQueue<Integer> pq) {
        long score = 0;

        if(pq.size() < 3) score = 1000;
        else{
            List<Integer> numbers = new ArrayList<>();
            for(int i = 0; i < 2; i++) numbers.add(pq.poll());

            score = pq.peek();
            pq.addAll(numbers);
        }

        return score;
    }
}

