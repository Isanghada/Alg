package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25577
// -
public class _01_Solution_1 {
    static class Node implements Comparable<Node>{
        int num;
        int index;
        public Node(int num, int index){
            this.num = num;
            this.index = index;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.num, o.num);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Node> nodeList = new ArrayList<>();
        for(int n = 0; n < N; n++) nodeList.add(new Node(Integer.parseInt(st.nextToken()), n));

        Collections.sort(nodeList);
        int[] adj = new int[N];
        for(int after = 0; after < N; after++) {
            int before = nodeList.get(after).index;
            adj[before] = after;
        }

        int answer = 0;
        boolean[] visited = new boolean[N];
        for(int n = 0; n < N; n++){
            if(visited[n]) continue;
            answer += dfs(adj, visited, n, 0) - 1;
        }


        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int dfs(int[] adj, boolean[] visited, int n, int count) {
        visited[n] = true;
        count++;

        if(visited[adj[n]]) return count;
        return dfs(adj, visited, adj[n], count);
    }
}
