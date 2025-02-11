package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17225
// - 구현, 정렬 : 1. 각 선물별로 포장 시작 시간이 언제이지 체크.
//                2. 포장 시작 시간을 기준으로 오름차순 정렬 : 같을 경우 파란 선물 먼저 진행
//                3. 정렬 결과로 포장 순서 반환
public class _10_Solution_1 {
    // 선물 클래스
    static class Gift implements Comparable<Gift>{
        int startTime;  // 포장 시작 시간
        String color;   // 포장 색깔
        public Gift(int startTime, String color) throws Exception {
            this.startTime = startTime;
            if(color.equals("B") || color.equals("R")){
                this.color = color;
            }else throw new Exception("색깔은 B, R 입니다.");
        }
        // 1. 포장 시작 시간 기준 오름차순 정렬
        // 2. 포장 색깔 기준 오름차순 정렬(B, R)
        @Override
        public int compareTo(Gift o) {
            if(this.startTime == o.startTime) return this.color.compareTo(o.color);
            return this.startTime - o.startTime;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int blue = Integer.parseInt(st.nextToken());    // 상민이의 포장 시간
        int red = Integer.parseInt(st.nextToken());     // 지수의 포장 시간
        int N = Integer.parseInt(st.nextToken());       // 손님의 수

        int blueEndTime = 0;    // 상민이의 이전 포장 완료 시간
        int redEndTime = 0;     // 지수의 이전 포장 완료 시간
        int blueCount = 0;      // 상민이의 포장 개수
        int redCount = 0;       // 지수의 포장 개수

        // 선물 리스트
        List<Gift> giftList = new ArrayList<>();
        giftList.add(new Gift(0, "B"));

        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int T = Integer.parseInt(st.nextToken());   // 주문 시간
            String C = st.nextToken();                  // 포장 색깔
            int M = Integer.parseInt(st.nextToken());   // 선물의 수

            // 포장 색깔에 따라 나누어 진행!
            //  1. 포장 완료 시간과 주문 시간 중 최대값 선택
            //  2. M개의 선물 차례로 포장
            if(C.equals("B")){
                blueEndTime = Math.max(blueEndTime, T);
                blueCount += M;
                while(M-- > 0){
                    giftList.add(new Gift(blueEndTime, C));
                    blueEndTime += blue;
                }
            }else{
                redEndTime = Math.max(redEndTime, T);
                redCount += M;
                while(M-- > 0){
                    giftList.add(new Gift(redEndTime, C));
                    redEndTime += red;
                }
            }
        }

        // 선물 리스트 정렬
        Collections.sort(giftList);
        
        StringBuilder blueOrder = new StringBuilder();  // 상민이 포장 순서
        StringBuilder redOrder = new StringBuilder();   // 지수 포장 순서

        // 정렬 결과를 통해 포장 순서 반환
        for(int gift = 1; gift < giftList.size(); gift++){
            if(giftList.get(gift).color.equals("B")) blueOrder.append(gift).append(" ");
            else redOrder.append(gift).append(" ");
        }

        sb.append(blueCount).append("\n")
                .append(blueOrder).append("\n")
                .append(redCount).append("\n")
                .append(redOrder).append("\n");

        // 정답 반환
        System.out.println(sb);
    }
}
