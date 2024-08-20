package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17839
// - BFS :  명령어를 방향 그래프로 연결하여 가능한 경우 탐색!
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<String, List<String>> map = new HashMap<>();

        int N = Integer.parseInt(in.readLine());
        while(N-- > 0){
            String[] split = in.readLine().split(" is ");
            if(!map.containsKey(split[0])) map.put(split[0], new ArrayList<>());
            map.get(split[0]).add(split[1]);
        }

        Set<String> answer = getAnswers(map);
//        System.out.println(answer);
        
        // 정답 출력
        for(String q : answer) sb.append(q).append("\n");
        System.out.println(sb.toString());
    }

    private static Set<String> getAnswers(Map<String, List<String>> map) {
        Set<String> result = new TreeSet<>();

        Deque<String> deque = new LinkedList<>();
        deque.offerLast("Baba");

        while(!deque.isEmpty()){
            String cur = deque.pollFirst();

            if(map.containsKey(cur)){
                for(String next : map.get(cur)){
                    if(result.contains(next)) continue;
                    result.add(next);
                    deque.offerLast(next);
                }
            }
        }
        return result;
    }
}
