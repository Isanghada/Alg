package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5535
// - DP : 입을 수 있는 옷을 기준으로 가장 화려함 차이의 합이 큰 경우 선택!
public class _14_Solution_1 {
    // 옷 클래스
    static class Clothes{
        int min;    // 최저 기온
        int max;    // 최고 기온
        int value;  // 화려함
        public Clothes(int min, int max, int value){
            this.min = min;
            this.max = max;
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int D = Integer.parseInt(st.nextToken());   // 일 수
        int N = Integer.parseInt(st.nextToken());   // 옷 개수

        // 최고 기온 정보
        int[] maxTemperature = new int[D];
        for(int d = 0; d < D; d++) maxTemperature[d] = Integer.parseInt(in.readLine());

        // 옷 정보
        Clothes[] clothesArr = new Clothes[N];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            clothesArr[n] = new Clothes(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // dp 초기화
        int[][] dp = new int[D][N];

        // 첫째날 가능한 옷의 경우는 0으로 설정
        Arrays.fill(dp[0], -1);
        for(int n = 0; n < N; n++){
            if(clothesArr[n].min <= maxTemperature[0]
                    && maxTemperature[0] <= clothesArr[n].max){
                dp[0][n] = 0;
            }
        }

        // dp 초기화!
        for(int d = 1; d < D; d++) {
            // -1로 초기화
            Arrays.fill(dp[d], -1);
            int past = d - 1;
            // 모든 옷 탐색
            for(int n = 0; n < N; n++){
                // 가능한 옷인 경우!
                if(clothesArr[n].min <= maxTemperature[d]
                        && maxTemperature[d] <= clothesArr[n].max){
                    // 이전 날 중 중 최대값이 되는 경우 선택
                    for(int pastN = 0; pastN < N; pastN++){
                        if(dp[past][pastN] == -1) continue;
                        dp[d][n] = Math.max(dp[d][n],
                                            dp[past][pastN] +
                                            Math.abs(clothesArr[pastN].value - clothesArr[n].value));
                    }
                }
            }
        }

        // for(int[] d : dp) System.out.println(Arrays.toString(d));

        int answer = 0;
        for(int n = 0; n < N; n++) answer = Math.max(answer, dp[D-1][n]);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}

