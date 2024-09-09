package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20040
// - 집합 : 유니온-파인드를 통해 순환이 생기는지 확인!
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 점의 개수
        int M = Integer.parseInt(st.nextToken());   // 선분 개수

        // 부모 배열 초기화
        int[] parents = initParents(N);

        // 선택 횟수!
        int answer = 1;
        while(M-- > 0){
            // 선분 정보
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 다른 집합인 경우 횟수 증가!
            if(union(parents, a, b)) answer++;
            // 같은 집합인 경우 : 연결할 선분의 개수 반환
            else {
                sb.append(answer);
                break;
            }
        }
        // 순환이 발생하지 않는 경우 0 반환
        if(sb.length() == 0) sb.append(0);

        // 정답 출력
        System.out.println(sb);
    }

    private static boolean union(int[] parents, int a, int b) {
        if(a > b){
            int temp = a;
            a = b;
            b = temp;
        }
        if(find(parents, a) == find(parents, b)) return false;
        else {
            parents[parents[b]] = parents[a];
            return true;
        }
    }

    private static int find(int[] parents, int target) {
        if(parents[target] == target) return target;
        return parents[target] = find(parents, parents[target]);

    }

    private static int[] initParents(int n) {
        int[] parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;
        return parents;
    }
}
