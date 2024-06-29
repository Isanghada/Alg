package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30459
// - 이분 탐색 : 범위별로 최대값을 찾아 비교!
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());       // 말뚝의 개수
        int M = Integer.parseInt(st.nextToken());       // 깃대 개수
        int R = Integer.parseInt(st.nextToken()) * 2;   // 최대 넓이 => 사각형으로 변환하여 활용

        // 말뚝 정보
        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        // 범위로 변환
        Set<Integer> wSet = new HashSet<>();
        for(int s = 0; s < N; s++){
            for(int e = s+1; e < N; e++) wSet.add(Math.abs(A[e] - A[s]));
        }
        List<Integer> wList = new ArrayList<>(wSet);
//        System.out.println(wList);

        // 깃대 정보 입력
        st = new StringTokenizer(in.readLine());
        int[] B = new int[M];
        for(int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());
        // 이분 탐색 활용을 위해 정렬
        Arrays.sort(B);

        // 정답 초기화
        int answer = -1;
        // 모든 말뚝 범위에서 이분 탐색으로 가능한 최대 넓이 계산
        for(int w : wList){
            int left = 0;
            int right = M-1;
            while(left <= right){
                int mid = (left + right) / 2;
                // 선택한 정보 토대로 사각형의 넓이 계산
                int square = w * B[mid];
                // R 이하일 경우 : 정답 최대값으로 갱신, left 변환
                if(square <= R){
                    answer = Math.max(answer, square);
                    left = mid+1;
                // 아닐 경우 right 변환
                }else right = mid -1;
            }
        }

        // 정답 반환
        // - 가능지 않은 경우 -1 반환
        // - 가능한 경우 : 넓이 반환!
        sb.append( (answer == -1) ? answer : (String.format("%.1f", (answer / 2.0))) );
        System.out.println(sb);
    }
}