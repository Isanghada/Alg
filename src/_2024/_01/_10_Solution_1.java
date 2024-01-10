package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24391
// - 유니온-파인드 : 각 그룹을 구하여 강의마다 체크!
public class _10_Solution_1 {
    // N : 강의 개수
    // M : 건물의 쌍 개수
    public static int N, M;
    // PARENTS : 부모 배열(그룹 배열)
    // SUBJECTS : 강의 순서
    public static int[] PARENTS, SUBJECTS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 강의 개수
        int M = Integer.parseInt(st.nextToken());   // 건물 쌍 개수(연결된 건물 개수)

        // 부모 배열 초기화 : 자기 자신으로 초기화
        PARENTS = new int[N+1];
        for(int i = 1; i <= N; i++) PARENTS[i] = i;

        // 건물 연결!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 건물 a
            int b = Integer.parseInt(st.nextToken());   // 건물 b
            // a, b 연결
            union(a, b);
        }

        // 강의 순서 입력
        SUBJECTS = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        int answer = 0;
        // 초기값 설정 : 첫 강의는 이동하지 않으므로 첫 강의 건물로 설정
        int past = SUBJECTS[0];
        // 이후의 강의에 따른 건물 이동 횟수 계산
        for(int idx = 1; idx < N; idx++){
            // 이전 강의 건물 그룹
            int pastParent = find(past);
            // 현재 강의 건물 그룹
            int curParent = find(SUBJECTS[idx]);

            // 강의 그룹이 다른 경우 정답 증가
            if(pastParent != curParent) answer++;
            // past 업데이트
            past = SUBJECTS[idx];
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // 유니온 함수 : a, b 연결! => 부모 배열 설정
    private static void union(int a, int b) {
        int aParent = find(a);  // a의 부모
        int bParent = find(b);  // b의 부모

        // a, b의 부모 연결!
        if(aParent < bParent) PARENTS[bParent] = aParent;
        else PARENTS[aParent] = bParent;
    }
    // 파인드 함수 : a의 부모를 찾는 함수. 재귀를 통해 탐색
    private static int find(int a){
        int parent = PARENTS[a];
        // 자기 자신인 경우 반환
        if(parent == a) return parent;
        // 재귀를 통해 루트 반환
        return PARENTS[a] = find(parent);
    }
}
