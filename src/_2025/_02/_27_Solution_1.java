package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15811
// - 백트래킹 : 1. 사용하는 알파벳 모두 체크!
//              2. 각 알파벳에 가능한 모든 숫자를 매칭
//              3. 매칭시킨 결과로 단어를 숫자로 바꿔 계산하여 확인
public class _27_Solution_1 {
    static boolean[] USED;                  // 숫자 사용 여부
    static int[] ALP_ARR; // (알파벳, 숫자) 매칭
    static List<Character> ALP_LIST;        // 사용한 알파벳 종류
    static String A, B, RESULT;             // 입력한 단어
    static final int MAX = 10;              // 숫자 최대값!
    static final int ALP = 65;              // A의 아스키 코드
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 단어 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = st.nextToken();
        B = st.nextToken();
        RESULT = st.nextToken();

        // 사용한 알파벳 체크 : 중복 제거
        Set<Character> wordSet = new HashSet<>();
        wordSet.addAll(change(A));
        wordSet.addAll(change(B));
        wordSet.addAll(change(RESULT));

        // 각 값 초기화
        ALP_LIST = new ArrayList<>(wordSet);
        USED = new boolean[MAX];
        ALP_ARR = new int['Z' - 'A' + 1];
        Arrays.fill(ALP_ARR, -1);

        // 사용한 알파벳이 10개 초과인 경우 NO 반환
        // - 각 알파벳은 다른 숫자를 의미하므로 최대 10개까지 표현 가능
        if(ALP_LIST.size() > MAX) sb.append("NO");
        // 백트래킹을 통해 가능한 모든 경우 탐색!
        else sb.append(backtracking(0) ? "YES" : "NO");

        // 정답 반환
        System.out.println(sb);
    }

    /**
     * ALP_LIST의 각 알파벳에 차례로 숫자 매칭<br>
     * 모든 숫자를 매칭한 경우 단어를 숫자로 변환해 계산 확인
     * @param step ALP_LIST 인덱스
     * @return boolean : 계산식 해답 존재 여부
     */
    private static boolean backtracking(int step) {
        if(step == ALP_LIST.size()){
            long a = alpToNum(A);
            long b = alpToNum(B);
            long result = alpToNum(RESULT);

            if(a+b == result) return true;
        }else{
            for(int num = 0; num < MAX; num++){
                if(USED[num]) continue;

                USED[num] = true;
                ALP_ARR[ALP_LIST.get(step)-ALP] = num;
                if(backtracking(step+1)) return true;
                USED[num] = false;
                ALP_ARR[ALP_LIST.get(step)-ALP] = -1;
            }
        }
        return false;
    }

    private static long alpToNum(String target) {
        long result = 0;

        for(int i = 0; i < target.length(); i++){
            result *= 10;
            result += ALP_ARR[target.charAt(i)-ALP];
        }

        return result;
    }

    private static List<Character> change(String target) {
        List<Character> alpList = new ArrayList<>();

        for(int i = 0; i < target.length(); i++) alpList.add(target.charAt(i));

        return alpList;
    }
}
