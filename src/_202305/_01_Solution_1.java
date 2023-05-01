package _202305;

import java.util.*;

public class _01_Solution_1 {
    // 계산의 용이를 위해 plan을 담을 Entity 클래스 선언
    // - 정렬은 start를 기준으로 정렬
    public class Entity implements Comparable<Entity>{
        public String name;    // 과제명
        public int start;      // 시작 시간
        public int playtime;   // 걸리는 시간
        public Entity(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        @Override
        public int compareTo(Entity o) {
            return this.start - o.start;
        }
    }
    public String[] solution(String[][] plans) {
        // 정답을 추가하기 편하게 List 사용
        List<String> answer = new ArrayList<>();
        // 과제의 개수
        int n = plans.length;

        // String[] -> Entity로 변환
        Entity[] entities = new Entity[n];
        for(int idx = 0; idx < n; idx++){
            entities[idx] = new Entity(plans[idx][0], timeToInt(plans[idx][1]), Integer.parseInt(plans[idx][2]));
        }

        // start를 기준으로 정렬
        Arrays.sort(entities);

        // 남은 과제를 담을 덱
        Deque<Entity> deque = new LinkedList<>();
        // 현재 시간
        int time = 0;
        // 전체 Entity 반복
        for(int idx = 0; idx < n; idx++){
            // 남아있는 과제가 있거나 time이 현재 과제 시간보다 작을 때 반복
            while(!deque.isEmpty() && time < entities[idx].start){
                // 가장 최근에 멈춘 과제 확인
                Entity cur = deque.peek();
                // 현재 시간에 걸리는 시간을 더해 계산
                time += cur.playtime;

                // 현재 과제 시작 시간보다 작거나 같으면 정답에 추가
                if(time <= entities[idx].start){
                    answer.add(cur.name);
                    deque.poll();
                // 아닐 경우
                // - 최근에 멈춘 과제의 걸리는 시간 조정
                // - time을 현재 과제 시작 시간으로 변경
                }else{
                    deque.peek().playtime = time - entities[idx].start;
                    time = entities[idx].start;
                }
            }
            // 마지막 과제일 경우 바로 정답에 추가
            if(idx + 1 == n){
                answer.add(entities[idx].name);
            }else{
                // 다음 시간 계산
                int nextTime = entities[idx].start + entities[idx].playtime;
                // 다음 과제 시작 시간보다 작거나 같을 경우 정답에 추가
                if(nextTime <= entities[idx+1].start){
                    answer.add(entities[idx].name);
                // 아닐 경우
                // - 현재 과제의 남은 시간 조정
                // - nextTime을 다음 과제 시작 시간으로 조정
                // - 덱에 현재 과제 추가
                }else{
                    entities[idx].playtime = nextTime - entities[idx+1].start;
                    nextTime = entities[idx+1].start;
                    deque.offerFirst(entities[idx]);
                }
                // 시간 변경
                time = nextTime;
            }
        }

        // 남은 과제가 있다면 차례대로 정답에 추가
        while(!deque.isEmpty()) answer.add(deque.poll().name);

        // 배열로 변환하여 반환
        return answer.toArray(new String[n]);
    }

    // String으로 표현한 시간을 int형으로 반환하는 함수
    private int timeToInt(String input) {
        String[] split = input.split(":");

        // 시간 * 60 + 분 : 분으로 변환하여 반환
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
