package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14699
// - DP :  각 쉼터에서 더 높은 쉼터로만 연결!
//         각 쉼터에서 탐색할 수 있는 노드를 탐색하며 최대값으로 갱신!
public class _26_Solution_1 {
    // 쉼터 배열
    public static Node[] NODES;
    // 쉼터 클래스
    public static class Node{
        Set<Integer> nextNode;  // 연결된 쉼터
        int count;              // 최대 탐색 개수
        public Node(){
            this.nextNode = new HashSet<>();
            this.count = 1;
        }
        // 최대 탐색 개수 함수
        public int maxCount(){
            // 탐색하지 않은 경우 탐색!
            // - 다음 쉼터가 없는 경우 패스
            if(count == 1 && this.nextNode.size() > 0){
                // 최대값 : 연결된 쉼터 중 최대 탐색 개수!
                int max = 0;
                for(int high : this.nextNode) {
//                    System.out.println("in maxCount() : "+high);
//                    System.out.println("in maxCount() : "+NODES[high]);
                    max = Math.max(max, NODES[high].maxCount());
                }
                count += max;
            }

            return count;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ ")
                    .append(this.nextNode.size()).append(", ")
                    .append(this.nextNode.toString()).append(", ")
                    .append(count).append(" ]");
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 쉼터의 수
        int M = Integer.parseInt(st.nextToken());   // 길의 수

        // 쉼터 높이 정보!
        st = new StringTokenizer(in.readLine());
        int[] heights = new int[N+1];
        for(int i = 1; i <= N; i++) heights[i] = Integer.parseInt(st.nextToken());

        // 쉼터 초기화!
        NODES = new Node[N+1];
        for(int i = 0; i<= N; i++) NODES[i] = new Node();

        // 쉼터 연결!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 더 높은 방향으로만 연결!
            if(heights[node1] > heights[node2]) NODES[node2].nextNode.add(node1);
            else if(heights[node1] < heights[node2]) NODES[node1].nextNode.add(node2);
        }

//        for(int node = 1; node <= N; node++) System.out.println(node+", "+NODES[node]);
        // 모든 쉼터별 최대 탐색 개수 반환!
        for(int node = 1; node <= N; node++) sb.append(NODES[node].maxCount()).append("\n");


        // 정답 반환
        System.out.println(sb);
    }
}
