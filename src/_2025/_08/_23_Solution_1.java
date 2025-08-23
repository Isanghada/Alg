package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14588
// - DFS :  DFS를 통해 진입 시점을 기준으로 left, right 계산
public class _23_Solution_1 {
    static int N, S;        // N : 정점의 수, S : 루트 노드
    static int[][] TREE;    // 트리 : [v][0]-left, [v][1]-right

    static List<Integer>[] ADJ_LIST;    // 간선 정보
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정점의 수 입력
        N = Integer.parseInt(in.readLine());

        // 인접 리스트 초기화
        ADJ_LIST = new List[N+1];
        for(int i = 1; i <= N; i++) ADJ_LIST[i] = new ArrayList<>();

        // 간선 정보 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken());
            List<Integer> childList = new ArrayList<>();
            while(true){
                int next = Integer.parseInt(st.nextToken());
                if(next == -1) break;
                childList.add(next);
            }
            // 간선 정보 정렬
            Collections.sort(childList);
            // 추가!
            ADJ_LIST[parent].addAll(childList);
        }

        // 루트 노드
        S = Integer.parseInt(in.readLine());
        // 트리 초기화
        TREE = new int[N+1][2];

        // DFS를 통해 트리 정보 입력
        dfs(S, 1);

        // 정답 출력
        for(int tree = 1; tree <= N; tree++){
            sb.append(tree).append(" ")
                    .append(TREE[tree][0]).append(" ")
                    .append(TREE[tree][1]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int dfs(int cur, int order) {
        TREE[cur][0] = order;
        for (int next : ADJ_LIST[cur]) {
            if(TREE[next][0] != 0) continue;
            order = dfs(next, order + 1);
        }

        return TREE[cur][1] = order + 1;
    }
}
