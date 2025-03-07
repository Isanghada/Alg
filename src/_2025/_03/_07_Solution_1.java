package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2064
// - 비트마스킹 : Ipv4는 32비트 이므로 int형의 비트로 표현 가능!
//                비트로 표현하여 공통되는 부분을 체크하여 계산
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // IP 주소 개수
        int N = Integer.parseInt(in.readLine());

        // IP 주소 입력
        // - 8비트 씩 나누어 입력
        int[] ipArr = new int[N];
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine(), ".");
            for(int j = 0; j < 4; j++){
                ipArr[i] <<= 8;
                ipArr[i] += Integer.parseInt(st.nextToken());
            }
        }

        int netMask = 0;    // 네트워크 마스크
        int netAddress = 0; // 네트워크 주소

        // 네트워크 마스크 계산!
        for(int i = 31; i >= 0; i--){
            int bit = 1 << i;
            boolean flag = false;

            for(int n = 1; n < N; n++){
                if((ipArr[0] & bit) != (ipArr[n] & bit)){
                    flag = true;
                    break;
                }
            }
            if(flag) break;
            netMask |= bit;
        }

        // 네트워크 마스크를 활용해 네트워크 주소 계산
        netAddress = ipArr[0] & netMask;


        // 출력 형태 초기화
        StringBuilder address = new StringBuilder();
        StringBuilder mask = new StringBuilder();

        // 계산된 값을 활용해 네트워크 주소, 네트워크 마스크로 변환!
        int check = (1 << 8) - 1;
        int k = 3;
        while(true){
            int addressNum = (netAddress >>(8*k)) & check;
            int maskNum = (netMask >>(8*k)) & check;

            address.append(addressNum);
            mask.append(maskNum);

            k--;
            if(k == -1) break;
            address.append(".");
            mask.append(".");
        }

        // 정답 출력
        sb.append(address.toString()).append("\n").append(mask.toString());
        System.out.println(sb);
    }
}
