package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/23048
// - 에라토스테네스의 체 : 소수의 배수는 모두 같은 색으로 통일!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 자연수 N
        int N = Integer.parseInt(in.readLine());
        // 각 자연수의 색 번호
        int[] color = new int[N+1];
        // 각 자연수의 색칠 여부
        boolean[] visited = new boolean[N+1];

        // 1번은 1로 고정
        color[1] = 1;
        // 사용한 색 개수
        int count = 1;
        for(int num = 2; num <= N; num++){
            // 이미 색칠한 경우 패스
            if(visited[num]) continue;

            // 색 증가
            count++;
            // 현재 숫자의 모든 배수를 같은 색으로 색칠
            for(int check = num; check <= N; check += num){
                // 이미 색칠한 경우 패스
                if(visited[check]) continue;
                color[check] = count;
                visited[check] = true;
            }
        }

        sb.append(count).append("\n");
        for(int num = 1; num <= N; num++)
            sb.append(color[num]).append(" ");

        // 정답 반환
        System.out.println(sb);
    }
}

