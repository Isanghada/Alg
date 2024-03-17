package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25711
// - 누적합 : 양방향의 거리를 누적합을 통해 계산
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st=   new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 산장 개수
        int Q = Integer.parseInt(st.nextToken());   // 질의 개수

        // X 좌표 배열
        long[] X = Arrays.stream(("0 " + in.readLine()).split(" ")).mapToLong(Long::new).toArray();
        // Y 좌표 배열
        long[] Y = Arrays.stream(("0 " + in.readLine()).split(" ")).mapToLong(Long::new).toArray();

        double[] sum = new double[N+1];         // 정방향 누적합
        double[] sumReverse = new double[N+1];  // 역방향 누적합
        // Y좌표를 기준으로 오르막, 평지, 내리막 판단
        for(int i = 2; i <= N; i++){
            sum[i] = sum[i-1] + getDistance(X[i-1], Y[i-1], X[i], Y[i]) *
                    (Y[i-1] > Y[i] ? 1 : (Y[i-1] == Y[i] ? 2 : 3));
            sumReverse[N-i+1] = sumReverse[N-i+2] + getDistance(X[N-i+2], Y[N-i+2], X[N-i+1], Y[N-i+1]) *
                    (Y[N-i+2] > Y[N-i+1] ? 1 : (Y[N-i+2] == Y[N-i+1] ? 2 : 3));
        }

//        System.out.println(Arrays.toString(sum));
//        System.out.println(Arrays.toString(sumReverse));
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());   // 시작 산장 번호
            int end = Integer.parseInt(st.nextToken());     // 도착 산장 번호

            // 정방향인 경우 : sum 활용
            if(start < end) sb.append(sum[end] - sum[start]);
            // 역방향인 경우 : sumReverse 활용
            else sb.append(sumReverse[end] - sumReverse[start]);
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
    // 거리 계산 함수 : 두 좌표의 거리 계산
    private static double getDistance(long x1, long y1, long x2, long y2) {
        return Math.sqrt(pow(x1-x2, 2)+pow(y1-y2, 2));
    }
    // 제곱 함수 : a를 b만큼 곱하여 반환
    private static long pow(long a, long b){
        long result = 1;
        while(b-- > 0) result *= a;
        return result;
    }
}
