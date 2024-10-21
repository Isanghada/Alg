package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/2036
// - 그리디 : 음수, 양수, 0으로 나누어 값 입력받고 정렬 후 양수끼리, 음수끼리 두 정수 선택!
//            남은 양수와 음수를 차례로 더해주고, 0이 있는 경우 음수는 0으로 치환!
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정수의 수
        int N = Integer.parseInt(in.readLine());

        // 정답
        long answer = 0;

        // 0의 수
        int zeroCount = 0;
        // 음수
        List<Long> minus = new ArrayList<>();
        // 양수
        List<Long> plus = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            long num = Long.parseLong(in.readLine());
            if(num < 0) minus.add(num);
            else if(num == 1) answer++;
            else if(num == 0) zeroCount++;
            else plus.add(num);
        }
        // 음수는 내림차순 정렬
        Collections.sort(minus, Collections.reverseOrder());
        // 양수는 오름차순 정렬
        Collections.sort(plus);

        // 양수 점수!
        for(int i = plus.size()-1; i > 0; i -= 2) answer += plus.get(i) * plus.get(i-1);
        if((plus.size() & 1) == 1) answer += plus.get(0);

        // 음수 점수!
        for(int i = minus.size()-1; i > 0; i -= 2) answer += minus.get(i) * minus.get(i-1);
        if((minus.size() & 1) == 1) {
            if(zeroCount == 0) answer += minus.get(0);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}