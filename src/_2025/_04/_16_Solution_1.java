package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10464
// - XOR : XOR의 규칙 활용
//         1. 모든 범위의 값에 XOR을 진행하면 다음과 같은 순서를 따름
//            0000 -> 0000(0)       4로 나눈 나머지가 0일때 (마지막 값)
//            0001 -> 0001(1)       4로 나눈 나머지가 1일때 1
//            0010 -> 0011(3)       4로 나눈 나머지가 2일때 (마지막 값 + 1)
//            0011 -> 0000(0)       4로 나눈 나머지가 3일때 0
//            0100 -> 0100(4)       4로 나눈 나머지가 0일때 (마지막 값)
//         2. 같은 값을 XOR 할 경우 항상 0 : n XOR n == 0
//            따라서, (1 ~ F까지 XOR) XOR ( 1 ~ (S-1)까지 XOR) == S ~ F까지 XOR
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int S =  Integer.parseInt(st.nextToken());
            int F =  Integer.parseInt(st.nextToken());

            sb.append(allXor(F) ^ allXor(S-1)).append("\n");
        }
        
        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static int allXor(int num) {
        int remainder = num % 4;
        if(remainder == 0) return num;
        else if(remainder == 1) return 1;
        else if(remainder == 2) return num + 1;
        else return 0;
    }
}
