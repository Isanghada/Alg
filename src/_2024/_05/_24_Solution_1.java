package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1548
// - 그리디 : 시작 지점과 끝 지점을 선택하고 가능한지 체크!
//            값 3개 중 작은값 2개의 합이 가장 큰 값 초과일 때가 가능한 경우
// - 참고 : https://kau-algorithm.tistory.com/970
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());

        // 수열 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));

        // 오름차순 정렬
        Collections.sort(list);

        // 정답 초기화
        int answer = 2;
        // N이 2 초과인 경우 모든 경우 체크
        if(N > 2){
            // 시작 지점 선택
            for(int start = 0; start < N-2; start++){
                // 작은 값의 합 계산
                int sum = list.get(start) + list.get(start+1);
                // 끝 지점 선택 : 작은 값의 합이 end의 값 초과일 경우 길이 계산 후 갱신!
                for(int end = start+2; end < N; end++){
                    if(sum > list.get(end)) answer = Math.max(answer, end - start + 1);
                }
            }
        // N이 2 이하인 경우 수열 길이 반환
        }else answer = N;

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
