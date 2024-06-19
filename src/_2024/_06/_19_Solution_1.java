package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18114
// - 브루트포스 : 2개를 선택하고 나머지를 이분 탐색으로 확인
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물건 개수
        int C = Integer.parseInt(st.nextToken());   // 무게

        // 물건 조합 여부
        boolean flag = false;


        // 물건 무게 입력
        st = new StringTokenizer(in.readLine());
        int[] weights = new int[N];
        for(int i = 0; i < N; i ++) {
            weights[i] = Integer.parseInt(st.nextToken());
            if(weights[i] == C) flag = true;
        }

        // 물건 무게 오름차순 정렬
        Arrays.sort(weights);

        int a = 0;
        int b = N-1;
        while((a < b) && !flag){
            for(int i = 0; i < N; i++){
                if(i == a || i == b) continue;

                if(weights[a] == C || weights[b] == C || weights[i] == C ||
                        (weights[a] + weights[b] == C) ||
                        (weights[a] + weights[b] + weights[i] == C)
                ){
                    flag = true;
                    break;
                }
            }
            if(weights[a] + weights[b] < C) a++;
            else b--;
        }

        // 정답 출력
        sb.append((flag ? 1 : 0));
        System.out.println(sb.toString());
    }
}
