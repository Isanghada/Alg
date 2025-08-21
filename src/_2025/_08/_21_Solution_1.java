package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25515
// -
public class _21_Solution_1 {
    static int N;
    static List<Integer>[] ADJ_LIST;
    static int[] SCORES;
    static long[] DP;
    static boolean[] VISITED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());

        ADJ_LIST = new List[N];
        for(int i = 0; i < N; i++) ADJ_LIST[i] = new ArrayList<>();

        StringTokenizer st = null;
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ADJ_LIST[p].add(c);
            ADJ_LIST[c].add(p);
        }

        SCORES = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        VISITED = new boolean[N];
        DP = new long[N];

        // 정답 출력
        sb.append(getDP(0));
        System.out.println(sb);
    }

    private static long getDP(int target) {
        if(!VISITED[target]){
            VISITED[target] = true;
            DP[target] = SCORES[target];
            for(int next : ADJ_LIST[target]){
                if(VISITED[next]) continue;
                DP[target] = Math.max(DP[target], DP[target] + getDP(next));
            }
        }
        return DP[target];
    }
}