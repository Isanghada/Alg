package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/3967
// - 브루트포스 : 백트래킹을 통해 모든 경우 탐색!
public class _19_Solution_1 {
    public static boolean[] USED_ALPHABET;  // 사용한 알파벳
    public static int[] ALPHABET;           // 인덱스별 알파벳
    public static int[] RESULT;             // 최종 결과!
    public static int[][] MAGIC_LINE={      // 매직 스타를 이루는 선 인덱스
            {1, 3, 6, 8},
            {2, 3, 4, 5},
            {2, 6, 9, 12},
            {8, 9, 10, 11},
            {5, 7, 10, 12},
            {1, 4, 7, 11}
    };
    public static final int MAX = 13;       // 최대값
    public static final int CHANGE = 'A'-1; // A를 1로 표현하기 위해 변환값은 A-1로 설정
    public static boolean FLAG = false;     // 정답 탐색 여부
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        initParam();            // 값 초기화
        inputMagicStar(in);     // 매직 스타 입력

        // 백트래킹 : 위에서부터 차례로 알파벳을 선택하며 확인
        // - 가장 위의 알파벳 인덱스를 1, 가장 아래 알파벳 인덱스를 12
        backtracking(1);

        // 정답 반환
        // - 탐색한 매직스타 반환
        sb.append(getMagicStart());
        System.out.println(sb);
    }

    private static String getMagicStart() {
        StringBuilder result = new StringBuilder();

        int index = 1;
        for(int line = 0; line < 5; line++){
            switch(line){
                case 0: case 4:
                    for(int i = 0; i < 9; i++){
                        if(i == 4) result.append((char)(RESULT[index++]+CHANGE));
                        else result.append(".");
                    }
                    break;
                case 1: case 3:
                    for(int i = 0; i < 9; i++){
                        if((i & 1) == 1) result.append((char)(RESULT[index++]+CHANGE));
                        else result.append(".");
                    }
                    break;
                case 2:
                    for(int i = 0; i < 9; i++){
                        if(i == 2 || i == 6) result.append((char)(RESULT[index++]+CHANGE));
                        else result.append(".");
                    }
                    break;
            }
            result.append("\n");
        }
        return result.toString();
    }

    private static void backtracking(int step) {
        if(FLAG) return;

        if(step == MAX){
            if(isPossible()){
                RESULT = Arrays.copyOf(ALPHABET, MAX);
                FLAG = true;
            }
            return;
        }
        if(ALPHABET[step] != 0) backtracking(step+1);
        else{
            for(int alp = 1; alp < MAX; alp++){
                if(USED_ALPHABET[alp]) continue;
                ALPHABET[step] = alp;
                USED_ALPHABET[alp] = true;
                backtracking(step+1);
                ALPHABET[step] = 0;
                USED_ALPHABET[alp] = false;
            }
        }
    }

    private static boolean isPossible() {
        for(int i = 0; i < MAGIC_LINE.length; i++){
            int sum = 0;
            for(int index : MAGIC_LINE[i]) sum += ALPHABET[index];
            if(sum != 26) return false;
        }
        return true;
    }

    private static void inputMagicStar(BufferedReader in) throws Exception{
        int index = 1;
        for(int r = 0; r < 5; r++){
            String input = in.readLine();
            for(int c = 0; c < input.length(); c++){
                char value = input.charAt(c);
                if(value == 'x') index++;
                else if(value != '.'){
                    ALPHABET[index] = value - CHANGE;
                    USED_ALPHABET[ALPHABET[index]] = true;
                    index++;
                }
            }
        }
    }

    private static void initParam() {
        USED_ALPHABET = new boolean[MAX];
        ALPHABET = new int[MAX];
        RESULT = new int[MAX];
    }
}
