package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2661
// - 백트래킹 : DFS를 응용하여 1, 2, 3 순으로 자리수를 늘리며 좋은 수열인지 확인!
public class _28_Solution_1 {
    // 정답
    static String ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());
        // 백트래킹 : N 길이까지 탐색!
        backtracking(0, N, "");

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }

    // 백트래킹 함수 : DFS를 응용하여 1, 2, 3순으로 차례로 탐색!
    // - size : 현재 길이
    // - n : 목표 길이
    // - target : 현재 수열
    private static boolean backtracking(int size, int n, String target) {
        // 좋은 수열이 아닌 경우 탐색 종료
        if(!goodSequence(target)) return false;

        // 목표 길이인 경우 ANSWER 갱신 후 true 반환
        if(size == n){
            ANSWER = target;
            return true;
        }

        // 1, 2, 3 순으로 수열에 추가!
        for(int num = 1; num < 4; num++){
            // 좋은 수열을 발견한 경우 true 반환
            if(backtracking(size+1, n, target + num)) return true;
        }

        // 좋은 수열이 불가능한 경우 false 반환
        return false;
    }

    private static boolean goodSequence(String target) {
        for(int subSize = target.length() / 2; subSize > 0; subSize--){
            boolean flag = true;
            for(int index = target.length() - subSize; index < target.length(); index++){
                if(target.charAt(index-subSize) != target.charAt(index)){
                    flag = false;
                    break;
                }
            }
            if(flag) return false;
        }


        return true;
    }
}
