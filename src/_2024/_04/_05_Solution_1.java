package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/10597
// - 백트래킹 : 모든 경우의 수 확인
public class _05_Solution_1 {
    public static final int MAX = 50;
    public static int len;              // 순열 길이
    public static String input, answer; // 순열, 정답
    public static boolean[] used;       // 사용 여부
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        input = in.readLine();      // 순열 입력
        len = input.length();       // 순열 길이
        used = new boolean[MAX+1];  // 사용한 수

        // 처음부터 차례로 탐색
        backtracking(0, 0, "");

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static void backtracking(int idx, int n, String cur) {
        // 정답이 갱신된 경우 종료
        if(answer != null) return;
        // 모든 순열을 확인한 경우
        if(idx == len){
            // 모든 숫자가 사용되지 않은 경우 : 종료
            for(int i = 1; i <= n; i++) if(!used[i]) return;
            // 모든 숫자가 사용되었면 정답 갱신
            answer = cur;
            return;
        }
        // 현재 숫자 체크
        String tmp = ""+input.charAt(idx);
        int num = Integer.parseInt(tmp);
        // 사용되지 않은 경우
        // - 사용 체크 후 다음 숫자 탐색
        if(!used[num]){
            used[num] = true;
            backtracking(idx+1, (num > n) ? num : n, cur + " "+ tmp);
            used[num] = false;
        }
        // 마지막 인덱스가 아닌 경우
        if(idx < len-1){
            // 2개의 인덱스로 값 확인
            tmp += input.charAt(idx+1);
            num = Integer.parseInt(tmp);
            // 51 미만이고 사용되지 않은 경우
            // - 사용 체크 후 다음 숫자 탐색
            if(num < 51 && !used[num]){
                used[num] = true;
                backtracking(idx+2, (num > n) ? num : n, cur + " "+ tmp);
                used[num] = false;
            }
        }
    }
}
