package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3363
// - 브루트포스 : 모든 동전을 검사하여 모조품 확인
// - NoSuchElementException 오류 발생..어디지..?
public class _17_Solution_1 {
    // 동전의 수
    public static final int MAX_NUMBER = 12;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 모조품일 수 있는 동전
        Set<Integer> numberSet = new HashSet<>();
        for(int num = 1; num <= MAX_NUMBER; num++) numberSet.add(num);

        // 무거운 동전 모음
        List<Set<Integer>> winList = new ArrayList<>();
        // 가벼운 동전 모음
        List<Set<Integer>> loseList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            String input = in.readLine();
            char type;       // 저울 결과

            if(input.contains("=")) type = '=';
            else if(input.contains(">")) type = '>';
            else type = '<';

            input = input.replace(type, ' ');
            st = new StringTokenizer(input);
            Set<Integer> A = getNumbers(st, 4); // 동전 모음1
            Set<Integer> B = getNumbers(st, 4); // 동전 모음2

//            System.out.println(A);
//            System.out.println(B);

            // A가 무거운 경우
            if(type == '>'){
                winList.add(A);     // A를 winList에 추가
                loseList.add(B);    // B를 loseList에 추가
            // 비기는 경우 : 각 동전을 numberSet에서 삭제
            }else if(type == '='){
                for(int num : A) numberSet.remove(num);
                for(int num : B) numberSet.remove(num);
            // B가 무거운 경우
            }else{
                winList.add(B);     // B를 winList에 추가
                loseList.add(A);    // A를 loseList에 추가
            }
        }

        // 정답일 수 있는 동전 리스트
        List<String> answerList = new ArrayList<>();
        // numberSet에 남은 동전 모두 검사
        for(int num : numberSet){
            boolean winFlag = false;    // 무거운 경우
            boolean loseFlag = false;   // 가벼운 경우
            // 비교 결과를 참고하여 결과 탐색
            for(int i = 0; i < winList.size(); i++){
                // 무거운 경우에 속하면서 가벼운 경우가 없는 경우 : winFlag를 true로 변경
                if(winList.get(i).contains(num) && !loseFlag) winFlag = true;
                // 가벼운 경우에 속하면서 무거운 경우가 없는 경우 : loseFlag를 true로 변경
                else if(loseList.get(i).contains(num) && !winFlag) loseFlag = true;
                // 양쪽에 모두 속하는 경우 : 불가능한 경우이므로 모든 flag를 false로 변경
                else{
                    winFlag = false;
                    loseFlag = false;
                    break;
                }
            }
            // winFlag, loseFlag 중 하나만 true인 경우 정답 후보 리스트에 추가
            if(winFlag && !loseFlag) answerList.add(num+"+");
            else if(!winFlag && loseFlag) answerList.add(num+"-");
        }

//        System.out.println(answerList);

        // 정답 출력
        // - 정답 후보가 없는 경우 : 불가능한 경우(impossible)
        if(answerList.size() == 0) sb.append("impossible");
        // - 정답 후보가 1개인 경우 : 정답 후보가 정답이므로 해당 값 출력
        else if(answerList.size() == 1) sb.append(answerList.get(0));
        // 정답 후보가 2개 이상인 경우 : 정보가 불충분(indefinite)
        else sb.append("indefinite");
        System.out.println(sb);
    }

    private static Set<Integer> getNumbers(StringTokenizer st, int size) {
        Set<Integer> result = new HashSet<>();
        while(size-- > 0) result.add(Integer.parseInt(st.nextToken()));
        return result;
    }
}
