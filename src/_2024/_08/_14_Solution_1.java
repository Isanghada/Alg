package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/16500
// - DP : 마지막 인덱스부터 시작하여 문자로 만들 수 있는지 확인!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 타겟 문자열
        String s = in.readLine();

        // 단어의 개수
        int N = Integer.parseInt(in.readLine());
        // 단어 목록
        Set<String> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            set.add(in.readLine());
        }

        // DP 초기화
        int[] dp = new int[s.length()+1];
        dp[s.length()] = 1;
        // 마지막 인덱스부터 차례로 탐색!
        // - [start, last] 범위의 부분 문자열이 포함되는지 확인!
        for(int start = s.length()-1; start >= 0; start--){
            for(int last = start+1; last <= s.length(); last++){
                // - [last, ...]의 부분 문자열이 가능하고
                //   [start, last]의 부분 문자열이 단어 목록에 존재하면 가능!
                if(dp[last] == 1 && set.contains(s.substring(start, last))){
                    dp[start] = 1;
                }
            }
        }

        // 정답 출력
        sb.append(dp[0]);
        System.out.println(sb.toString());
    }
}

