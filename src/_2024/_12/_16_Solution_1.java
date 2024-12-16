package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/20302
// - 소수 : 소인수분해를 통해 각 숫자의 개수 체크!
//          - 정수인지 유무를 확인하기 때문에 (음수->양수) 변환하여 체크
//          -
public class _16_Solution_1 {
    public static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수의 개수
        int N = Integer.parseInt(in.readLine());
        // 수식 : 첫 값은 곱셈과 같으므로 "* " 추가하여 체크
        String[] cmd = ("* "+in.readLine()).split(" ");

        // 정수 여부 플래그
        boolean flag = false;
        // 각 인수의 개수 : 곱셈은 +1, 나눗셈은 -1로 계산
        int[] countOfNum = new int[MAX+1];
        for(int i = 0; i < cmd.length; i += 2){
            // 숫자 변환
            int num = Integer.parseInt(cmd[i+1]);
            // 음수 -> 양수 변환
            if(num < 0) num = -num;

            // 0이 있는 경우 => 결과 0으로 고정이므로 종료
            if(num == 0){
                flag = true;
                break;
            }

            // 곱셈인 경우 +1 계산
            if(cmd[i].equals("*")) factorization(num, countOfNum, 1);
            // 나눗셈인 경우 -1 계산
            else factorization(num, countOfNum, -1);
        }

        if(!flag){
            flag = true;
            // 인수 중 음수가 있는 경우 정수가 불가능하므로 flag 변경 후 종료
            for(int num : countOfNum) {
                if(num < 0){
                    flag = false;
                    break;
                }
            }
        }

        // 정답 출력
        sb.append(flag ? "mint chocolate" : "toothpaste");
        System.out.println(sb.toString());
    }

    private static void factorization(int num, int[] countOfNum, int value) {
        int i = 2;
        while(i*i <= num){
            if(num % i > 0) i+=1;
            else{
                while(num % i == 0){
                    num /= i;
                    countOfNum[i] += value;
                }
            }
        }
        if(num > 1) countOfNum[num] += value;
    }
}
