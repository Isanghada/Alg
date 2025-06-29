package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30405
// -
public class _30_Solution_1 {
    static class Food{
        int p;
        int s;
        public Food(int p, int s){
            this.p = p;
            this.s = s;
        }
    }
    static int answer, N;
    static Food[] foods;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        int caseNumber = 1;
        while(T-- > 0){
            N = Integer.parseInt(in.readLine());

            foods = new Food[N];
            visited = new boolean[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                foods[i] = new Food(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
            }

            answer = 0;
            dfs(0);

            sb.append("Case #").append(caseNumber++).append(": ").append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void dfs(int time) {
        answer = Math.max(answer, time);

        for(int i = 0; i < N; i++){
            if(!visited[i] && time <= foods[i].p){
                visited[i] = true;
                dfs(time + foods[i].s);
                visited[i] = false;
            }
        }
    }
}