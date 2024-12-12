package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9177
// - BFS : 순서대로 가능한 조합을 찾아가면 탐색
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 데이터의 수
        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(in.readLine());
            String a = st.nextToken();      // 사용 단어
            String b = st.nextToken();      // 사용 단어
            String target = st.nextToken(); // 타겟 단어

            // bfs를 통해 가능한 조합이 있는지 확인
            sb.append("Data set ").append(t).append(": ").append(bfs(a, b, target)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
    private static final int[][] DELTA = new int[][]{{0, 1}, {1, 0}};
    private static String bfs(String a, String b, String target) {
        Deque<int[]> deque = new LinkedList<>();
        boolean[][] visited = new boolean[a.length()+1][b.length()+1];

        deque.offerLast(new int[]{0, 0, 0});
        visited[0][0] = true;

        while(!deque.isEmpty()){
            int[] cur = deque.pollFirst();

            if(cur[2] == target.length()) return "yes";
            for(int[] d : DELTA){
                int[] next = new int[]{cur[0]+d[0], cur[1]+d[1], cur[2]+1};
                if(next[0] <= a.length() && next[1] <= b.length() &&
                        !visited[next[0]][next[1]] &&
                        ((d[0] == 1 && target.charAt(cur[2]) == a.charAt(cur[0]) ||
                                (d[1] == 1 && target.charAt(cur[2]) == b.charAt(cur[1]))))
                ){
                  deque.offerLast(next);
                  visited[next[0]][next[1]] = true;
                }
            }
        }

        return "no";
    }
}
