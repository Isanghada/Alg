package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/19538
// - BFS : BFS를 통해 주변인 중 절반 이상이 루머를 믿는 경우 탐색!
public class _13_Solution_1 {
    // 최대값
    public static final int MAX_VALUE = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사람의 수
        int N = Integer.parseInt(in.readLine());

        // 주변인 정보
        StringTokenizer st = null;
        Set<Integer>[] adjSet = new Set[N+1];
        for(int i = 1; i <= N; i++) {
            adjSet[i] = new HashSet<>();

            st = new StringTokenizer(in.readLine());
            while(st.hasMoreTokens()){
                int friend = Integer.parseInt(st.nextToken());
                if(friend != 0) adjSet[i].add(friend);
            }
        }

        // 최초 유포자 정보
        int M = Integer.parseInt(in.readLine());
        int[] starts = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 정답 : bfs를 통해 루머를 믿기 시작한 시간 계산
        int[] answers = bfs(adjSet, starts, N);
        // 시간 출력 : 루머를 믿지 않는 경우 -1 반환!
        for(int t = 1; t <= N; t++) sb.append(answers[t] == MAX_VALUE ? -1 : answers[t]).append(" ");

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static int[] bfs(Set<Integer>[] adjSet, int[] starts, int n) {
        Deque<Integer> deque = new LinkedList<>();  // 덱 초기화
        boolean[] visited = new boolean[n+1];       // 방문 배열
        int[] times = new int[n+1];                 // 믿기 시작한 시간
        int[] limits = new int[n+1];                // 루머를 믿기 위한 최소 주변인의 수
        int[] counts = new int[n+1];                // 루머를 믿는 주변인의 수

        // 초기값 설정
        for(int t = 1; t <= n; t++) {
            // 믿기 시작한 시간 => 최대값
            times[t] = MAX_VALUE;
            // 최소 주변인의 수 => 주변인의 절반 이상이여야 하므로 올림으로 처리!
            limits[t] = adjSet[t].size() / 2 + (adjSet[t].size() & 1);
        }

        // 최초 유포자 정보 설정
        for(int start : starts){
            // 덱에 추가
            deque.offerLast(start);
            // 방문 체크
            visited[start] = true;
            // 시간 갱신
            times[start] = 0;
        }

        // 시간 설정
        int time = 0;
        while(!deque.isEmpty()){
            // 시간 증가
            time++;

            // 탐색 대상의 수
            int size = deque.size();
            while(size-- > 0){
                // 탐색 대상
                int cur = deque.pollFirst();
                // System.out.println("time="+time+", number="+cur+", count="+counts[cur]+", limit="+limits[cur]);
                // 다음 대상 확인
                for(int next : adjSet[cur]){
                    // 방문한 경우 패스
                    if(visited[next]) continue;

                    // 루머를 믿는 주변인의 수 증가
                    counts[next]++;
                    // limit 이상인 경우 정보 설정!
                    if(counts[next] >= limits[next]){
                        deque.offerLast(next);  // 덱에 추가
                        visited[next] = true;   // 방문 체크
                        times[next] = time;     // 시간 갱신
                    }
                }
            }
        }

        return times;
    }
}
