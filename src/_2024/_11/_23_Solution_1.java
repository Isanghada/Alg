package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/4803
// - DFS : 깊이 우선 탐색을 통해 순환이 발생하는지 확인!
public class _23_Solution_1 {
    // 노드 클래스
    public static class Node{
        int parent; // 이전 노드(부모 노드)
        int cur;    // 현재 노드
        public Node(int parent, int cur){
            this.parent = parent;
            this.cur = cur;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        int caseCount = 1;
        while(true){
            st= new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 정점의 수
            int M = Integer.parseInt(st.nextToken());   // 간선의 수

            // 마지막인 경우 종료
            if(N == 0 && M == 0) break;

            // 인접 리스트 입력!
            List<Integer>[] adjList = new List[N+1];
            for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();
            while(M-- > 0){
                st = new StringTokenizer(in.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                adjList[node1].add(node2);
                adjList[node2].add(node1);
            }

            // 트리의 개수 확인 : DFS로 순환 체크
            int answer = countOfTree(adjList, N);

            // 정답 출력
            sb.append("Case ").append(caseCount++).append(": ");
            if(answer == 0) sb.append("No trees.");
            else if(answer == 1) sb.append("There is one tree.");
            else sb.append("A forest of ").append(answer).append(" trees.");
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 트리 개수 확인 함수
    private static int countOfTree(List<Integer>[] adjList, int n) {
        // 개수 초기화
        int count = 0;

        // 방문 배열
        boolean[] visited = new boolean[n+1];
        // 모든 노드를 루트로 시작!
        for(int start = 1; start <= n; start++){
            // 이미 방문한 경우 패스
            if(visited[start]) continue;

            Deque<Node> deque = new LinkedList<>();

            // 초기값 정보 추가!
            deque.offerLast(new Node(-1, start));
            visited[start] = true;
            
            // 트리 여부 플래그
            boolean flag = true;
            while(!deque.isEmpty() && flag){
                // 노드 반환
                Node node = deque.pollLast();

                // 연결된 노드 확인
                for(int next : adjList[node.cur]){
                    // 순환인 경우! : flag 갱신 후 종료
                    // - 부모 노드가 아닌데 방문한 경우
                    if(next != node.parent && visited[next]){
                        flag = false;
                        break;
                    // 부모 노드는 패스!
                    }else if(next == node.parent) continue;

                    // 노드 추가
                    deque.offerLast(new Node(node.cur, next));
                    visited[next] = true;
                }
            }
//            System.out.println(flag +", "+start);
            if(flag) count++;
        }

        return count;
    }
}
