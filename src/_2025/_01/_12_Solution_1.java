package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1338
// - 브루트포스 : 가능한 경우 모두 탐색! => 2개 이상인 경우 실패!
public class _12_Solution_1 {
    // 실패 메시지
    // - 정확한 값을 찾을 수 없는 경우
    static final String FAIL_MESSAGE = "Unknwon Number";
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long start = Integer.parseInt(st.nextToken());   // 시작 범위
        long end = Integer.parseInt(st.nextToken());     // 끝 범위
        // start가 더 큰 경우 스왑!
        if(start > end){
            long temp = start;
            start = end;
            end = temp;
        }

        st = new StringTokenizer(in.readLine());
        // 나누는 값 => Qx + y(0 <= y < |x|) 이므로
        //              Q는 음수도 가능하기 때문에 x를 양수로 변환
        long x = Integer.parseInt(st.nextToken());
        x = Math.abs(x);
        // 나머지 값
        long y = Integer.parseInt(st.nextToken());

        // 나머지 y인 경우가 2번 이상 나오거나 y가 x 범위를 벗어나는 경우 실패
        if(end - start >= 2*x || !(0 <= y && y < x)) sb.append(FAIL_MESSAGE);
        else{
            // 값을 저장할 덱
            Deque<Long> deque = new LinkedList<>();

            // 시작 숫자
            long number = start;
            // 음수일 때도 있기 때문에 이렇게 하는 거구나..
            long mod = ((start % x + x) % x);
            // - 이건 왜 실패하는 거지?
            // long mod = start % x;

            // mod에 따라 number 증가!
            if(mod < y) number += y - mod;
            else if(mod > y) number += x - mod + y;

            // 범위내에 나머지가 y인 경우 탐색
            for(; number <= end; number += x){
                deque.offerLast(number);
                // 2개 이상을 찾은 경우 종료
                if(deque.size() > 1) break;
            }
            // 1개의 값만 있는 경우 해당 값 반환
            // 아닐경우 실패 메시지 반환
            sb.append(deque.size() == 1 ? deque.pollLast() : FAIL_MESSAGE);
        }

        // 결과 반환
        System.out.println(sb);
    }
}
