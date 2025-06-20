package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32963
// - 이분 탐색 :
public class _20_Solution_1 {
    static class Node implements Comparable<Node>{
        int t;
        int s;
        public Node(int t, int s){
            this.t = t;
            this.s = s;
        }
        @Override
        public int compareTo(Node o){
            int diff = Integer.compare(this.t, o.t);
            return diff == 0 ? Integer.compare(this.s, o.s) : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N];
        StringTokenizer tInput = new StringTokenizer(in.readLine());
        StringTokenizer sInput = new StringTokenizer(in.readLine());

        for(int i = 0; i < N; i++){
            int t = Integer.parseInt(tInput.nextToken());
            int s = Integer.parseInt(sInput.nextToken());

            nodes[i] = new Node(t, s);
        }

        Arrays.sort(nodes);

        int count = 0;
        int max = 0;
        int[] maxCount = new int[N];
        for(int i = N-1; i >= 0; i--){
            if(max < nodes[i].s){
                max = nodes[i].s;
                count = 1;
            }else if( max == nodes[i].s) count++;
            maxCount[i] = count;
        }

        while(Q-- > 0){
            int p = Integer.parseInt(in.readLine());

            int left = 0;
            int right = N - 1;
            while(left <= right){
                int mid = (left + right) / 2;

                if(nodes[mid].t >= p) right = mid - 1;
                else left = mid + 1;
            }

            if(left < N) sb.append(maxCount[left]).append("\n");
            else sb.append("0\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
