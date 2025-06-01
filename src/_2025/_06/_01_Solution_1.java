package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20956
// - 덱 : 아이스크림의 양을 key로 각 아이스크림의 번호 덱을 value로 가지는 Map 활용
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Deque<Integer>> iceMap = new TreeMap<>(Collections.reverseOrder());
        st = new StringTokenizer(in.readLine());
        for(int n = 1; n <= N; n++){
            int ice = Integer.parseInt(st.nextToken());
            if(!iceMap.containsKey(ice)) iceMap.put(ice, new LinkedList<>());
            iceMap.get(ice).offerLast(n);
        }

        boolean flag = true;
        for(Map.Entry<Integer, Deque<Integer>> iceEntry : iceMap.entrySet()){
            int ice = iceEntry.getKey();
            Deque<Integer> iceDeque = iceEntry.getValue();
            while(M > 0 && !iceDeque.isEmpty()){
                if(flag) sb.append(iceDeque.pollFirst());
                else sb.append(iceDeque.pollLast());
                sb.append("\n");
                if(ice % 7 == 0) flag = !flag;
                M--;
            }
        }

        // 정답 입력
        System.out.println(sb.toString());
    }
}
