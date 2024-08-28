package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/9935
// - 스택 : 문자열을 하나씩 스택에 추가하며 폭발 문자열이 되는지 확인!
//          폭발 문자열이 된다면 해당 범위는 스택에서 제거 후 반복!
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String origin = in.readLine();  // 초기 문자열
        String target = in.readLine();  // 폭발 문자열

        // 폭발 문자열 길이가 더 긴 경우 폭발할 수 없으므로 origin 반환
        if(origin.length() < target.length()) sb.append(origin);
        else{
            // 문자열을 체크할 덱! : 스택으로 활용
            Deque<Character> check = new LinkedList<>();
            // 폭발 문자열의 마지막 문자
            char last = target.charAt(target.length()-1);
            // origin의 문자를 차례로 덱에 추가하며 확인!
            for(int i = 0; i < origin.length(); i++){
                // 덱에 문자 추가!
                check.offerLast(origin.charAt(i));
                // 폭발 문자열 길이 이상이고, 마지막 문자가 폭발 문자열의 마지막 문자와 같은 경우!
                // - 폭발 문자열이 되는지 확인하고, 폭발 문자열이라면 제거!
                if(check.size() >= target.length() &&
                        check.peekLast() == last){
                    // 폭발 문자열 확인용 덱
                    Deque<Character> temp = new LinkedList<>();
                    // 폭발 문자열의 마지막 문자부터 차례로 검사!
                    for(int j = target.length()-1; j >= 0; j--){
                        // 폭발 문자열의 문자와 동일한 경우 check 에서 삭제 후 temp에 추가!
                        if(check.peekLast() == target.charAt(j)){
                            temp.offerLast(check.pollLast());
                        // 폭발 문자열의 문자와 동일하지 않은 경우 : temp이 문자를 check에 다시 추가!
                        }else{
                            while(!temp.isEmpty()){
                                check.offerLast(temp.pollLast());
                            }
                            break;
                        }
                    }
                }
            }
            // check에 남은 문자 반환!
            while(!check.isEmpty()) sb.append(check.pollFirst());
            // 모두 폭발한 경우 FRULA 반환!
            if(sb.length() == 0) sb.append("FRULA");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
