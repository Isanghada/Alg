package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20414
// -
public class _03_Solution_1 {
    static final int GRADE_TYPE = 5;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] gradeAmounts = new int[GRADE_TYPE];
        for(int i = 0; i < (GRADE_TYPE-1); i++) gradeAmounts[i] = Integer.parseInt(st.nextToken());
        gradeAmounts[GRADE_TYPE-1] = gradeAmounts[GRADE_TYPE-2]+1;

        Map<Character, Integer> gradeToIdx = getGradeToIdx("BSGPD");

        String grade = in.readLine();

        int[] goals = new int[N];
        for(int n = 0; n < N; n++) goals[n] = gradeAmounts[gradeToIdx.get(grade.charAt(n))]-1;
        //System.out.println(Arrays.toString(goals));

        int prev = goals[0], answer = goals[0];
        for(int n = 1; n < N; n++){
            boolean flag = goals[n] < prev;
            prev = (grade.charAt(n) == 'D') ? goals[n] : goals[n] - prev;
            answer += prev;

            if(flag) prev = 0;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static Map<Character, Integer> getGradeToIdx(String target) {
        int length = target.length();
        Map<Character, Integer> result = new HashMap<>();
        for(int i = 0; i < length; i++) result.put(target.charAt(i), i);
        return result;
    }
}
