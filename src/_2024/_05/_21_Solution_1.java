package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15922
// - 스위핑 : 현재 선분의 정보를 갱신하며 길이 계산!
//            선분 정보가 정렬된 상태(x 오름차순, y 오름차순)이므로 추가 정렬 X
public class _21_Solution_1 {
    public static int answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 선분의 개수
        int N = Integer.parseInt(in.readLine());

        // 선분 길이의 총합
        int answer = 0;
        // 현재 계산하는 선분 정보!
        int[] point = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        StringTokenizer st = null;
        // N번 반복!
        while(N-- > 0){
            // 선분 입력
            st = new StringTokenizer(in.readLine());
            int[] cur = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
            // 이전 선분과 연결되지 않은 경우
            if(cur[0] > point[1]) {
                // 이전 선분 길이만큼 증가
                answer += point[1] - point[0];
                // 선분 갱신
                point[0] = cur[0];
                point[1] = cur[1];
            // 이전 선분과 연결된 경우 : 종료 지점 갱신!
            }else point[1] = Math.max(point[1], cur[1]);
        }
        // 마지막 선분 길이만큼 증가
        answer += point[1] - point[0];

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}