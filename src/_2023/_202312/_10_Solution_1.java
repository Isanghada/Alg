package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1068
// - BFS를 통해 해결 : 모든 경우 탐색!
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 노드의 수
        int N = Integer.parseInt(in.readLine());

        // 인접 리스트 초기화
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        for(int v = 0 ; v < N; v++) adjList.put(v, new HashSet<>());

        // 루트 노드 초기화
        int root = 0;

        // 인접 리스트 입력
        // - key : 부모
        // - value : 자식 리스트
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int child = 0; child < N; child++){
            int parent = Integer.parseInt(st.nextToken());
            // 부모가 -1인 경우 루트 노드이므로 패스!
            if(parent == -1) {
                root = child;
                continue;
            }
            // 부모 노드의 리스트에 child 추가!
            adjList.get(parent).add(child);
        }

        // 삭제할 노드
        int node = Integer.parseInt(in.readLine());

        // 리프 노드의 개수 계산
        int answer = getLeafNode(adjList, root, node);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 리프 노드의 수 계산 함수 : BFS를 통해 계산
    // - adjLsit : 인접 리스트
    // - node : 삭제할 노드
    private static int getLeafNode(Map<Integer, Set<Integer>> adjList, int root, int node) {
        // 리프 노드의 개수 초기화
        int leafCount = 0;

        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        // 루트 노드 추가!
        deque.offerLast(root);

        while(!deque.isEmpty()){
            // 현재 노드 반환
            int cur = deque.pollFirst();

            // 삭제한 노드인 경우 패스!
            if(cur == node) continue;

            // 노드 삭제!
            adjList.get(cur).remove(node);

            // 자식 리스트의 사이즈가 0인 경우 리프 노드 이므로 개수 증가
            if(adjList.get(cur).size() == 0) leafCount++;
            // 자식 노드가 있는 경우 자식 노드 탐색!
            else{
                for(int child : adjList.get(cur)) {
                    deque.offerLast(child);
                }
            }
        }

        // 리프 노드의 수 반환
        return leafCount;
    }
}
