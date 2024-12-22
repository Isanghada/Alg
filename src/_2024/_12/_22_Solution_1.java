package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1976
// - 집합, BFS : BFS를 통해 연결된 경로끼리 구분!
//               여행 경로의 모든 도시가 같은 경로에 있는지 체크
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 도시의 수
        int M = Integer.parseInt(in.readLine());    // 여행 경로의 길이

        // 인접 리스트!
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        // 연결 정보 입력
        StringTokenizer st = null;
        for(int a = 1; a <= N; a++){
            st = new StringTokenizer(in.readLine());
            for(int b = 1; b <= N; b++){
                int v = Integer.parseInt(st.nextToken());
                if(v == 1) adjList[a].add(b);
            }
        }

        // 여행 경로
        int[] routes = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 경로 집합!
        int[] citySet = getCitySet(adjList, N);

//        System.out.println(Arrays.toString(citySet));
//        System.out.println(Arrays.toString(routes));

        // 정답 출력
        // - 여행 경로의 모든 도시가 같은 경로인지 체크
        sb.append(checkRoute(routes, citySet) ? "YES" : "NO");
        System.out.println(sb);
    }

    private static boolean checkRoute(int[] routes, int[] citySet) {
        int setIdx = citySet[routes[0]];
        for(int route = 1; route < routes.length; route++){
            if(citySet[routes[route]] != setIdx) return false;
        }
        return true;
    }

    private static int[] getCitySet(List<Integer>[] adjSet, int n) {
        int[] citySet = new int[n+1];

        boolean[] visited = new boolean[n+1];

        int setIdx = 1;
        for(int city = 1; city <= n; city++){
            if(!visited[city]){
                Deque<Integer> deque = new LinkedList<>();

                deque.offerLast(city);
                visited[city] = true;

                while(!deque.isEmpty()){
                    int cur = deque.pollFirst();
                    citySet[cur] = setIdx;

                    for(int next : adjSet[cur]){
                        if(visited[next]) continue;
                        deque.offerLast(next);
                        visited[next] = true;
                    }
                }
                setIdx++;
            }
        }

        return citySet;
    }
}
