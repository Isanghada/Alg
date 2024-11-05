package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16562
// - BFS : 친구의 친구인지 확인하기 위해 BFS 활용
//         모든 친구 그룹과 친구가 되기위해 각 그룹의 최소 친구 비용 선택!
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 친구 관계 수
        int K = Integer.parseInt(st.nextToken());   // 돈!

        // 친구비 정보
        int[] A = Arrays.stream(("0 " + in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // 친구 관계 입력
        Set<Integer>[] adjSet = new Set[N+1];
        for(int n = 0; n <= N; n++) adjSet[n] = new HashSet<>();
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(in.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjSet[v].add(w);
            adjSet[w].add(v);
        }

        // 정답 초기화 : 최소 친구 비용!
        int answer = 0;
        // 방문 배열
        boolean[] visited = new boolean[N+1];
        // 모든 그룹 탐색
        for(int n = 1; n <= N && answer <= K; n++){
            // 이미 탐색한 경우 패스
            if(visited[n]) continue;

            // 친구 비용!
            int price = 10_000;
            // 덱 초기화
            Deque<Integer> deque = new LinkedList<>();

            // 새로운 친구 그룹 추가
            deque.offerLast(n);
            visited[n] = true;
            while(!deque.isEmpty()){
                int cur = deque.pollFirst();
                // 친구 비용 최소값으로 갱신
                price = Math.min(price, A[cur]);

                // 친구 관계 확인
                for(int next : adjSet[cur]){
                    if(visited[next]) continue;
                    deque.offerLast(next);
                    visited[next] = true;
                }
            }
            // 친구 비용만큼 추가
            answer += price;
        }

        // 정답 출력
        // - 모두 친구가 될 수 잇으면 최소 비용
        // - 모두 친구가 될 수 없다면 Oh no
        sb.append(answer <= K ? answer : "Oh no");
        System.out.println(sb);
    }
}
