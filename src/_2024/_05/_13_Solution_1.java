package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.acmicpc.net/problem/9519
// - 구현 : 동작을 역으로 진행하여 원본 데이터 복원!
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 깜박임 횟수
        int X = Integer.parseInt(in.readLine());

        // 변경된 데이터
        String after = in.readLine();
        // 데이터 길이
        int len = after.length();

        // 변경전 앞 단어
        StringBuilder head = null;
        // 변경전 뒷 단어
        StringBuilder tail = null;

        // 변경된 단어 모음
        Set<String> set = new HashSet<>();
        List<String> list=  new ArrayList<>();

        list.add(after);
        int cnt = 0;

        String tempWord = after;
        int temp = X;
        while(temp > 0){
            head = new StringBuilder();
            tail = new StringBuilder();

            // 짝수인 경우, 홀수인 경우 체크!
            if((len & 1) == 0){
                for(int i = len-1; i >= 0; i -= 2) tail.append(tempWord.charAt(i));
                for(int i = 0; i < len; i += 2) head.append(tempWord.charAt(i));
            }else{
                for(int i = len-2; i >= 0; i -= 2) tail.append(tempWord.charAt(i));
                for(int i = 0; i < len; i += 2) head.append(tempWord.charAt(i));
            }

            // 변경전 데이터 생성
            head.append(tail);
            tempWord = head.toString();

            list.add(tempWord);
            // 이미 포함된 경우 종료
            if(set.contains(tempWord)) break;

            // 데이터 추가!
            set.add(tempWord);
            cnt++;
            temp--;
        }

        // 남은 횟수가 있는 경우
        if(temp > 0){
            // 반복 횟수를 통해 계산
            temp = X % cnt;
            sb.append(list.get(temp));
        // 남은 횟수가 없는 경우 : tempWord 반환
        }else sb.append(tempWord);

        // 정답 반환
        System.out.println(sb);
    }
}
