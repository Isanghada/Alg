package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12025
// - 구현 : 1, 2, 6, 7을 비트화하여 0인 경우 1 혹은 2, 1인 경우 6 혹은 7로 변경!
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 패스워드
        char[] password = in.readLine().toCharArray();
        // 1, 2, 6, 7 비트화! : 순서대로 기록
        int[] num = new int[password.length];

        int cnt = 0;
        for(int i = password.length - 1; i >= 0; i--){
            // 1, 2, 6, 7 인 경우 추가하고 작은 수로 변환
            if(password[i] == '1' || password[i] == '2' || password[i] == '6' || password[i] == '7' ){
                // 위치 기록
                num[cnt++] = i;
                // 6은 1로, 7은 2로 변환
                if(password[i] == '6') password[i] = '1';
                else if (password[i] == '7') password[i] = '2';
            }
        }

        // 순서 입력
        long K = Long.parseLong(in.readLine());
        K--;
        // 최대 순서 입력
        final long MAX = (long) Math.pow(2, cnt);

        // 불가능한 순서인 경우
        if(MAX < K) sb.append(-1);
        // 차례대로 변환! : K를 2진수로 변환해 0인 경우 1 혹은 2, 1인 경우 6 혹은 7로 변환!
        else{
            cnt = 0;
            while(K > 0){
                if(K%2 == 1){
                    if (password[num[cnt]] == '1') password[num[cnt]] = '6';
                    else if (password[num[cnt]] == '2') password[num[cnt]] = '7';
                }
                K /= 2;
                cnt++;
            }

            for(char p : password) sb.append(p);
        }

        // 결과 반환
        System.out.println(sb);
    }
}
