package _2023._202309;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12942
// - 다익스트라!! : 이런식으로도 쓰이는구나.. 새로운 응용 방식 입력
// - 일반적으로 한 정점에서 모든 정점을 탐색하는 것이 아닌
// - 모든 출입구에서 동시에 진행! => 최소값인 경우만 사용하므로 중복을 걱정하지 않아도 된다.
public class _11_Solution_1 {
    // 간선을 담을 클래스
    public class Node{
        int v;  // 정점
        int w;  // 가중치

        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    // 출입구 Set
    public static Set<Integer> gateSet;
    // 정산 Set
    public static Set<Integer> summitSet;
    // 인접 리스트!
    public static Map<Integer, List<Node>> graph;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 출입구 배열을 Set으로 변환!
        gateSet = new HashSet<>();
        for(int gate : gates) gateSet.add(gate);
        // 정상 배열을 Set으로 변환
        summitSet = new HashSet<>();
        for(int summit : summits) summitSet.add(summit);

        // 그래프 초기화 ( key - 정점, value - 간선 리스트)
        // - 필요한 노드만큼 생성
        graph = new HashMap<>();
        for(int idx = 1; idx <= n; idx++) graph.put(idx, new ArrayList<>());

        // 모든 경로 추가
        for(int[] path : paths){
            int s = path[0];    // 정점1
            int e = path[1];    // 정점2
            int w = path[2];    // 걸리는 시간
            
            // 출입구는 나가는 간선만, 정상은 들어오는 간선만 허용
            // s가 출입구이거나 e가 정상인 경우
            // - 단방향 이동
            if(gateSet.contains(s) || summitSet.contains(e)){
            graph.get(s).add(new Node(e, w));
            // e가 출입구이거나 s가 정상인 경우
            // - 단방향 이동
            }else if(gateSet.contains(e) || summitSet.contains(s)){
                graph.get(e).add(new Node(s, w));
            // 쉼터는 양방향으로 진행!
            }else{
                graph.get(s).add(new Node(e, w));
                graph.get(e).add(new Node(s, w));
            }
        }

        // 다익스트라를 통해 최소값 계산
        return getMinIntensity(n);
    }

    // 다익스트라 최소값 탐색 함수
    // - n : 정점의 수
    private int[] getMinIntensity(int n) {
        // 정답 초기화
        int[] answer = new int[] {0, Integer.MAX_VALUE};

        // intensity 배열 초기화 : 최대값으로
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
    
        // deque 초기화
        // - 모든 출입구 추가!
        Deque<Node> deque= new LinkedList<>();
        for(int gate : gateSet){
            // 출입구에서의 intensity는 0!
            deque.offerLast(new Node(gate, 0));
            intensity[gate] = 0;
        }

        // deque가 빌 때가지 반복
        while(!deque.isEmpty()){
            // 현재 정점 반환
            Node cur = deque.pollFirst();

            // 노드의 intensity가 최소값 보다 크면 패스
            if(cur.w > intensity[cur.v]) continue;

            // 인접 노드 탐색
            for(Node next : graph.get(cur.v)){
                // 인접 노드까지의 intensity 계산
                // - 현재 노드의 intensity와 인접 노드까지의 가중치 중 최대값
                int weight = Math.max(intensity[cur.v], next.w);
                
                // 인접 노드의 intensity 최소값보다 새로운 값이 작으면 변경!
                if(intensity[next.v] > weight){
                    // 덱에 새로운 노드 추가
                    deque.offerLast(new Node(next.v, weight));
                    // intensity 업데이트
                    intensity[next.v] = weight;
                }
            }
        }
        
        // 모든 정점 중 최소값 탐색
        for(int summit : summitSet){
            if(answer[1] > intensity[summit]){
                answer[0] = summit;
                answer[1] = intensity[summit];
            }else if(answer[1] == intensity[summit]){
                answer[0] = Math.min(answer[0], summit);
            }
        }

        return answer;
    }
}
