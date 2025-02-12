package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16432
// - DP : 이전 날에 준 떡 종류를 활용해 현재 가능한 경우 체크
public class _12_Solution_1 {
    static final int RICE_CAKE = 9;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 떡을 팔아야할 날의 수
        int N = Integer.parseInt(in.readLine());

        // 날짜별 떡 종류 입력 
        StringTokenizer st = null;
        List<Integer>[] riceCakes = new List[N+1];
        for(int n = 1; n <= N; n++) {
            riceCakes[n] = new ArrayList<>();

            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());
            while(M-- > 0) riceCakes[n].add(Integer.parseInt(st.nextToken()));
        }

        // DP 초기화
        // - dp[day][riceCake] : day 날에 riceCake 종류를 주기위한 이전날의 떡 종류
        int[][] dp = new int[N+1][RICE_CAKE+1];

        // 초기값 설정
        for(int riceCake : riceCakes[1]) dp[1][riceCake] = riceCake;

        for(int day = 2; day <= N; day++){
            // 현재 떡 종류
            for(int riceCake : riceCakes[day]){
                // 이전 날의 떡 종류
                for(int past = 1; past <= RICE_CAKE; past++){
                    // 가능한 경우 dp 갱신 후 종료
                    if(riceCake != past && dp[day-1][past] != 0){
                        dp[day][riceCake] = past;
                        break;
                    }
                }
            }
        }

        // for(int[] d : dp) System.out.println(Arrays.toString(d));

        // 마지막 날부터 전달할 떡 확인
        int day = N;
        int riceCake = 1;
        while(day > 0){
            while(riceCake <= RICE_CAKE && dp[day][riceCake] == 0) riceCake++;
            if(riceCake > RICE_CAKE) {
                sb.setLength(0);
                sb.append(-1);
                break;
            }

            sb.insert(0, riceCake).insert(1, "\n");
            // System.out.println(sb.toString()+"====="+dp[day][riceCake]);
            riceCake = dp[day--][riceCake];
        }

        // 결과 반환
        System.out.println(sb);
    }
}
