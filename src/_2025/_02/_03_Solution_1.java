package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17305
// - 누적합 : 작은 사탕, 큰 사탕 중 하나의 개수를 정하고 다른 사탕을 남은 무게만큼 추가!
public class _03_Solution_1 {
    // 사탕 무게!
    static final int SMALL = 3, BIG = 5;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사탕 개수
        int W = Integer.parseInt(st.nextToken());   // 무게 제한

        List<Integer> small = new ArrayList<>();    // 작은 사탕
        List<Integer> big = new ArrayList<>();      // 큰 사탕

        // 사탕 정보 입력
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            if(t == SMALL) small.add(s);
            else big.add(s);
        }

        // 내림차순 정렬로 사탕을 담을 때 당도가 높은 것부터 선택
        Collections.sort(small, Collections.reverseOrder());
        Collections.sort(big, Collections.reverseOrder());

        // 최대 당도
        long answer = 0;

        // 작은 사탕 인덱스
        int smallIdx = 0;
        // 큰 사탕 인덱스 : 가능한 최대 개수만큼!
        int bigIdx = Math.min(W / BIG, big.size());

        // 당도의 합
        long s = 0;
        // 무게의 합
        int w = bigIdx * BIG;
        for(int i = 0; i < bigIdx; i++) {
            s += big.get(i);
        }

        while(true){
            // 남은 무게만큼 작은 사탕 추가 선택
            while(smallIdx < small.size() && (w + SMALL) <= W){
                s += small.get(smallIdx++);
                w += SMALL;
            }

            // 정답 갱신 : 당도가 최대인 경우
            answer = Math.max(answer, s);
            
            // 큰 사탕을 모두 제거한 경우 종료
            if(bigIdx == 0) break;

            // 큰 사탕 한개 제거!
            bigIdx--;
            s -= big.get(bigIdx);
            w -= BIG;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
