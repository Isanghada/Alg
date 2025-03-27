package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/17255
// - 백트래킹 : 숫자와 방법을 String으로 표현하여 가능한 모든 방법을 Set에 추가!
//              Set으로 중복을 제거하고 방법의 수 계산
public class _28_Solution_1 {
    static char[] number;   // 목표 숫자
    static Set<String> set; // 가능한 방법 Set
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        number = in.readLine().toCharArray();   // 숫자 입력
        set = new HashSet<>();                  // set 초기화

        // 모든 위치를 기준으로 탐색!
        for(int start = 0; start < number.length; start++){
            backtracking(start, start, String.valueOf(number[start]), String.valueOf(number[start]));
        }

        // 정답 반환
        // - 방법의 수 반환
        sb.append(set.size());
        System.out.println(sb);
    }

    private static void backtracking(int left, int right, String num, String path) {
        // 숫자를 완성한 경우 set에 추가
        if(left == 0 && right == number.length-1){
            set.add(path);
        }else{
            // left가 남은 경우
            if(left > 0) {
                String next = number[left-1] + num;
                StringBuilder nextPath = new StringBuilder();
                nextPath.append(path).append(" ").append(next);
                backtracking(left-1, right, next, nextPath.toString());
            }

            // right가 남은 경우
            if(right < number.length - 1){
                String next = num + number[right+1];
                StringBuilder nextPath = new StringBuilder();
                nextPath.append(path).append(" ").append(next);
                backtracking(left, right+1, next, nextPath.toString());
            }
        }
    }
}
