package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17089
// - 브루트포스 : 모든 경우 탐색!
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());   // 사람 수
        int M = Integer.parseInt(st.nextToken());   // 친구 관계의 수

        // 인접 행렬 초기화
        boolean[][] adjArr = new boolean[N+1][N+1];
        // 친구의 수 초기화
        int[] countArr = new int[N+1];
        // 친구 관계 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 친구 관계 추가
            adjArr[a][b] = true;
            adjArr[b][a] = true;

            // 친구의 수 증가
            countArr[a]++;
            countArr[b]++;
        }

        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 모든 친구 관계 조사 : 세 명이 모두 친구여야 하므로 아닌 경우는 가지치기
        for(int a = 1; a <= N; a++){
            for(int b = a+1; b <= N; b++){
                // a와 b가 친구가 아닌 경우 패스
                if(!adjArr[a][b]) continue;
                for(int c = b+1; c <= N; c++){
                    // a와 c 혹은 b와 c가 친구가 아닌 경우 패스
                    if(!adjArr[a][c] || !adjArr[b][c]) continue;

                    // 친구 수를 합하고 a, b, c는 제외해야하므로 -6
                    int total = countArr[a] + countArr[b] + countArr[c] - 6;
                    // 현재 정답과 비교해 최소값 선택
                    answer = Math.min(answer, total);
                }
            }
        }

        // 정답 반환
        // - 최소값 반환
        // - 세 사람을 고를 수 없는 경우 -1 반환
        sb.append(answer == Integer.MAX_VALUE ? -1 : answer);
        System.out.println(sb);
    }
}
