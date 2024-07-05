package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/5904
// - 분할 정복 : n의 위치를 왼쪽, 중간, 오른쪽 3가지 범위로 구분하여 탐색!
public class _05_Solution_1 {
    // S(k) 길이
    public static List<Integer> SIZE_LIST;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N 입력
        int N = Integer.parseInt(in.readLine());

        // N이 포함된 수열까지 계산
        SIZE_LIST = new ArrayList<>();
        SIZE_LIST.add(3);
        for(int k = 1; SIZE_LIST.get(k-1) < N; k++){
            SIZE_LIST.add(SIZE_LIST.get(k-1) * 2 + k+3);
        }

        // 정답 반환
        // - N을 마지막 수열에서 탐색!
        sb.append(moo(N, SIZE_LIST.size()-1));
        System.out.println(sb);
    }
    // Moo 함수 : S(k)수열에서 n 위치의 문자 탐색!
    private static char moo(int n, int k) {
        // n위치의 무자
        char result;

        // 1인 경우 m 반환
        if(n == 1) result = 'm';
        // 3이하인 경우 o반환
        else if(n <= 3) result ='o';
        // 아닐 경우 : 왼쪽, 오른쪽, 중앙 범위 중 선택하여 탐색
        else{
            // 왼쪽 범위에 있는 경우 : k 감소시키고 탐색
            if(n <= SIZE_LIST.get(k-1)) result = moo(n, k-1);
            // 오른쪽 범위에 있는 경우 : n과 k를 감소시키고 탐색
            else if(n > (SIZE_LIST.get(k) - SIZE_LIST.get(k-1))) {
                result = moo(n - SIZE_LIST.get(k) + SIZE_LIST.get(k-1), k-1);
            // 중앙 범위에 있는 경우
            }else{
                n -= SIZE_LIST.get(k-1);
                // 시작 위치이면 m 반환
                if(n == 1) result = 'm';
                // 아닐 경우 o 반환
                else result = 'o';
            }
        }

        return result;
    }
}
