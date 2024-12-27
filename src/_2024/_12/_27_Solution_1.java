package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7662
// - 우선순위 큐 : max큐, min큐를 구성하고 map을 통해 각 값의 개수 유지!
//                 명령에 따라 삽입, 삭제 과정을 진행!
// - 참고 : https://girawhale.tistory.com/14
//          - max큐, min큐 각각의 카운트를 체크해주었는데 전체적으로 확인만 해도 가능!
public class _27_Solution_1 {
    // 카운트 클래스
    public static class Count{
        int max;    // max큐에 존재하는 수의 개수
        int min;    // min큐에 존재하는 수의 개수
        public Count(){
            this(0, 0);
        }
        public Count(int max, int min){
            this.max = max;
            this.min = min;
        }
        public void plusCount(){
            this.max++;
            this.min++;
        }

        public boolean check(int type) {
            if(type == 0) return this.max <= this.min;
            else return this.max >= this.min;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            // 큐 초기화
            PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minPq = new PriorityQueue<>();

            // map 초기화
            Map<Integer, Count> countMap = new HashMap<>();

            // 명령 개수
            int K = Integer.parseInt(in.readLine());
            while(K-- > 0){
                st = new StringTokenizer(in.readLine());
                char cmd = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());
                // 삽입 명령
                if(cmd == 'I'){
                    if(!countMap.containsKey(value)) countMap.put(value, new Count());
                    countMap.get(value).plusCount();
                    maxPq.add(value);
                    minPq.add(value);
                // 삭제 명령 : max큐, min큐에 동일하게 존재하지 않는 수는 제거!
                }else if(cmd == 'D') {
                    // max큐 삭제
                    if (value == 1) remove(maxPq, countMap, 0);
                    // min큐 삭제
                    else if (value == -1) remove(minPq, countMap, 1);
                }
            }
            while(!maxPq.isEmpty() && !countMap.get(maxPq.peek()).check(0)){
                countMap.get(maxPq.poll()).max--;
            }
            while(!minPq.isEmpty() && !countMap.get(minPq.peek()).check(1)){
                countMap.get(minPq.poll()).min--;
            }

            if(maxPq.isEmpty() || minPq.isEmpty()) sb.append("EMPTY");
            else sb.append(maxPq.peek()).append(" ").append(minPq.peek());
            sb.append("\n");
        }


        // 정답 반환
        System.out.println(sb);
    }
    public static void remove(PriorityQueue<Integer> target, Map<Integer, Count> countMap, int type){
        // 동일하게 존재하지 않으면 삭제
        while(!target.isEmpty() && !countMap.get(target.peek()).check(type)){
            if(type == 0) countMap.get(target.poll()).max--;
            else countMap.get(target.poll()).min--;
        }

        // 비었을 경우 패스
        if(target.isEmpty()) return;
        int remove = target.poll();
        if(type == 0) countMap.get(remove).max--;
        else countMap.get(remove).min--;
    }
}
