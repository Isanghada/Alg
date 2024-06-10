package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/22252
// - 우선순위 큐 : 정보의 가치를 우선순위 큐로 내림차순 정렬하여 탐색
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 쿼리의 수
        int Q = Integer.parseInt(in.readLine());

        // 정답 : 정보의 가치(비용)
        long answer = 0;
        // 정보상 정보 Map
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        StringTokenizer st = null;
        while(Q-- > 0){
            // 쿼리 입력
            st = new StringTokenizer(in.readLine());

            int type = Integer.parseInt(st.nextToken());    // 명령 타입
            String name =  st.nextToken();                  // 정보상 이름
            int k = Integer.parseInt(st.nextToken());       // 정보의 개수
            // map에 name 정보상이 없는 경우 추가!
            if(!map.containsKey(name)) map.put(name, new PriorityQueue<>(Collections.reverseOrder()));

            // 1인 경우 정보 추가
            if(type == 1) map.get(name).addAll(getInfo(st));
            // 2인 경우 정보 구매
            else{
                if(map.containsKey(name)){
                    while(!map.get(name).isEmpty() && k-- > 0){
                        answer += map.get(name).poll();
                    }
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static List<Integer> getInfo(StringTokenizer st) {
        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));

        return list;
    }
}
