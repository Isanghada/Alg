package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16974
// - 재귀 & DP : 미리 햄버거 크기와 패티 개수를 구하고, 재귀를 통해 분할 정복!
public class _14_Solution_1 {
    // TOTAL : 햄버거의 크기
    // PAT : 패티의 개수
    public static long[] TOTAL, PAT;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 버거 레벨
        long X = Long.parseLong(st.nextToken());    // 먹을 개수

        TOTAL = new long[N+1];  // 레벨별 버거 크기
        PAT = new long[N+1];    // 레벨별 패티 개수
        
        // 0레벨 기준 설정
        TOTAL[0] = 1;
        PAT[0] = 1;

        // 레벨별 계산
        for(int n = 1; n <= N ; n++){
            TOTAL[n] = TOTAL[n-1] * 2 + 3;  // 이전 버거 *2 + 3(번 2개, 패티 1개)
            PAT[n] = PAT[n-1] * 2 + 1;      // 이전 버거 * 2 + 1
        }

//        System.out.println(Arrays.toString(TOTAL));
//        System.out.println(Arrays.toString(PAT));

        // 정답 출력
        // - 재귀를 통해 먹은 패티의 수 계산
        sb.append(getPatCount(N, X));
        System.out.println(sb.toString());
    }

    private static long getPatCount(int n, long x) {
        // 레벨이 0인 경우 : 먹을 개수가 0이면 0, 아니면 1 반환
        if(n == 0) return (x == 0 ? 0 : 1);

        // 레벨이 1 이상인 경우!
        // 1. 먹을 개수가 1인 경우 : 0 반환
        if(x == 1) return 0;
        // 2. 먹을 개수가 중간 패티 미만인 경우 : 레벨과 먹을 개수 1씩 감소
        else if(x < (2 + TOTAL[n-1]) ) return getPatCount(n-1, x-1);
        // 3. 먹을 개수가 중간 패티까지인 경우 : n-1레벨의 패티의 수 + 1
        else if(x == (2 + TOTAL[n-1]) ) return PAT[n-1] + 1;
        // 4. 먹을 개수가 마지막 번 전일 경우 : n-1레벨의 패티의 수 + 1 + 남은 구간의 패티의 수
        else if(x < TOTAL[n] ) return PAT[n-1] + 1 + getPatCount(n-1, x-TOTAL[n-1]-2);
        // 5. 전부 먹을 경우 : n레벨의 패티의 수
        else return PAT[n];
    }
}

