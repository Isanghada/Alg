package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/3107
// - 구현!
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // ipv6 주소 입력
        //   1. 0으로 이루어진 연속된 그룹을 식별하기 위해 ::에서 :-:로 변경
        //   2. : 기준으로 분리
        String[] ipv6 = in.readLine().replace("::", ":-:").trim().split(":");

        // 전체 IP 주소 리스트!
        List<String> address = new ArrayList<>();
        // 각 그룹 추가
        for(int idx = 0; idx < ipv6.length; idx++){
            // - 일 경우 : 0으로 이루어진 연속된 그룹
            if(ipv6[idx].equals("-")){
                // 0으로 이루어진 그룹의 수 계산
                int count = 8 - ipv6.length + 1;
                // 0000 그룹 추가
                while(count-- > 0) address.add("0000");
            // - 가 아닐 경우
            }else{
                // 현재 그룹에서 0의 개수 계산
                int count = 4 - ipv6[idx].length();
                StringBuilder part = new StringBuilder();
                // 0 추가
                while(count-- > 0) part.append("0");
                // 생략된 주소 추가
                part.append(ipv6[idx]);
                address.add(part.toString());
            }
        }

        // 정답 반환
        sb.append(String.join(":", address));
        System.out.println(sb);
    }
}
