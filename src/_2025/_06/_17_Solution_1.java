package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23742
// - 그리디 : 1개 이상의 팀을 구성할 수 있으므로,
//              1. 기본적으로 양수 팀과 음수인 개별팀으로 구분!
//              2. 큰 음수부터 차례로 양수 팀에 추가하며 최대값 탐색
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Integer> minusList = new ArrayList<>();

        long answer = 0L;
        long plusSum = 0L, minusSum = 0L;
        int plusCount = 0;
        while(st.hasMoreTokens()){
            int value = Integer.parseInt(st.nextToken());
            if(value < 0) {
                minusList.add(value);
                minusSum += value;
            }
            else{
                plusSum += value;
                plusCount++;
            }
        }

        answer = plusSum * plusCount + minusSum;

        Collections.sort(minusList, Collections.reverseOrder());

        for(int minus : minusList){
            plusCount++;
            plusSum += minus;
            minusSum -= minus;
            answer = Math.max(answer, plusCount * plusSum + minusSum);
        }


        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
