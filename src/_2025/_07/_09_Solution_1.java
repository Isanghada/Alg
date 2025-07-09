package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/30013
// - 브루트포스 : 모든 귀뚜라미 울음의 주기가 같으므로 주기를 기준으로 귀뚜라미의 수 탐색!
//                귀뚜라미 최소 개체 수를 계산하여 출력
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());        // 측정 시간
        char[] sounds = in.readLine().toCharArray();    // 측정 기록

        // 정답 초기화
        int answer = N;
        // 귀뚜라미 울음 주기를 기준으로 탐색
        for(int n = 1; n <= N; n++){
            boolean[] visited = new boolean[N];
            int count = 0;  // 현재 주기에서의 귀뚜라미 개체 수
            // 모든 측정 기록 탐색
            for(int i = 0; i < N; i++){
                // 이미 방문했거나 귀뚜라미 울음이 아닌 경우 패스
                if(visited[i] || sounds[i] == '.') continue;
                
                // 방문 표시
                visited[i] = true;
                // 귀뚜라미 개체 수 증가
                count++;

                // 주기를 기준으로 다음 울음 체크
                int next = i + n;
                while(next < N && sounds[next] == '#'){
                    visited[next] = true;
                    next += n;
                }
            }

            // System.out.println(n+", "+count+"===");
            // 최소값 갱신
            answer = Math.min(answer, count);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
