package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9489
// - 트리 : 연속된 수를 같은 부모를 가지는 자식으로 설정! 사촌 조건이 만족하는지 확인
//          사촌이 되기 위해선 아래의 조건을 만족해야한다
//            1. 부모가 다르다.
//            2. 부모의 부모가 같다.
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 노드의 수
            int K = Integer.parseInt(st.nextToken());   // 타겟

            // 종료 조건
            if(N == 0 && K == 0) break;

            // 타겟 초기화
            int target = 0;
            // 노드 정보
            int[] arr = new int[N+1];
            // 부모 정보
            int[] parents = new int[N+1];
            // 부모 노드 인덱스
            int idx = -1;
            
            // 초기값 설정
            arr[0] = -1;
            parents[0] = -1;

            // 노드 정보 입력
            st = new StringTokenizer(in.readLine());
            for(int i = 1; i <= N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] == K) target = i;
                if(arr[i] != arr[i-1] + 1) idx++;
                parents[i] = idx;
            }

            // 모든 값과 비교하여 사촌의 수 계산
            int answer = 0;
            for(int i = 1; i <= N; i++){
                if(parents[i] != parents[target] && parents[parents[i]] == parents[parents[target]]){
                    answer++;
                }
            }

            sb.append(answer).append("\n");
        }
        // 정답 출력
        System.out.println(sb);
    }

}
