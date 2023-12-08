package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/1612
// - 나머지 연산 법칙 : https://velog.io/@gidskql6671/%EB%82%98%EB%A8%B8%EC%A7%80Modulo-%EC%97%B0%EC%82%B0-%EB%B6%84%EB%B0%B0%EB%B2%95%EC%B9%99
// - 자릿수를 늘려가며 확인 -> 반복되는 나머지가 나오면 불가능한 경우
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 자연수
        int N = Integer.parseInt(in.readLine());

        // 자릿수 초기화
        int answer = 0;
        // 가능 여부 플래그
        boolean flag = false;
        // 값을 담을 변수
        int result = 0;
        // 나머지를 담을 Set
        Set<Integer> check = new HashSet<>();
        while(true){
            // 자릿수 증가
            answer++;
            // 이전 결과에 * 10을 한 후 1을 더한 값의 나머지 게산
            result = (result * 10 + 1) % N;
            // 0인 경우 가능한 경우
            if(result == 0){
                flag = true;
                break;
            }
            // 이미 나온 나머지인 경우 종료
            if(check.contains(result)) break;
            // 나머지 추가
            check.add(result);
        }

        // 정답 출력
        // - flag에 따라 answer, -1 출력
        sb.append(flag ? answer : -1);
        System.out.println(sb);
    }
}
