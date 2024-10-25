package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16494
// - DP : 각 범위별 최대값 계산!
public class _25_Solution_1 {
    // 최대값
    public static final int MAX = 100_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수열 길이
        int M = Integer.parseInt(st.nextToken());   // 그룹 개수

        // 수열 정보
        int[] num = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::new).toArray();

        // DP 초기화
        int[][] dp = new int[N+1][N+1];
        for(int r = 1; r <= N; r++) {
            for(int c = r; c <= N; c++) dp[r][c] = -MAX;
        }

        // 각 범위별 합!
        int[][] sum = new int[N+1][N+1];
        for(int r = 0; r < N; r++){
            for(int c = 1; c <= N; c++){
                if(r + c > N) break;
                sum[c][c+r] = sum[c][c+r-1] + num[r+c];
            }
        }

        // sum을 활용해 범위별 최대값 탐색!
        for(int dpStart = 1; dpStart <= N; dpStart++){
            for(int dpEnd = dpStart; dpEnd <= N; dpEnd++){
                for(int sumStart = dpStart; sumStart <= dpEnd; sumStart++){
                    for(int sumEnd = sumStart; sumEnd <= dpEnd; sumEnd++){
                        dp[dpStart][dpEnd] = Math.max(dp[dpStart][dpEnd], sum[sumStart][sumEnd]);
                    }
                }
            }
        }

        // 선택 범위!
        int[] select = new int[N];
        for(int idx = N-M+1; idx < N; idx++) select[idx] = 1;

        // 정답 초기화
        int answer = -MAX;
        do{
            // 점수
            int score = 0;
            // 시작!
            int start = 1;
            for(int end = 0; end < N; end++){
                if(select[end] == 1){
                    score += dp[start][end+1];
                    start = end + 2;
                }
            }
            if(start <= N){
                score += dp[start][N];
                answer = Math.max(answer, score);
            }
        }while(nextPermutation(select));

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean nextPermutation(int[] select) {
        int len = select.length;

        int i = len - 1;
        while(i > 0 && select[i] <= select[i-1]) i--;

        if(i == 0) return false;

        int j = len - 1;
        while(select[j] <= select[i-1]) j--;

        swap(select, i-1, j);
        int k = len-1;
        while(i < k) swap(select, i++, k--);

        return true;
    }

    private static void swap(int[] select, int i, int j) {
        int temp = select[i];
        select[i] = select[j];
        select[j] = temp;
    }
}