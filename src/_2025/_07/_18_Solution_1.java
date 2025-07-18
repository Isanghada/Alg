package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23835
// - BFS : 1. BFS를 통해 최적 경로로 우유 배달. => 배달한 우유의 수 체크
//         2. 목표 방에 배달한 우유 수 출력
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 방의 수
        int N = Integer.parseInt(in.readLine());
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();
        // 비밀 통로 입력
        for(int n = 1; n < N; n++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 배달한 우유의 수
        int[] count = new int[N+1];
        
        // 쿼리 개수
        int Q = Integer.parseInt(in.readLine());
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            // 쿼리 번호
            int t = Integer.parseInt(st.nextToken());

            // 우유 배달
            if(t == 1){
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 방문 체크
                // - check[n] : n번 방에 도달하기 위한 이전 경로
                int[] check = new int[N+1];
                Arrays.fill(check, -1);
                Deque<Integer> deque = new LinkedList<>();
                deque.offerLast(start);
                check[start] = start;

                // 우유 배달 개수
                int milk = 0;
                while(check[end] == -1 && !deque.isEmpty()){
                    int size = deque.size();
                    while(size-- > 0) {
                        int cur = deque.pollFirst();

                        for(int next : adjList[cur]){
                            if(check[next] != -1) continue;
                            check[next] = cur;
                            deque.offerLast(next);
                        }
                    }
                    // 다음 방에 방문할 때 우유 배달 개수 증가!
                    milk++;
                    //System.out.println(111111+", "+check[end]);
                }

                // 목적지를 시작으로 역으로 우유 배달 개수 계산
                while(end != start){
                    count[end] += milk;
                    end = check[end];
                    milk--;
                }
            // 우유 개수 출력
            }else if(t == 2){
                int target = Integer.parseInt(st.nextToken());
                sb.append(count[target]).append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
