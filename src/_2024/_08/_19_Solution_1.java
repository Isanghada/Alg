package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/31423
// - DFS : 순서를 연결 리스트로 입력하여 깊이 우선 탐색으로 통합!
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringBuilder[] universities = new StringBuilder[N];
        List<Integer>[] adjList = new List[N];
        for(int i = 0; i < N; i++) {
            universities[i] = new StringBuilder(in.readLine());
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        int start = 0;
        for(int n = 1; n < N; n++){
            st = new StringTokenizer(in.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;

            start = i;
            adjList[i].add(j);
        }
        // 정답 출력
        sb.append(getIntegrationUniversity(start, adjList, universities));
        System.out.println(sb.toString());
    }

    private static String getIntegrationUniversity(int start, List<Integer>[] adjList, StringBuilder[] universities) {
        StringBuilder result = new StringBuilder();

        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(start);

        while(!deque.isEmpty()){
            int cur = deque.pollLast();
            result.append(universities[cur]);

            for(int i = adjList[cur].size() -1; i >= 0; i--){
                deque.offerLast(adjList[cur].get(i));
            }
        }
        return result.toString();
    }
}
