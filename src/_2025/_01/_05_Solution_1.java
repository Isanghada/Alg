package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/30805
// - 그리디 : Set을 활용해 공통 숫자 중 최대값을 탐색하여 공통 부분 수열 생성
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;

        // A의 길이
        int N = Integer.parseInt(in.readLine());
        // A 정보 입력
        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        // B의 길이
        int M = Integer.parseInt(in.readLine());
        // B 정보 입력
        st = new StringTokenizer(in.readLine());
        int[] B = new int[M];
        for(int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        int aIdx = 0;   // A 인덱스
        int bIdx = 0;   // B 인덱스
        // A와 B의 공통 숫자 체크
        Set<Integer> commonSet = getCommonSet(A, aIdx, B, bIdx);

        // 없는 경우 0 반환
        if(commonSet.isEmpty()){
            sb.append(0);
        }else{
            // 공통 부분 수열 리스트
            List<Integer> answerList = new ArrayList<>();

            // 공통 숫자가 없을 때까지 반복
            while(!commonSet.isEmpty()){
                // 최대값 탐색 후 추가
                int max = commonSet.stream().max(Integer::compareTo).get();
                answerList.add(max);

                // 인덱스 갱신 : max 이후로 변경!
                while(A[aIdx] != max) aIdx++;
                while(B[bIdx] != max) bIdx++;
                aIdx++;
                bIdx++;

//                System.out.println(max+", "+aIdx+", "+bIdx);
                // 새로운 공통 숫자 체크
                commonSet = getCommonSet(A, aIdx, B, bIdx);
//                System.out.println(commonSet+"=======");
            }
            sb.append(answerList.size()).append("\n");
            for(int answer : answerList) sb.append(answer).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 공통 숫자 체크
    private static Set<Integer> getCommonSet(int[] a, int aIdx, int[] b, int bIdx) {
        // A의 숫자
        Set<Integer> aSet = new HashSet<>();
        for(; aIdx < a.length; aIdx++) aSet.add(a[aIdx]);

        // B의 숫자
        Set<Integer> bSet = new HashSet<>();
        for(; bIdx < b.length; bIdx++) bSet.add(b[bIdx]);

//        System.out.println(aSet+" | "+Arrays.toString(a));
//        System.out.println(bSet+" | "+Arrays.toString(b));
//        System.out.println("--------------");

        // 공통된 부분만 추출하여 반환
        return aSet.stream().filter(bSet::contains).collect(Collectors.toSet());
    }
}
