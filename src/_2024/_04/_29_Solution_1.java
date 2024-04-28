package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1052
// - 참고 : https://tussle.tistory.com/950
// - 그리디 : 물병의 수를 이진수로 표현하여 계산
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물병의 수
        int K = Integer.parseInt(st.nextToken());   // 가져갈 수 있는 물병의 수

        // 정답
        int answer = 0;

        // N -> 비트값
        String bitN = Integer.toBinaryString(N);
        // 1의 개수 : 합친 물병의 개수
        int countOfOne = Integer.bitCount(N);

        // K보다 많은 물병인 경우
        if(countOfOne > K){
            // 인덱스
            int index = 0;
            // 사야할 물병 위치 찾기
            for(int i = 0; i < bitN.length(); i++){
                // K개의 물병을 탐색한 경우 현재 인덱스 반환 및 종료
                if(K == 0){
                    index = i;
                    break;
                }
                // 물병 위치인 경우 K 감소
                if(bitN.charAt(i) == '1') K--;
            }

            // K개를 초과하는 물병의 수
            String temp = bitN.substring(index);
            int t = Integer.parseInt(temp, 2);

            // 구매해야하는 물병의 수 계산
            if(t != 0) answer = (int) (Math.pow(2, bitN.length() - index) - t);
        }


        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}