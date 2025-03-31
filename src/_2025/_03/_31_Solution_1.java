package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21738
// - bfs : BFS를 활용해 지지대인 얼음 중 가장 빨리 만나는 2개를 제외하고 나머지 얼음 모두 제거
public class _31_Solution_1 {
    static class Node{
        int num;    // 얼음 번호
        int count;  // 거쳐온 얼음 개수
        public Node(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 얼음 개수
        int S = Integer.parseInt(st.nextToken());   // 지지대 개수
        int P = Integer.parseInt(st.nextToken());   // 시작 얼음

        // 인접 리스트
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        // 얼음 연결 정보 입력
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList[A].add(B);
            adjList[B].add(A);
        }

        // 정답 반환
        // - bfs를 통해 제거되지 않는 최소 얼음의 수를 구하여 제거되는 얼음 개수 계산
        sb.append(N - countOfSaveIce(adjList, N, S, P));
        System.out.println(sb);
    }

    private static int countOfSaveIce(List<Integer>[] adjList, int n, int s, int p) {
        int save = 1;
        boolean flag = false;

        Deque<Node> deque = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        deque.offerLast(new Node(p, 0));
        visited[p] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if(cur.num <= s){
                save += cur.count;
                if(flag) break;
                flag = true;
            }else{
                for(int num : adjList[cur.num]){
                    Node next = new Node(num, cur.count+1);

                    if(visited[next.num]) continue;
                    deque.offerLast(next);
                    visited[next.num] = true;
                }
            }
        }

        return save;
    }
}