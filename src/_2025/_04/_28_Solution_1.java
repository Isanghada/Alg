package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/13019
// -  그리디 : 자리에 맞지 않는 경우를 체크하여 최소 변경 횟수 계산
public class _28_Solution_1 {
    static final int ALPHABET = 26;     // 알파벳 개수
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] A = in.readLine().toCharArray(); // 문자열 원본
        char[] B = in.readLine().toCharArray(); // 문자열 타겟

        // 각 문자열 알파벳 개수
        int[] countA = getAlpCount(A);
        int[] countB = getAlpCount(B);

//        System.out.println(Arrays.toString(countA));
//        System.out.println(Arrays.toString(countB));
//        System.out.println(check(countA, countB));
        // 알파벳 개수가 동일하지 않으면 불가능하므로 -1 반환
        if(!check(countA, countB)) sb.append(-1);
        else {
            // 정답 초기화 : 최소 변경 횟수
            int answer = 0;
            
            // 각 문자열의 마지막 인덱스부터 비교
            int aIdx = A.length - 1;
            int bIdx = B.length - 1;

            while(aIdx >= 0){
                // 변경하는 않는 경우 => 두 알파벳이 같은 경우
                if(A[aIdx] == B[bIdx]) bIdx--;
                // 다른 알파벳이므로 변경 횟수 증가
                else answer++;

                aIdx--;
            }
            // 최소 변경 횟수 출력
            sb.append(answer);
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static boolean check(int[] countA, int[] countB) {
        for(int i = 0; i < ALPHABET; i++){
            if(countA[i] != countB[i]) return false;
        }
        return true;
    }

    private static int[] getAlpCount(char[] target) {
        int[] count = new int[ALPHABET];
        for(char alp : target) count[alp - 'A']++;
        return count;
    }
}
