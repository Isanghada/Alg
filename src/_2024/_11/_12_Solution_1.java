package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15971
// - BFS : 로봇의 시작 지점 2곳에서 모두 BFS로 다른 방으로 이동하는 거리 계산!
//         이후 로봇 1개의 위치를 지정하고, 다른 로봇을 인접 방으로 이동시켜 최소 거리 계산
public class _12_Solution_1 {
    // 노드 클래스
    public static class Node{
        int room;   // 도착 위치
        int cost;   // 거리
        public Node(int room, int cost){
            this.room = room;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());           // 방 개수
        int roomA = Integer.parseInt(st.nextToken()) - 1;   // 로봇A 위치
        int roomB = Integer.parseInt(st.nextToken()) - 1;   // 로봇B 위치

        // 인접 리스트 : 방 연결 정보 입력(양방향)
        List<Node>[] adjList = new List[N];
        for(int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int room1 = Integer.parseInt(st.nextToken()) - 1;
            int room2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            adjList[room1].add(new Node(room2, distance));
            adjList[room2].add(new Node(room1, distance));
        }

        // 로봇A를 기준으로 이동 거리 계산
        int[] distanceA = bfs(adjList, roomA);
        // 로봇B를 기준으로 이동 거리 계산
        int[] distanceB = bfs(adjList, roomB);

        // 정답 초기화
        int answer = 2_000_000_000;
        // 로봇A의 최종 위치 선택!
        for(int a = 0; a < N; a++){
            // 방 a와 인접한 모든 방까지 이동하는 로봇B 중 최소값 선택!
            int sum = 1_000_000_000;
            for(Node b : adjList[a]) sum = Math.min(sum, distanceB[b.room]);
            sum = Math.min(sum, distanceB[a]);

            // 로봇A의 이동 거리만큼 증가
            sum += distanceA[a];
            // 정답 최소값으로 갱신
            answer = Math.min(answer, sum);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static int[] bfs(List<Node>[] adjList, int start) {
        int[] result = new int[adjList.length];
        for(int i = 0; i < adjList.length; i++) result[i] = 1_000_000_000;
        result[start] = 0;

        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[adjList.length];

        deque.offerLast(start);
        visited[start] = true;

        while(!deque.isEmpty()){
            int room = deque.pollFirst();

            for(Node next : adjList[room]){
                if(visited[next.room]) continue;

                result[next.room] = result[room] + next.cost;
                deque.offerLast(next.room);
                visited[next.room] = true;
            }
        }

        return result;
    }
}
