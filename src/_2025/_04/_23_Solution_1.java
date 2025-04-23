package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/28283
// - BFS : 보안 시스템을 설치하는 컴퓨터를 기준으로 각 컴퓨터에 설치되는 시점을 BFS로 계산
//          각 컴퓨터에 보안 설치되기 전에 해킹으로 가져올 수 있는 돈 계산!
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 컴퓨터의 수
        int M = Integer.parseInt(st.nextToken());   // 통신망의 수
        int X = Integer.parseInt(st.nextToken());   // 동시 해킹의 수
        int Y = Integer.parseInt(st.nextToken());   // 보안 설치의 수

        // 해킹으로 얻을 수 있는 돈 정보 입력
        int[] moneyArr = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // 인접 리스트 초기화
        List<Integer>[] adjList = new List[N+1];
        for(int i = 1; i <= N ;i++) adjList[i] = new ArrayList<>();

        // 통신망 정보 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adjList[s].add(e);
            adjList[e].add(s);
        }

        // 초기 보안 설치 정보 입력
        int[] securityArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // bfs를 통해 각 컴퓨터가 보안 설치되는 시기 계산
        int[] times = bfs(adjList, securityArr, N);
        // tiems를 기준으로 최대로 얻을 수 있는 돈 계산
        sb.append(getMaxMoney(times, moneyArr, N, X));

        // 정답 출력
        System.out.println(sb);
    }

    private static long getMaxMoney(int[] times, int[] moneyArr, int n, int x) {
        long result = 0;

        PriorityQueue<Long> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i = 1; i <= n; i++){
            if(times[i] == -1 && moneyArr[i] != 0) return -1;
            pq.offer(((long)times[i] * moneyArr[i]));
        }

        while(x-- > 0) result += pq.poll();

        return result;
    }

    private static int[] bfs(List<Integer>[] adjList, int[] securityArr, int n) {
        int[] times = new int[n+1];
        Arrays.fill(times, -1);

        Deque<Integer> deque = new LinkedList<>();

        for(int security : securityArr){
            times[security] = 0;
            deque.offerLast(security);
        }

        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            for(int next : adjList[cur]){
                if(times[next] != -1) continue;

                times[next] = times[cur] + 1;
                deque.offerLast(next);
            }
        }

        return times;
    }
}
