package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25556
// - DP : 각 캐릭터로 가능한 경우를 계산하여 최대값 합산!
public class _12_Solution_1 {
    // 보스 정보 클래스
    public static class Boss{
        long hp;     // 체력
        int gold;   // 골드

        public Boss(long hp, int gold){
			this.hp = hp;
			this.gold = gold;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 캐릭터의 수
        int M = Integer.parseInt(st.nextToken());   // 사용할 캐릭터의 수
        int K = Integer.parseInt(st.nextToken());   // 보스의 수

        // 캐릭터별 데미지 정보 입력
        long[] damageArr = new long[N];
        for(int i = 0; i < N; i++) damageArr[i] = Long.parseLong(in.readLine());

        // 보스 정보 입력
        Boss[] bossArr = new Boss[K+1];
        for(int i = 1; i <= K; i++){
            st = new StringTokenizer(in.readLine());
            bossArr[i] = new Boss(
                    Long.parseLong(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 각 캐릭터별 최대 골드 획득량 배열
        Integer[] maxGold = new Integer[N];
        // 캐릭터별로 최대 골드량 계산
        for(int i = 0; i < N; i++){
            // 현재 캐릭터의 각 보스 클리어 시간 계산
            // - 올림을 통해 시간 계산
            long[] timeArr = new long[K+1];
            for(int j = 1; j <= K; j++){
                timeArr[j] = bossArr[j].hp / damageArr[i];
                if(bossArr[j].hp % damageArr[i] != 0) timeArr[j]++;
            }

            // dp 배열 초기화
            // - dp[bossIdx][time] : time 시간으로 bossIdx까지 중 최대 골드량
            int[][] dp = new int[K+1][901];

            // 모든 보스 계산
            for(int bossIdx = 1; bossIdx <= K; bossIdx++){
                for(int time = 1; time < 901; time++){
                    if(time >= timeArr[bossIdx]){
                        dp[bossIdx][time] = Math.max(
                                dp[bossIdx-1][time],
                                bossArr[bossIdx].gold +
                                dp[bossIdx-1][(int)(time - timeArr[bossIdx])]
                        );
                    }else dp[bossIdx][time] = dp[bossIdx-1][time];
                }
            }
            // 최대값 입력
            maxGold[i] = dp[K][900];
        }
        // 오름차순 정렬
        Arrays.sort(maxGold, Comparator.reverseOrder());

        // 정답 초기화
        int answer = 0;
        // 골드량이 높은 순에서 M개 합산
        while(M-- > 0) answer += maxGold[M];

        sb.append(answer);
        System.out.println(sb.toString());
    }
}
