package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7511
// - 유니온 파인드 : 연결 경로 여부는 같은 그룹에 속하는지 여부에 따라 나뉜다.
//                   따라서, 유니온-파인드 알고리즘을 통해 parents가 같으면 같은 그룹에 속하여
//                   연결하는 경로가 있음을 의미한다.
public class _05_Solution_1 {
    public static int[] parents;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 테스트케이스 수
        int T = Integer.parseInt(in.readLine());

        parents = new int[1000000];
        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(in.readLine());    // 유저의 수
            // 유저 정보 초기화 : parent를 자기 자신으로 설정
            for(int num = 0; num < N; num++) parents[num] = num;

            int K = Integer.parseInt(in.readLine());    // 친구 관계의 수
            // 친구 관계 입력
            while(K-- > 0){
                // 친구 관계
                st = new StringTokenizer(in.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                // 두 유저 연결
                union(from, to);
            }

            // 출력 설정
            sb.append("Scenario ").append(t).append(":\n");

            // 미리 구할 쌍의 수
            int M = Integer.parseInt(in.readLine());
            while(M-- > 0){
                // 친구 관계
                st = new StringTokenizer(in.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                // 두 유저의 find 함수 값이 같다면 같은 그룹 : 1 (연결 가능한 경로 존재)
                // 두 유저의 find 함수 값이 다르다면 다른 그룹 : 0 (연결 가능한 경로 미존재)
                sb.append(find(from) == find(to) ? "1\n": "0\n");
            }
            sb.append("\n");
        }
        // 정답 출력
        System.out.println(sb);
    }
    // 유니온 함수 : a, b 노드 연결
    private static void union(int a, int b) {
        int aParent = find(a);  // a의 parent
        int bParent = find(b);  // b의 parent

        // 더 작은 parent에 속하도록 설정
        if(aParent < bParent) parents[bParent] = aParent;
        else parents[aParent] = bParent;
    }

    // find 함수 : a의 parent 탐색
    private static int find(int a) {
        // parents가 자기자신이 아닌 경우 재귀를 통해 parent 계산
        if (a == parents[a]) return a;
        else return parents[a] = find(parents[a]);
    }
}
