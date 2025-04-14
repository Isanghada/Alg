package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17250
// - 유니온-파인드 : 유니온-파인드를 활용하여 연결된 각 집합을 분리하여 계산
//                      1. 두 은하의 연결 여부 확인
//                      2. 연결되지 않았다면 집합 통합!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 은하의 수
        int M = Integer.parseInt(st.nextToken());   // 철도의 수

        int[] groups = new int[N+1];    // 은하 집합
        int[] counts = new int[N+1];    // 집합별 행성 개수
        
        // 행성 개수 입력
        for(int i = 1; i <= N; i++){
            groups[i] = i;
            counts[i] = Integer.parseInt(in.readLine());
        }

        while(M-- > 0){
            // 철도 정보 : (a, b) 은하 연결
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a가 더 작은 번호가 되도록 설정
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }

            // 은하 연결 여부 체크
            // - 연결되지 않은 경우 진행
            if(check(groups, a, b)) {
                // a의 집합을 b의 집합만큼 증가 
                counts[find(groups, a)] += counts[find(groups, b)];
                // a, b 철도 연결
                union(groups, a, b);
            }
            // (a, b) 철도 연결로 이용할 수 있는 행성의 수 출력
            sb.append(counts[find(groups, a)]).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }
    static boolean check(int[] groups, int a, int b){
        int groupA = find(groups, a);
        int groupB = find(groups, b);

        if(groupA == groupB) return false;
        return true;
    }
    static void union(int[] groups, int a, int b){
        int groupA = find(groups, a);
        int groupB = find(groups, b);

        groups[groupB] = groupA;
    }

    private static int find(int[] groups, int target) {
        if(groups[target] == target) return target;
        else return groups[target] = find(groups, groups[target]);
    }
}

