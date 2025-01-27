package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12893
// - 이분 그래프 : 매번 번갈아 그룹을 지정하며, 어긋나는 경우가 있는지 확인
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 적대 관계의 수

        // 적대 관계 Set
        Set<Integer>[] adjSet = new Set[N+1];
        for(int n = 1; n <= N; n++) adjSet[n] = new HashSet<>();

        // 적대 관계 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjSet[a].add(b);
            adjSet[b].add(a);
        }

        // 적대 관계 그룹 초기화
        int[] groups = new int[N+1];
        Arrays.fill(groups, -1);

        boolean flag = true;
        // 모든 경우의 적대 관계 확인
        for(int n = 1; n <= N; n++){
            // 이미 체크된 경우 패스
            if(groups[n] != -1) continue;
            // 적대 관계가 이루어질 수 없는 경우 flag 변경 후 종료
            if(!bitparticleGraph(groups, adjSet, n, 0)){
                flag = false;
                break;
            }
        }

        // 정답 반환
        // - flag에 따라 가능 여부 반환
        sb.append(flag ? 1 : 0);
        System.out.println(sb);
    }

    private static boolean bitparticleGraph(int[] groups, Set<Integer>[] adjSet, int node, int group) {
        if(groups[node] != -1) return groups[node] == group;

        groups[node] = group;
        for(int next : adjSet[node]){
            if(!bitparticleGraph(groups, adjSet, next, 1 - group)) return false;
        }

        return true;
    }
}
