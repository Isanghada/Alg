package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10713
// - 누적합 : 각 기차를 사용 횟수의 누적합으로 해당 기차의 최소 비용 계산
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 경로의 수

        // 열차 이용 횟수
        int[] trainCount = new int[N+1];
        st = new StringTokenizer(in.readLine());
        // 시작 지점
        int start = Integer.parseInt(st.nextToken());
        for(int i = 1 ;i < M; i++) {
            // 도착 지점
            int m = Integer.parseInt(st.nextToken());
            // 누적합 계산을 위해 체크
            setTrainCount(trainCount, start, m);
            // start 갱신
            start = m;
        }
        // 누적합 계산
        for(int n = 1; n <= N; n++) trainCount[n] += trainCount[n-1];
//        System.out.println(Arrays.toString(trainCount));

        // 정답 초기화
        long answer = 0;
        for(int n = 1; n < N; n++){
            // n번째 기차 정보 입력
            st = new StringTokenizer(in.readLine());
            long a = Long.parseLong(st.nextToken());    // 현금 가격
            long b = Long.parseLong(st.nextToken());    // IC 가격
            long c = Long.parseLong(st.nextToken());    // IC카드 가격

            // (현금*횟수)과 (IC * 횟수 + IC카드) 중 최소값 만큼 증가
            answer += Math.min(a * trainCount[n], b * trainCount[n] + c);
        }
        
        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
    // 누적합 체크 함수 : p1, p2위치에 따라 계산
    // - 작은값 : 증가
    // - 큰 값 : 감소
    private static void setTrainCount(int[] trainCount, int p1, int p2) {
        // p1이 p2보다 클 경우 값 변경
        if(p1 > p2){
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }
        trainCount[p1]++;
        trainCount[p2]--;
    }
}
