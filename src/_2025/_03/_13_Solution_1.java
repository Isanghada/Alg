package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18866
// - 누적합 : 각 범위에서 아래의 값을 구하여 체크
//            1. 젊은 날 : 최소 행복도, 최대 피로도
//            2. 늙은 날 : 최대 행복도, 최소 피로도
//            => 젊은 날을 기준으로 늙은 날의 최대 행복도보다 높고 최소 피로도보다 낮은지 체크!
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(in.readLine());

        // 연도별 행복도, 피로도
        // - info[n][0] : n년의 행복도
        // - info[n][1] : n년의 피로도
        int[][] info = new int[N+1][2];

        StringTokenizer st = null;
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            info[n][0] = Integer.parseInt(st.nextToken());
            info[n][1] = Integer.parseInt(st.nextToken());
        }

        // 젊은 날의 정보
        int[] youngMinHappy = new int[N+2];
        int[] youngMaxFatigue = new int[N+2];
        
        // 늙은 날의 정보
        int[] oldMaxHappy = new int[N+2];
        int[] oldMinFatigue = new int[N+2];

        Arrays.fill(youngMinHappy, Integer.MAX_VALUE);
        Arrays.fill(oldMinFatigue, Integer.MAX_VALUE);

        for(int n = 1; n <= N; n++){
            if(info[n][0] == 0) youngMinHappy[n] = youngMinHappy[n-1];
            else youngMinHappy[n] = Math.min(youngMinHappy[n-1], info[n][0]);

            if(info[n][1] == 0) youngMaxFatigue[n] = youngMaxFatigue[n-1];
            else youngMaxFatigue[n] = Math.max(youngMaxFatigue[n-1], info[n][1]);


            if(info[N-n+1][0] == 0) oldMaxHappy[N-n+1] = oldMaxHappy[N-n+2];
            else oldMaxHappy[N-n+1] = Math.max(oldMaxHappy[N-n+2], info[N-n+1][0]);

            if(info[N-n+1][1] == 0) oldMinFatigue[N-n+1] = oldMinFatigue[N-n+2];
            else oldMinFatigue[N-n+1] = Math.min(oldMinFatigue[N-n+2], info[N-n+1][1]);
        }

        int answer = -1;
        for(int n = 1; n < N; n++){
            if(youngMinHappy[n] > oldMaxHappy[n+1] &&
                    youngMaxFatigue[n] < oldMinFatigue[n+1]){
                answer = n;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
