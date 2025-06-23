package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1953
// - BFS : BFS를 통해 집합 분리!
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        List<Integer>[] adjList = new List[N+1];
        StringTokenizer st = null;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            int num = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>();
            while(num-- > 0) adjList[i].add(Integer.parseInt(st.nextToken()));
        }

        int[] team = new int[N+1];
        for(int i = 1; i <= N; i++){
            if(team[i] != 0) continue;

            Deque<Integer> deque = new LinkedList<>();
            team[i] = 1;
            deque.offerLast(i);

            while(!deque.isEmpty()){
                int cur = deque.pollFirst();

                for(int next : adjList[cur]){
                    if(team[next] != 0) continue;

                    team[next] = -team[cur];
                    deque.offerLast(next);
                }
            }
        }

        List<Integer> blue = new ArrayList<>();
        List<Integer> white = new ArrayList<>();
        for(int i = 1; i <= N; i++){
            if(team[i] == 1) blue.add(i);
            else white.add(i);
        }
        sb.append(getTeam(blue));
        sb.append(getTeam(white));

        // 정답 출력
        System.out.println(sb);
    }

    private static String getTeam(List<Integer> team) {
        StringBuilder sb = new StringBuilder();
        sb.append(team.size()).append("\n");
        for(int num : team) sb.append(num).append(" ");
        sb.append("\n");

        return sb.toString();
    }
}
