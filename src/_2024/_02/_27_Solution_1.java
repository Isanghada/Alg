package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2666
// - 브루트포스 : 가능한 모든 경우 탐색
public class _27_Solution_1 {
    public static int orderSize;
    public static int[] orderArr;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 벽장 개수
        int N = Integer.parseInt(in.readLine());

        // 열려있는 벽장 2개
        int[] openArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 벽장 순서 길이
        orderSize = Integer.parseInt(in.readLine());

        // 벽장 순서
        orderArr = new int[orderSize];
        for(int i = 0; i < orderSize; i++) orderArr[i] = Integer.parseInt(in.readLine());

        // 정답 반환
        sb.append(getMinCount(0, openArr[0], openArr[1]));
        System.out.println(sb);
    }

    // 최소 이동 횟수 함수 : 재귀를 통해 계산
    private static int getMinCount(int step, int a, int b) {
        // 모든 벽장을 연 경우 0 반환
        if(step == orderSize) return 0;
        // 왼쪽(a), 오른쪽(b) 중 최소값 선택!
        return Math.min( Math.abs(a - orderArr[step]) + getMinCount(step+1, orderArr[step], b),
                         Math.abs(b - orderArr[step]) + getMinCount(step+1, a, orderArr[step]));
    }
}
