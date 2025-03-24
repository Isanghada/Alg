package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23061
// - DP : 배낭 문제
//        각 물건을 (선택 / 미선택)시의 가치를 비교하여 최대인 경우 선택
public class _24_Solution_1 {
    // 물건 클래스
    static class Item{
        int weight; // 무게
        int value;  // 가치
        public Item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
    static final int MAX_VALUE = 3_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물건의 수
        int M = Integer.parseInt(st.nextToken());   // 가방의 수

        // 물건 정보
        Item[] items = new Item[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            items[i] = new Item(
                    Integer.parseInt(st.nextToken()),   // 무게
                    Integer.parseInt(st.nextToken())    // 가치
            );
        }

        // 가방 최대 무게
        int maxWeight = 0;
        // 가방 정보 입력
        int[] bags = new int[M+1];
        for(int i = 1; i <= M; i++){
            bags[i] = Integer.parseInt(in.readLine());
            maxWeight = Math.max(maxWeight, bags[i]);
        }

        // dp 초기화
        // - dp[n][m] : 무게 m에서 n번째 물건까지 체크할 경우 최대 가치
        int[][] dp = new int[N+1][maxWeight+1];
        for(int i = 1; i <= N; i++){
            for(int w = 1; w <= maxWeight; w++){
                // 초기값 설정 : (n-1)번째 물건까지의 가치, (w-1)무게의 최대 가치 중 선택
                dp[i][w] = Math.max(dp[i-1][w], dp[i][w-1]);
                // i번째 물건을 선택하는 경우 최대 가치 계산
                if(w >= items[i].weight){
                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i-1][w - items[i].weight]+items[i].value
                    );
                }
            }
            // System.out.println(Arrays.toString(dp[i]));
        }

        // 정답 초기화
        int answer = 0;
        double maxValue = -1;
        for(int m = 1; m <= M; m++){
            int weight = bags[m];
            double value = (double)dp[N][weight] / weight;
            // System.out.println(dp[N][weight]+", "+weight+", "+value);

            if(value > maxValue){
                answer = m;
                maxValue = value;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
