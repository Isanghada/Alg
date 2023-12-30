package _2023._202308;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17831
// - Tree DP 문제라길래 도전!
// - 진땀승!!!! 우와ㅏㅏㅏㅏㅏㅏㅏ
// - 먼저, 현재 노드를 사용하지 않는 경우를 구하고
// - 이를 토대로 현재 노드를 사용하는 경우를 구해주면 댄다!
public class _23_Solution_1 {
    // 판매원 수
    public static int LEN;
    // dp
    public static int[][] dp;
    // 판매원 실력 수치
    public static int[] valueArr;
    // 판매원 인접 리스트
    public static List<Integer>[] adjList;
    public static void main(String[] args) throws Exception {
        // 텍스트 파일로 입력을 받도록 설정
        System.setIn(new FileInputStream("src/_2023/_202308/_23_input.txt"));

        // 입력, 출력 설정
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 변수 입력
        // 판매원 수는 +1 해줌 : 배열 선언시 1~N을 사용하기 위해!
        LEN = Integer.parseInt(in.readLine()) + 1;
        // dp 초기화
        // - 초기값은 -1로 설정 : -1이 아닌 경우 함수를 다시 실행하지 않도록!
        dp = new int[LEN][2];
        for(int i = 0; i < LEN; i++) Arrays.fill(dp[i], -1);

        // 판매원 실력 초기화
        valueArr = new int[LEN];
        
        //인접 리스트 초기화
        adjList = new ArrayList[LEN];
        for(int i = 1; i < LEN; i++) adjList[i] = new ArrayList<>();

        // 인접 리스트 입력
        // - 단방향이므로 한 방향으로만 추가
        st = new StringTokenizer(in.readLine());
        for(int child = 2; child < LEN; child++){
            int p = Integer.parseInt(st.nextToken());
            adjList[p].add(child);
        }

        // 판매원 실력 입력
        st = new StringTokenizer(in.readLine());
        for(int idx = 1; idx < LEN; idx++) valueArr[idx] = Integer.parseInt(st.nextToken());

        // 루트 노드부터 Bottom-Up 방식으로 모든 노드 탐색
        getMaxValue(1);

        // 루트를 사용하는 경우와 사용하지 않는 경우 중 최대값 선택
        sb.append(Math.max(dp[1][0], dp[1][1]));
        System.out.println(sb);
    }

    // 최대값을 구하는 함수 : Bottom-up 방식의 DP 활용
    // - pos : 시작 인덱스
    private static void getMaxValue(int pos) {
        // 해당 판매원의 값이 -1이 아닌 경우 종료 : 이미 게산된 상태
        if(dp[pos][0] != -1 && dp[pos][1] != -1) return;

        // 값 초기화
        dp[pos][0] = 0;
        dp[pos][1] = 0;

        // 리프 노드일 경우 종료
        if(adjList[pos].size() == 0) return;

        // 각 부사수 인원들이 멘토링을 하였는지 여부를 확인할 Map
        Map<Integer, Boolean> isChildUsed = new HashMap<>();
        
        // 사수가 멘토링을 진행하지 않는 경우 계산
        for(int child : adjList[pos]){
            // 부사수에 대해 최대값 계산 함수 실행
            getMaxValue(child);
            
            // 부사수가 멘토링 하는 경우와 하지 않는 경우 중 최대값 선택
            dp[pos][0] += Math.max(dp[child][0], dp[child][1]);
            
            // 멘토링 여부 체크
            if(dp[child][0] >= dp[child][1]) isChildUsed.put(child, false);
            else isChildUsed.put(child, true);
        }

        // 사수가 멘토링을 진행하는 경우 계산
        for(int child : adjList[pos]){
            // 현재값과 현재 부사수와 멘토링 하는 경우 중 최대값 선택
            // - dp[pos][1] : 현재까지 멘토링 하는 경우의 최대값
            // - newValue : 사수가 멘토링을 진행하지 않는 경우를 활용해 계산
            //   1. 부사수가 멘토링을 진행하는 경우 : dp[pos][0] + "dp[child][0] - dp[child][1]" + valueArr[pos] * valueArr[child]
            //      - 부사수가 진행하는 원래의 멘토링을 제거하고 현재 사수와 멘토링 하도록 계산
            //   2. 부사수가 멘토링을 진행하지 않는 경우 : dp[pos][0] + valueArr[pos] * valueArr[child]
            //      - 부사수가 현재 사수가 멘토링하는 경우 계산
            dp[pos][1] = Math.max(dp[pos][1], dp[pos][0] + valueArr[pos] * valueArr[child]
                                                + (isChildUsed.get(child) ? dp[child][0] - dp[child][1] : 0));
        }
    }
}
