package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 플로이드 : 플로이드 와샬을 통해 큰 경우, 작은 경우 모두 탐색!
//              큰 경우 혹은 작은 경우가 절반보다 많은경우 중간이 될 수 없으므로 정답 증가!!
public class _04_Solution_1 {
    public static final int MAX = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 구슬의 수
        int M = Integer.parseInt(st.nextToken());   // 저울 사용 횟수

        // 작은 경우
        int[][] smalls = new int[N][N];
        // 큰 경우
        int[][] bigs = new int[N][N];

        // 초기화!
        for(int r = 0; r < N; r++){
            Arrays.fill(smalls[r], MAX);
            Arrays.fill(bigs[r], MAX);
            smalls[r][r] = 0;
            bigs[r][r] = 0;
        }

        // 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int big = Integer.parseInt(st.nextToken()) - 1;
            int small = Integer.parseInt(st.nextToken()) - 1;

            smalls[small][big] = 1;
            bigs[big][small] = 1;
        }

        // 플로이드 와샬을 통해 smalls, bigs 모두 계산!
        for(int k = 0; k < N; k++){
            for(int s = 0; s < N; s++){
                for(int e = 0; e < N; e++){
                    smalls[s][e] = Math.min(smalls[s][e], smalls[s][k]+smalls[k][e]);
                    bigs[s][e] = Math.min(bigs[s][e], bigs[s][k]+bigs[k][e]);
                }
            }
        }

        // 정답
        int answer = 0;
        // 절반 개수!
        final int HALF = (N+1) / 2;
        // 모든 노드 탐색
        for(int node = 0; node < N; node++){
            // 작은 경우 혹은 큰 경우가 절반 이상인 경우 절반이 될 수 없음!
            if(getCount(smalls, node) >= HALF ||
                    getCount(bigs, node) >= HALF
            ) answer++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 개수 계산 함수 : 연결된 경우 작은 혹은 큰 것을 의미하므로 연결된 노드의 개수 탐색!
    private static int getCount(int[][] info, int start) {
        int count = 0;

        for(int end = 0; end < info.length; end++){
            if(info[start][end] != MAX && start != end) count++;
        }

        return count;
    }

}
