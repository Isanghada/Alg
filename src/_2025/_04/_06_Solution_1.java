package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16166
// - BFS : 호선별 방문 역, 역별 환승 호선을 정리하고
//         시작 가능한 호선부터 BFS를 통해 최소 환승 횟수 계산
public class _06_Solution_1 {
    static class Node{
        int num;
        int count;
        public Node(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        Set<Integer>[] stations = new Set[N+1];                 // 호선별 방문 역 번호(Set을 이용해 중복 제거)
        Map<Integer, List<Integer>> transfer = new HashMap<>(); // 역에 방문하는 호선 리스트(환승 가능 호선)

        StringTokenizer st = null;
        // 지하철 정보 입력
        for(int i = 1; i <= N; i++){
            stations[i] = new HashSet<>();

            st = new StringTokenizer(in.readLine());
            int K = Integer.parseInt(st.nextToken());
            while(K-- > 0) {
                int station = Integer.parseInt(st.nextToken());
                // 현재 호선에 포함되지 않은 역일 경우 추가!
                if(!stations[i].contains(station)){
                    // 역 추가
                    stations[i].add(station);

                    // 역에 방문하는 호선 추가
                    if(!transfer.containsKey(station)) transfer.put(station, new ArrayList<>());
                    transfer.get(station).add(i);
                }
            }
        }
        // 목표 역 번호
        int target = Integer.parseInt(in.readLine());

        // 정답 출력
        // - bfs를 통해 최소 환승 횟수 계산
        sb.append(bfs(stations, transfer, N, 0, target));
        System.out.println(sb);
    }

    private static int bfs(Set<Integer>[] stations, Map<Integer, List<Integer>> transfer, int n, int start, int target) {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 호선 방문 체크 배열
        boolean[] visited = new boolean[n+1];

        // 초기화 : start역을 포함하는 호선 추가
        for(int num : transfer.get(start)){
            deque.offerLast(new Node(num, 0));
            visited[num] = true;
        }

        while(!deque.isEmpty()){
            // 현재 정보 반환
            Node cur = deque.pollFirst();

            // 목표 역을 포함하는 경우 환승 횟수 반환
            if(stations[cur.num].contains(target)) return cur.count;

            // 현재 호선에서 방문하는 모든 역 탐색
            for(int station : stations[cur.num]){
                // 각 역에서 환승할 수 있는 호선 체크
                for(int num : transfer.get(station)){
                    // 방문한 호선인 경우 패스
                    if(visited[num]) continue;

                    // 새로운 호선인 경우 추가
                    deque.offerLast(new Node(num, cur.count+1));
                    visited[num] = true;
                }
            }
        }

        // 목표 역에 방문하지 못하는 경우 -1 반환
        return -1;
    }
}
