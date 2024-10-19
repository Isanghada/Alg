package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 그리디 : A의 개수에 따라 만들 수 있는 쌍의 최대값이 K개 이상인 경우에서
//            마지막 A의 위치를 조절하여 값 생성!
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 문자열 길이
        int K = Integer.parseInt(st.nextToken());   // 쌍의 개수

        // 가능 여부 플래그
        boolean flag = true;
        int countOfA = 1;   // A의 수
        int countOfB = 0;   // B의 수
        for(; countOfA <= N; countOfA++){
            countOfB = N - countOfA;

            // 가능한 최대 쌍의 수!
            if(countOfA * countOfB >= K) break;
            // 불가능한 경우 flag 갱신!
            if(countOfA == N) flag = false;
        }

        // 가능한 경우
        if(flag){
            // B로 초기화
            char[] answer = new char[N];
            Arrays.fill(answer, 'B');

            // (A의 수 - 1)개 A로 변환
            for(int i = 0; i < countOfA-1; i++) answer[i] = 'A';

            // 현재 쌍의 수!
            int count = (countOfA - 1) * countOfB;
            // 남은 쌍의 수!
            int move = K - count;

            System.out.println(countOfA+", "+count+", "+move);
            System.out.println(answer);
            // 마지막 A 변경!
            answer[N-1-move] = 'A';
            
            sb.append(String.valueOf(answer));
        // 불가능한 경우 -1 반환
        }else sb.append(-1);

        // 정답 출력
        System.out.println(sb.toString());
    }
}
