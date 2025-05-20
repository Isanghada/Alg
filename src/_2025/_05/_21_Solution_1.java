package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12875
// - 플로이드-워셜 : 최대 친구 거리를 플로이드 워셜로 계산!
//                   최대값 (1_000_000)인 경우 친구가 아닌 경우를 의미하며 이 경우는 무한대!
public class _21_Solution_1 {
    static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int d = Integer.parseInt(in.readLine());

        int[][] map = new int[N][N];
        for(int r = 0; r < N; r++){
            char[] input = in.readLine().toCharArray();
            for(int c = 0; c < N; c++) {
                if(input[c] == 'Y') map[r][c] = 1;
                else map[r][c] = MAX;
            }
            map[r][r] = 0;
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                answer = Math.max(answer, map[i][j]);
            }
        }

        if(answer == MAX) answer = -1;
        else answer *= d;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}