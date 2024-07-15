package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1717
// - 유니온-파인드 : 같은 집합인지 확인하기 위해 유니온-파인드 활용
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 초기 집합
        int M = Integer.parseInt(st.nextToken());   // 연산의 수

        // 부모 초기화
        int[] parents = initParent(N);
        // 연산 진행
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());    // 연산 타입
            int a = Integer.parseInt(st.nextToken());       // 원소 a
            int b = Integer.parseInt(st.nextToken());       // 원소 b
            // 연산 0인 경우 : 합집합 진행
            if(type == 0) union(parents, a, b);
            // 연산 1인 경우 : a, b가 같은 집합인지 확인하여 결과 출력
            else sb.append(isSameSet(parents, a, b)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
    // 동일 집합 확인 함수
    private static String isSameSet(int[] parents, int a, int b) {
        // 부모가 같은 경우 같은 집합이므로 YES 출력
        if(getParent(parents, a) == getParent(parents, b)) return "YES";
        // 아닐 경우 NO 출력
        else return "NO";
    }

    // 합집합 함수 : a, b의 parents를 동일하게 설정!
    private static void union(int[] parents, int a, int b) {
        int parentA = getParent(parents, a);    // a의 부모
        int parentB = getParent(parents, b);    // b의 부모

        // 동일하지 않은 경우 : 작은 수에 포함되도록 설정
        if(parentA != parentB){
            if(parentA > parentB){
                int temp = parentA;
                parentA = parentB;
                parentB = temp;
            }
            parents[parentB] = parentA;
        }
    }

    // 부모 탐색 함수 : 루트를 확인하는 함수
    private static int getParent(int[] parents, int a) {
        // 동일한 경우 루트이므로 a 반환
        if(parents[a] == a) return a;
        // 동일하지 않은 경우 재귀를 통해 탐색
        else return parents[a] = getParent(parents, parents[a]);
    }

    // 부모 초기화 함수 : 0~n 범위로 초기화
    private static int[] initParent(int n) {
        int[] parents = new int[n+1];
        for(int i = 0; i <= n; i++) parents[i] = i;
        return parents;
    }
}
