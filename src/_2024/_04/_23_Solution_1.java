package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/20495
// - 이분 탐색 : 이분 탐색을 통해 하한, 상한 탐색!
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 수열 길이
        int N = Integer.parseInt(in.readLine());
        // 각 수열 정보
        int[][] arr = new int[N][2];
        // 각 수열의 하한 정보
        List<Integer> leftList = new ArrayList<>();
        // 각 수열의 상한 정보
        List<Integer> rightList = new ArrayList<>();

        // 수열 정보 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 기준값
            int b = Integer.parseInt(st.nextToken());   // 오차범위 변수

            arr[i][0] = a-b;    // 하한
            arr[i][1] = a+b;    // 상한

            leftList.add(arr[i][0]);
            rightList.add(arr[i][1]);
        }
        // 리스트 정렬 : 이분 탐색을 위해
        Collections.sort(leftList);
        Collections.sort(rightList);

//        for(int[] a : arr) System.out.print(Arrays.toString(a)+", ");
//        System.out.println();
//        System.out.println(leftList);
//        System.out.println(rightList);

        // 각 수열 정보의 인덱스 출력
        for(int i = 0 ;i < N; i++){
            // 하한 출력
            sb.append(lowerBound(rightList, arr[i][0])+1).append(" ")
                    // 상한 출력
                    .append(upperBound(leftList, arr[i][1])).append("\n");
        }

        System.out.println(sb);
    }
    // 하한 함수
    public static int lowerBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size() - 1;
        while(left <= right){
            int mid = (left+right) / 2;
            if(list.get(mid) >= target) right = mid-1;
            else left = mid + 1;
        }

        return left;
    }
    // 상한 함수
    public static int upperBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size() - 1;
        while(left <= right){
            int mid = (left+right) / 2;
            if(list.get(mid) <= target) left = mid + 1;
            else right = mid-1;
        }

        return left;
    }
}
