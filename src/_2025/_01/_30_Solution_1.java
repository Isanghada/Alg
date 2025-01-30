package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/1229
// - DP : 1. 육각수의 점의 개수를 계산 => 배열 h
//        2. 육각수 점의 개수를 활용해 N개의 점을 만들기 위한 최소 개수 계산
public class _30_Solution_1 {
    static final int MAX = 146858;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 점의 개수
        int N = Integer.parseInt(in.readLine());
        
        // 육각수의 점의 개수
        List<Integer> h = hexagon();
        // System.out.println(h.size()+", "+h.get(h.size()-1));

        // dp 초기화
        // - dp[n] : 점 n개를 만들기 위한 최소 육각수의 개수
        int[] dp = new int[N+1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        dp[1] = 1;

        // 가능한 모든 경우 탐색
        for(int i = 2; i <= N; i++){
            for(int j = 0; j < h.size();j++){
                if(h.get(j) > i) break;
                dp[i] = Math.min(dp[i], dp[i-h.get(j)]+1);
            }
        }

        // 정답 반환
        sb.append(dp[N]);
        System.out.println(sb);
    }

    private static List<Integer> hexagon() {
        List<Integer> hexagon = new ArrayList<>();
        hexagon.add(1);
        for(int i = 2; ; i++){
            int num = (i-1)*6+ hexagon.get(i-2) - (2*(i-1)-1);
            if(num <= 1_000_000) hexagon.add(num);
            else break;
        }

        return hexagon;
    }
}