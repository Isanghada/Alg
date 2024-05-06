package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/27447
// - 구현 : 토기를 만들어두고 커피 서빙이 가능한지 확인!
public class _06_Solution_1 {
    // 최대 시간
    public static final int MAX_TIME = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 손님의 수
        int M = Integer.parseInt(st.nextToken());   // 흙탕물이 되는 시간

        // 정답 초기화
        String answer = "success";

        // 손님 도착 시간 토큰!
        st = new StringTokenizer(in.readLine());
        
        int time = 0;       // 현재 시간
        int togiCount = 0;  // 토기 개수
        // 상태 여부
        // - false : 토기 생성 or 커피 넣기
        // - true : 서빙
        boolean[] stateArr = new boolean[MAX_TIME+1];
        
        // 토큰이 남아있다면 반복
        while(st.hasMoreTokens()){
            // 고객 도착 시간
            int customer = Integer.parseInt(st.nextToken());

            // 서빙 시간 체크
            stateArr[customer] = true;
            // (도착 시간 - M)시간 전까지 토기 생성
            while(time < (customer - M)) if(!stateArr[time++]) togiCount++;

            // 토기 개수가 0인 경우 도착 시간전에 토기 생성
            while(togiCount == 0 && time < customer) {
                if(!stateArr[time++]){
                    togiCount++;
                    break;
                }
            }

            // 토기가 1개 이상인 경우
            if(togiCount > 0){
                // 도착 시간 전까지 중 커피를 넣은 시간 탐색
                while(time < customer) {
                    if(!stateArr[time]) break;
                    time++;
                }

                // 도착 전에 시간이 있다면 커피 넣기!
                // - 시간 증가, 토기 개수 감소
                if(time < customer){
                    time++;
                    togiCount--;
                // 도착 전에 시간이 없다면 fail로 갱신
                }else{
                    answer = "fail";
                    break;
                }
            // 토기가 없다면 fail로 갱신
            }else {
                answer = "fail";
                break;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
