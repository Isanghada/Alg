package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3584
// - 그래프 탐색 : A, B 각 노드에서 루트까지 부모를 탐색하며 가장 가까운 부모 확인
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 노드 개수
            int N = Integer.parseInt(in.readLine());
            // 부모 노드 초기화!
            int[] parents = initParents(N);

            // 트리 정보 입력 : 자식 -> 부모로 찾아가기 위해 각 노드의 부모 노드 입력
            for(int n = 1; n < N; n++){
                st = new StringTokenizer(in.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                parents[child] = parent;
            }

            // 두 노드 입력
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // A 노드의 모든 조상 탐색!
            Set<Integer> set = new HashSet<>();
            while(parents[A] != A){
                set.add(A);
                A = parents[A];
            }

            // B부터 차례로 조상을 찾으며 공통 조상을 찾으면 종료!
            while(parents[B] != B){
                if(set.contains(B)) break;
                B = parents[B];
            }
            // 가장 가까운 조상 노드 반환
            sb.append(B).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int[] initParents(int n) {
        int[] parents = new int[n+1];
        for(int i = 1; i <= n; i++) parents[i] = i;
        return parents;
    }
}

