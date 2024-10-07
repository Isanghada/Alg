package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12924
// - 브루트포스 : A, B의 자릿수가 같으므로 [A, B] 구간의 모든 수를 대상으로 멋진 쌍 탐색!
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());   // 시작 범위
        int B = Integer.parseInt(st.nextToken());   // 끝 범위

        // 멋진 쌍 개수
        int answer = 0;
        // A부터 B미만까지 모두 탐색
        for(int num = A; num < B; num++){
            // num을 기준으로 만들 수 있는 멋진 쌍
            Set<Integer> possibleSet = new HashSet<>();

            // num을 기준으로 변경할 수 있는 모든 경우 탐색!
            for(int change = 10; change < num; change *= 10){
                // 숫자 변경!
                StringBuilder numToString = new StringBuilder();
                numToString.append(num % change).append(num / change);
                int changeNum  = Integer.parseInt(numToString.toString());

                // 변경한 값이 num(처음 값)보다 크고 B 이하인 경우 set에 추가!
                if(changeNum > num && changeNum <= B) {
                    // System.out.println(num+", "+ numToString +", "+changeNum);
                    possibleSet.add(changeNum);
                }
            }
            // 가능한 경우만큼 증가!
            answer += possibleSet.size();
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
