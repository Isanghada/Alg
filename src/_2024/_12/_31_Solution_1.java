package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24041
// - 누적합
//   1. 양 옆의 강의실과 이웃하기 때문에 공사 여부에 따라 다른 집합으로 분리
//   2. 첫 번째와 마지막 강의실이 이웃하고 있으므로 공사 여부에 따라
//      마지막 강의실 집합을 첫 번째 강의실 집합으로 변경
//   3. 각 집합별 최소 돌 개수의 합이 가능한지 체크
public class _31_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 강의실 수
        int M = Integer.parseInt(st.nextToken());   // 공사 구간 수
        long K = Long.parseLong(st.nextToken());    // 돌의 수

        // 강의실별 돌 개수 입력
        st = new StringTokenizer(in.readLine());
        int[] S = new int[N+1];
        for(int i = 1; i <= N; i++) S[i] = Integer.parseInt(st.nextToken());

        // 강의실별 번호 초기화
        int[] lectureSet = new int[N+1];
        // 첫 번째, 마지막 강의실 연결 여부
        boolean firstLastConnection = true;
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // 공사 구간
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            // j가 작은 경우 스왑
            if(i > j){
                int temp = i;
                i = j;
                j = temp;
            }

            // 첫 번째, 마지막 강의실 구간 공사인 경우 플래그 변경
            if(i == 1 && j == N) firstLastConnection = false;
            // 강의실 번호가 큰 쪽 집합 번호 1 증가
            else lectureSet[j] = 1;
        }

        // 첫 번째 강의실의 집합 번호 1
        lectureSet[1] = 1;
        // 누적합을 통해 다른 강의실의 집합 번호 계산
        for(int idx = 2; idx <= N; idx++) lectureSet[idx] += lectureSet[idx-1];

        // 첫 번째, 마지막 강의실 연결되어 있는 경우
        // - 마지막 강의실 집합을 첫 번째 강의실 집합으로 변경
        if(firstLastConnection){
            int set = lectureSet[N];
            int idx = N;
            while(idx > 1 && lectureSet[idx] == set) lectureSet[idx--] = 1;
        }

        // System.out.println(Arrays.toString(lectureSet));

        // 초기 정보 설정
        // - 첫 번째 강의실 집합(1)로 설정
        // - 최소 돌 개수 초기화
        // - 정답 초기화
        int set = 1;
        int minCount = Integer.MAX_VALUE;
        long answer = 0;

        // 마지막 강의실 번호 : 첫 번째, 마지막이 연결된 경우 해당 부분 미리 체크
        int last = N;
        for(; last > 0 && lectureSet[last] == 1; last--){
            if(S[last] < minCount) minCount = S[last];
        }

        // 첫 번재 강의실부터 last까지 탐색
        for(int idx = 1; idx <= last; idx++){
            // 집합 번호가 다른 경우
            if(lectureSet[idx] != set){
                // asnwer : 이전 집합의 최소 돌 개수만큼 증가
                answer += minCount;
                // 새로운 집합 정보로 초기화
                set = lectureSet[idx];
                minCount = S[idx];
            // 집합 번호가 같은 경우 : 최소 돌 개수 갱신!
            }else if(S[idx] < minCount) minCount = S[idx];
        }
        // 탐색 이후 집합이 1인 경우 => 모든 강의실이 연결된 경우
        // 따라서, 집합이 1이 아닌 경우에만 answer 증가
        if(set != 1) answer += minCount;

        // 강의실 연결 가능 여부 반환
        sb.append((answer <= K) ? "YES" : "NO");


        // 정답 반환
        System.out.println(sb);
    }
}