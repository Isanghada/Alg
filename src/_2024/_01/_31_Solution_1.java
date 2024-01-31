package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16965
// - 이분 탐색 : 각 정수가 나올 수 있는 하한을 구하여 계산
public class _31_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 문자열 길이
        int Q = Integer.parseInt(st.nextToken());   // 구간의 수

        String input = in.readLine();           // 문자열
        List<Integer> AB = new ArrayList<>();   // a, b 후보 정수 리스트
        List<Integer> CD = new ArrayList<>();   // c, d 후보 정수 리스트
        for(int idx = 0; idx < N; idx++){
            if(input.charAt(idx) == 'R') AB.add(idx);       // R인 경우 AB에 추가
            else if(input.charAt(idx) == 'B') CD.add(idx);  // B인 경우 CD에 추가
        }

        // Q 횟수만큼 반복
        while(Q-- > 0){
            // 구간 입력
            st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());   // 하한
            int r = Integer.parseInt(st.nextToken());   // 상한

            // 정답 리스트 초기화
            List<Integer> answerList = new ArrayList<>();
            
            // AB 추가
            answerList.addAll(binarySearch(l, r, AB));

            // AB가 있는 경우 CD 추가
            if(answerList.size() == 2)
                answerList.addAll(binarySearch(answerList.get(1), r, CD));

            // a, b, c, d가 있는 경우 출력
            if(answerList.size() == 4)
                for(int s : answerList) sb.append(s).append(" ");
            // 없는 경우 -1 출력
            else sb.append(-1);
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    // 이분 탐색 : l이상 r이하의 값 2개 반환
    private static List<Integer> binarySearch(int l, int r, List<Integer> list) {
        // 반환 리스트 초기화
        List<Integer> result = new ArrayList<>();
        
        int left = 0;                   // 왼쪽 인덱스
        int right = list.size() - 1;    // 오른쪽 인덱스
        while(left < right){
            // mid 계산
            int mid = (left + right) / 2;

            // 하한보다 작을 경우 left 변경
            if(list.get(mid) < l) left = mid + 1;
            // 이외의 경우 right 변경
            else right = mid;
        }
        
        // 인덱스 리미트 계산
        int limit = Math.min(left + 2, list.size());
        // 2개의 값 추가
        for(int idx = left; idx < limit; idx++){
            // l, r 구간인 경우만 추가
            if(list.get(idx) >= l && list.get(idx) <= r) result.add(list.get(idx));
         }

        return result;
    }
}