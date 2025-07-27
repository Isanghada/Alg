package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30408
// -
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        Set<Long> weightSet = new HashSet<>();

        Deque<Long> deque = new LinkedList<>();
        weightSet.add(N);
        deque.offerLast(N);

        while(!deque.isEmpty()){
            long weight = deque.pollFirst();
            if(weight == 1) continue;

            long next = weight / 2;
            if((weight & 1) == 1) checkWeight(weightSet, deque, next+1);
            checkWeight(weightSet, deque, next);
        }

        // 정답 반환
        sb.append(weightSet.contains(M) ? "YES" : "NO");
        System.out.println(sb);
    }

    private static void checkWeight(Set<Long> weightSet, Deque<Long> deque, long weight) {
        if(weightSet.contains(weight)) return;
        weightSet.add(weight);
        deque.offerLast(weight);
    }
}
