package _2025._10;

import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2107
// - 누적합 : 구간 범위에 존재할 수 없는 경우로 부터 가능한 경우의 수 계산
public class _04_Solution_1 {
    // 노드 클래스
    static class Node implements Comparable<Node>{
        int start;  // 시작
        int end;    // 끝
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
        // end 기준 내림차순 정렬, start 기준 오름차순 정렬
        @Override
        public int compareTo(Node o){
            int diff = Integer.compare(o.end, this.end);
            return diff == 0? Integer.compare(this.start, o.start) : diff;
        }
        @Override
        public String toString(){
            return String.format("[start = %d, end = %d]", this.start, this.end);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 구간의 수
        int N = Integer.parseInt(in.readLine());
        
        // 구간 초기화
        Node[] nodes = new Node[N];

        // 구간 정보 입력
        List<Integer> pointList = new ArrayList<>();
        StringTokenizer st = null;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            nodes[n] = new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            pointList.add(nodes[n].start);
            pointList.add(nodes[n].end);
        }

        Collections.sort(pointList);

        int pointSize =  pointList.size();
        Map<Integer, Integer> pointToIdx = new HashMap<>();
        for(int i = 0; i < pointSize; i++) pointToIdx.put(pointList.get(i), i);

        int[] startSum = new int[pointSize];
        int[] endSum = new int[pointSize];
        for(int n = 0; n < N; n++){
            startSum[pointToIdx.get(nodes[n].start)]++;
            endSum[pointToIdx.get(nodes[n].end)]++;
        }

        for(int i = 1; i < pointSize; i++){
            startSum[i] += startSum[i-1];
            endSum[i] += endSum[i-1];
        }

        int  answer = 0;
        for(Node node : nodes){
            int start = pointToIdx.get(node.start);
            int end = pointToIdx.get(node.end);

            int exclusionStart = startSum[start];
            int exclusionEnd = endSum[pointSize-1] - (end > 0 ? endSum[end-1] : 0);

            answer = Math.max(answer, N - exclusionStart - exclusionEnd + 1);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
