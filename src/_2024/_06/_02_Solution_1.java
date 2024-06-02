package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12915
// - 이분 탐색 : 대회 개최의 수를 기준으로 이분 탐색 진행
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int E = Integer.parseInt(st.nextToken());   // 난이도 쉬움
        int EM = Integer.parseInt(st.nextToken());  // 난이도 쉬움/중간
        int M = Integer.parseInt(st.nextToken());   // 난이도 중간
        int MH = Integer.parseInt(st.nextToken());  // 난이도 중간/어려움
        int H = Integer.parseInt(st.nextToken());   // 난이도 어려움

        // 대회의 수 초기화
        int answer = 0;

        // 최소 대회 수
        int left = 0;
        // 최대 대회 수
        int right = 200000;
        // 이분 탐색을 통해 최대값 탐색
        while(left <= right){
            // 중간값 계산
            int mid = (left + right) / 2;
            // mid 개수의 대회를 개최할 수 있다면 answer, left 갱신
            if(isPossible(mid, E, EM, M, MH, H)){
                answer = mid;
                left = mid + 1;
            // 불가능한 경우 right 갱신
            }else right = mid - 1;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 대회 개최 가능 여부 함수 : target 개수의 대회를 개최할 수 있는지 확인
    private static boolean isPossible(int target, int e, int em, int m, int mh, int h) {
        // 난이도 쉬움 체크
        if(e < target){
            if(e + em < target) return false;
            em -= target - e;
        }
        // 난이도 중간 체크
        if(m < target){
            if(m + em + mh < target) return false;
            m += em;
            if(m < target) mh -= target - m;
        }
        // 난이도 어려움 체크
        if(h < target && (h + mh < target)) return false;

        return true;
    }
}
