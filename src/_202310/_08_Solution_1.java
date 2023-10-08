package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1107
// - dp 활용 : 모든 조합을 찾고 목표 채널의 최소값이 되는 경우를 찾음
// - 버튼으로 각 채널을 이동하는 경우 + (+1, -1) 이동 횟수
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 이동할 채널 입력
        int N = Integer.parseInt(in.readLine());
        // 고장난 버튼의 개수
        int M = Integer.parseInt(in.readLine());

        // 사용 가능한 버튼
        Set<Integer> useNumber = new HashSet<>();
        for(int number = 0; number < 10; number++) useNumber.add(number);
        // 고장난 버튼 제외
        if(M > 0){
            // 고장난 버튼 종류
            st = new StringTokenizer(in.readLine());
            // 고장난 버튼을 useNumber에서 제외
            while(st.hasMoreTokens()) useNumber.remove(Integer.parseInt(st.nextToken()));
        }

        // 가장 크게 만들 수 있는 값이 999999이므로 해당 값까지 체크
        int[] dp = new int[1000000];
        // - 모든 값을 최대값으로 설정
        Arrays.fill(dp, Integer.MAX_VALUE);
        // - 사용 가능한 버튼이 있다면 해당 버튼의 횟수를 1로 변경 : 숫자 버튼 이동
        for(int number : useNumber) dp[number] = 1;

        // 1번부터 999999번까지 숫자 버튼으로 이동하는 모든 경우 계산
        for(int channel = 1; channel < 1000000; channel++){
            // 이동할 수 없는 채널일 경우 패스
            if(dp[channel] == Integer.MAX_VALUE) continue;
            // 사용 가능한 숫자 버튼을 눌러 새 채널로 이동
            for(int number : useNumber){
                // 현재 채널 *10 + 새로운 숫자 버튼
                int next = channel * 10 + number ;
                // 1000000 이상일 경우 패스 : 숫자 버튼으로 만들 수 있는 최대값은 999999!
                if(next >= 1000000) continue;
                // 현재 채널까지의 숫자 버튼 회숫 + 1 입력
                dp[next] = dp[channel]+1;
            }
        }

//        for(int idx = 5450; idx <= 5460; idx++){
//            System.out.print("(idx="+idx+", value="+dp[idx]+"), ");
//        }
//        System.out.println();

        // 기본 채널인 100에서 +1, -1로 이동하는 횟수를 정답으로 초기화
        int answer = Math.abs(N - 100);
        // 모든 이동 가능한 채널에서의 값과 비교하여 최소값으로 변경
        for(int channel = 0; channel < 1000000; channel++){
            if(dp[channel] == Integer.MAX_VALUE) continue;
            // 값을 비교하여 최소값으로 변경
            // - 현재 정답
            // - channel에서 목표 값으로 이동하는 경우 : dp[channel] + (+, - 이동 횟수)
            answer = Math.min(answer, Math.abs(N-channel) + dp[channel]);
        }
        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
