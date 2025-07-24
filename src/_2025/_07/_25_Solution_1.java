package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/26650
// -
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 입력된 문자열
        char[] input = in.readLine().toCharArray();

        // past : 이전 문자 (중복 가능!)
        // target: 현재 찾아야하는 문자
        char past = 'A', target = 'A';

        // 모든 알파벳이 순서대로 나열된 팬그램을 찾는 것이므로
        // 중간의 알파벳 수와 무관하게 A, Z의 수로 팬그램의 개수가 결정
        // 따라서, 올바른 팬그램인 경우 A와 Z의 개수의 곱만큼 정답이 증가
        long countOfA = 0;   // A의 수
        long countOfZ = 0;   // Z의 수
        
        // 정답 초기화
        long answer = 0;
        for(char alp : input){
            // System.out.println(alp+", "+past+", "+target+", "+countOfA+", "+countOfZ);
            // 이전 문자와 동일한 경우
            if(past == alp){
                // A 혹은 Z인 경우 개수 증가
                if(alp == 'A') countOfA++;
                else if(alp == 'Z') countOfZ++;
            }

            // 올바른 팬그램이 아닌 경우
            if(past != alp && target != alp){
                answer += countOfA * countOfZ;
                past = 'A';
                target ='A';
                countOfA = 0;
                countOfZ = 0;
            }

            // 올바른 팬그램 문자인 경우
            if(target == alp){
                if(target == 'A') countOfA = 1;
                else if(target == 'Z') countOfZ = 1;
                past = target;
                target = (char)(past + 1);
            }
        }

        answer += countOfA * countOfZ;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}