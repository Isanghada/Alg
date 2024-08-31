package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11657
// - 참고 : 벨만포드(https://stdio-han.tistory.com/87)
// - 벨만포드 : 벨만포드 알고리즘을 통해 최단 거리 계산!
public class _31_Solution_1 {
    public static int N, M;
    public static long[] DISTANCE;
    public static List<Edge> EDGE_LIST;
    public static class Edge{
        int start, end, cost;
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시의 수
        M = Integer.parseInt(st.nextToken());   // 버스 노선의 수

        EDGE_LIST = new ArrayList<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            EDGE_LIST.add(new Edge(A, B, C));
        }

        DISTANCE = new long[N+1];
        Arrays.fill(DISTANCE, 500000000);

        if(bellmanFord(1)){
            for(int city = 2; city <= N; city++){
                sb.append(DISTANCE[city] != 500000000 ? DISTANCE[city] : -1)
                        .append("\n");
            }
        }else sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean bellmanFord(int start) {
        DISTANCE[start] = 0;
        for(int i = 1; i <= N; i++){
            for(Edge edge : EDGE_LIST){
                if(DISTANCE[edge.start] == 500000000) continue;

                long next = DISTANCE[edge.start]+edge.cost;
                if(DISTANCE[edge.end] > next){
                    if(i == N) return false;
                    DISTANCE[edge.end] = next;
                }
            }
        }
        return true;
    }
}