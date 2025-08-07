package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/34051
// - 그리디 : 문자열을 정렬하여 최상의 순서가 아닌 위치에서 뒤집기!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 크기
        int N = Integer.parseInt(in.readLine());

        // 기본 문자열
        Character[] origin = in.readLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        // 정렬 문자열 : 최상의 문자열
        Character[] sorted = Arrays.copyOf(origin, N);
        Arrays.sort(sorted, Comparator.reverseOrder());

        // 정답 후보 : 초기값 추가
        List<String> changeList = new ArrayList<>();
        changeList.add(chracterToString(origin));

        // 최상의 문자가 아닌 위치를 기준으로 뒤집기
        for(int start = 0; start < N; start++){
            if(origin[start] != sorted[start]){
                // 모든 가능한 조합 추가
                for(int end = start + 1; end < N; end++){
                    if(origin[start] < origin[end]){
                        reverse(origin, start, end);
                        changeList.add(chracterToString(origin));
                        reverse(origin, start, end);
                    }
                }
                break;
            }
        }

        // 정렬 후 최상의 값 출력
        Collections.sort(changeList, Comparator.reverseOrder());

        // 정답 출력
        sb.append(changeList.get(0));
        System.out.println(sb);
    }

    private static String chracterToString(Character[] target) {
        StringBuilder sb = new StringBuilder();
        for(char ch : target) sb.append(ch);
        return sb.toString();
    }

    private static void reverse(Character[] origin, int start, int end) {
        while(start < end){
            char temp = origin[start];
            origin[start] = origin[end];
            origin[end] = temp;

            start++; end--;
        }
    }
}
