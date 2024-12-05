package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13701
// - 비트마스킹 : int형은 32(2^5)byte이므로 인덱스 하나당 32개의 데이터를 저장할 수 있으므로,
//                최대 수인 2^25까지 저장하지 위해 2^20까지 인덱스 사용!
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // bit 계산
        int[] bit = new int[32];
        bit[0] = 1;
        for(int b = 1; b < 32; b++) bit[b] = bit[b-1] << 1;

        StringTokenizer st = new StringTokenizer(in.readLine());

        // 비트 마스킹
        int[] check = new int[bit[20]+1];
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            int x = num / 32;   // 인덱스
            int y = num % 32;   // 비트
            // - 중보수가 아니라면 출력
            if((check[x] & bit[y]) == 0){
                check[x] |= bit[y];
                sb.append(num).append(" ");
            }
        }

        // 정답 반환
        System.out.println(sb);
    }
}
