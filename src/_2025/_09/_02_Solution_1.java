package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14369
// - 백트래킹 : 알파벳의 수를 체크하고 가능한 경우 탐색!
public class _02_Solution_1 {
    static final String[] NUMBERS = new String[]{
            "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"
    };
    static final int ALP = 'Z' - 'A' + 1;
    static int[] ANSWERS, ALPHABET;
    static boolean FLAG;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int t = 1; t <= T; t++){
            // 초기화
            ANSWERS = new int[10];
            ALPHABET = new int[ALP];
            FLAG = false;

            // 알파벳 수 체크
            for(char alp : in.readLine().toCharArray()) ALPHABET[alp-'A']++;
            // 백트래킹을 통해 정답 탐색
            backtracking(0);

            sb.append("Case #").append(t).append(": ").append(getTelephone()).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void backtracking(int num) {
        if(isEnd()){
            FLAG = true;
        }else{
            for(; num < 10; num++){
                // 사용 가능한 번호 인지 체크
                if(check(num)){
                    // 사용
                    useNumber(num);
                    backtracking(num);
                    // 모든 알파벳을 사용한 경우 종료
                    if(FLAG) break;
                    // 미사용
                    unuseNumber(num);
                }
            }
        }
    }

    private static void unuseNumber(int num) {
        for(char alp : NUMBERS[num].toCharArray()) ALPHABET[alp-'A']++;
        ANSWERS[num]--;
    }

    private static void useNumber(int num) {
        for(char alp : NUMBERS[num].toCharArray()) ALPHABET[alp-'A']--;
        ANSWERS[num]++;
    }

    private static boolean check(int num) {
        for(char alp : NUMBERS[num].toCharArray()){
            if(ALPHABET[alp-'A'] == 0) return false;
        }

        return true;
    }

    private static boolean isEnd() {
        for(int count : ALPHABET) if(count > 0) return false;

        return true;
    }

    private static String getTelephone() {
        StringBuilder telephone = new StringBuilder();
        for(int num = 0; num < 10; num++){
            while(ANSWERS[num] > 0) {
                telephone.append(num);
                ANSWERS[num]--;
            }
        }

        return telephone.toString();
    }
}
