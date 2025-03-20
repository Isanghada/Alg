package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17220
// - BFS : 방향 그래프로 그래프 구성 후 파악한 마약 공급책부터 BFS로 탐색!
//         이후, 마약을 공급받을 수 있는 마약 공급책 체크(단, 원산지는 제외!)
public class _21_Solution_1 {
    public static int answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 마약 공급책의 수
        int M = Integer.parseInt(st.nextToken());   // 관계 수

        // 인접 리스트 초기화
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i = 0; i < N; i++) adjList.put(i, new ArrayList<>());
        
        // 진입 차수 : 진입 차수가 0인 경우 원산지
        int[] inDegree = new int[N];
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int from = st.nextToken().charAt(0)-'A';
            int to = st.nextToken().charAt(0)-'A';

            inDegree[to]++;
            adjList.get(from).add(to);
        }

        // 파악한 파악 공급책 정보
        st = new StringTokenizer(in.readLine());
        // 파악한 마약 공급책의 수
        int K = Integer.parseInt(st.nextToken());
        while(K-- > 0){
            // 파악한 마약 공급책
            int target = st.nextToken().charAt(0)-'A';

            // BFS를 통해
            Deque<Integer> deque = new LinkedList<>();
            deque.offerLast(target);
            inDegree[target] = 0;

            while(!deque.isEmpty()){
                int cur = deque.pollFirst();

                if(inDegree[cur] != 0) continue;
                for(int next : adjList.get(cur)){
                    if(inDegree[next] == 0) continue;

                    deque.offerLast(next);
                    inDegree[next]--;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            if(inDegree[i] > 0) answer++;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}