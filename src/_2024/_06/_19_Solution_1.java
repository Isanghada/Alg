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

        // 물건 무게 입력
        st = new StringTokenizer(in.readLine());
        int[] weights = new int[N];
        for(int i = 0; i < N; i ++) weights[i] = Integer.parseInt(st.nextToken());

        // 물건 무게 오름차순 정렬
        Arrays.sort(weights);

        // 물건 조합 여부
        boolean flag = false;
        for(int a = 0; a < N; a++){
            // 1개의 물건이 C와 동일한 경우 플래그 변경 후 종료
            if(weights[a] == C){
                flag = true;
                break;
            }
            for(int b = a+1; b < N; b++){
                // 2개의 물건 무게 합
                int w = weights[a] + weights[b];
                // 무게가 C와 동일한 경우 플래그 변경 후 종료
                if(w == C){
                    flag = true;
                    break;
                }
                // 나머지 무게가 존재하는지 이분 탐색을 통해 확인
                if(isPossible(weights, b+1, N-1, C - w)) flag = true;
            }
        }

        // 정답 출력
        sb.append((flag ? 1 : 0));
        System.out.println(sb.toString());
    }

    private static boolean isPossible(int[] weights, int left, int right, int target) {
        while(left <= right){
            int mid = (left + right) / 2;
            if(weights[mid] == target) return true;
            else if(weights[mid] > right) right = mid-1;
            else left = mid+1;
        }
        return false;
    }
}
