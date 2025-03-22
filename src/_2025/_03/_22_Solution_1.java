package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Arrays.binarySearch;

// https://www.acmicpc.net/problem/16210
// - 정렬, 누적합 : 좌표 (x, y)를 각각으로 나누어 정렬 후 누적합!
//                  각 은행 좌표를 기준으로 택시 거리 계산
//                  - 은행 좌표보다 큰 범위, 작은 범위를 찾아 계산할 수 있음.
//                    - (x * 작은 범위의 개수) - (작은 범위의 합)
//                    - (큰 범위의 합) - (x * 큰 범위의 개수)
public class _22_Solution_1 {
    // 은행 클래스
    static class Bank{
        int x;  // x 좌표
        int y;  // y 좌표
        public Bank(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 은행 개수
        int N = Integer.parseInt(in.readLine());

        // 은행 초기화
        Bank[] banks = new Bank[N+1];
        StringTokenizer st = null;

        // x, y 좌표 따로 입력
        int[] x = initN(N);
        int[] y = initN(N);
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            // 은행 입력
            banks[i] = new Bank(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            // x, y좌표 따로 입력
            x[i] = banks[i].x;
            y[i] = banks[i].y;
        }

        // x, y좌표 정렬
        Arrays.sort(x);
        Arrays.sort(y);

        // x, y좌표 누적합 계산
        long[] sumX = new long[N+1];
        long[] sumY = new long[N+1];
        for(int i = 1; i<= N; i++){
            sumX[i] = sumX[i-1] + x[i];
            sumY[i] = sumY[i-1] + y[i];
        }

        // 정답 초기화
        long sumDistance = Long.MAX_VALUE;  // 최소 택시 거리의 합
        int answer = 0;                     // 본점 번호
        // 모든 은행 체크!
        for(int i = 1; i <= N; i++){
            // 현재 은행 x좌표와 동일한 좌표의 인덱스
            int idxX = binarySearch(x, banks[i].x);
            // 현재 은행 y좌표와 동일한 좌표의 인덱스
            int idxY = binarySearch(y, banks[i].y);

            // x좌표의 택시 거리
            long xDistance = getDistance(sumX, banks[i].x, idxX, N);
            // y좌표의 택시 거리
            long yDistance = getDistance(sumY, banks[i].y, idxY, N);

            // 택시 거리의 합
            long distance = xDistance+yDistance;
            // 택시 거리의 합에 따라 정답 갱신
            if(sumDistance > distance){
                sumDistance = distance;
                answer = i;
            }
        }
//        System.out.println(Long.MAX_VALUE+", "+4_000_000_000L*500_000);
//        System.out.println(sumDistance+"----");
        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
    // 택시 거리 계산 함수
    // - target과 동일한 좌표는 택시 거리가 0이 되므로 target을 포함하여 계산
    private static long getDistance(long[] sum, long target, int idx, int n){
        long distance = ((target * idx) - sum[idx]) +                   // 현재 target 이하인 경우
                        ((sum[n] - sum[idx-1]) - (target * (n-idx+1))); // 현재 target 이상인 경우

        return distance;
    }
    private static int[] initN(int n) {
        int[] arr = new int[n+1];
        arr[0] = Integer.MIN_VALUE;
        return arr;
    }
}
