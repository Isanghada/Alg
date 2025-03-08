package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5710
// - 이분탐색 : 1. 전체 사용 와트를 비용을 기준으로 계산
//              2. 이분 탐색을 통해 B 만큼의 비용 차이가 발생하는 쌍 탐색
//              3. 상근이는 가장 전기를 적게 쓴다고 확신하므로
//                 탐색한 결과 중 와트가 작은 값 반환
//              4. 상근이 사용 와트 기준으로 비용 계산
public class _08_Solution_1 {
    // 전기 사용 비용 타입
    static final int TYPE = 4;
    // 사용 기준
    static final int[] WATT_HOUR = new int[]{0, 100, 10_000, 1_000_000};
    // 사용 비용
    static final int[] WATT_FEE = new int[]{2, 3, 5, 7};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());   // 전체 비용
            int B = Integer.parseInt(st.nextToken());   // 차이 비용

            // 끝나는 경우!
            if(A == 0 && B == 0) break;

            // A를 기준으로 전체 와트 계산
            int totalWatt = getFeeToWatt(A);

            // 1. totalWatt를 기준으로 B만큼 비용 차이가 발생하는 쌍 계산
            // 2. 쌍 중 작은 값을 반환하여 해당 와트의 비용 계산
            sb.append(getWattToFee(getTargetWatt(totalWatt, B)));
            sb.append("\n");
        }


        // 정답 출력
        System.out.println(sb);
    }

    private static int getTargetWatt(int totalWatt, int absDiffFee) {
        int left = 0;
        int right = totalWatt;
        while(left <= right){
            int mid = (left + right) / 2;

            int A = getWattToFee(mid);
            int B = getWattToFee(totalWatt - mid);
            int diff = Math.abs(A-B);

            if(diff == absDiffFee) return Math.min(mid, totalWatt-mid);
            else if(diff > absDiffFee) right = mid - 1;
            else left = mid + 1;
        }


        return 0;
    }

    private static int getWattToFee(int watt) {
        int fee = 0;
        for(int i = TYPE - 1; i >= 0; i--){
            if(watt > WATT_HOUR[i]){
                fee += (watt - WATT_HOUR[i]) * WATT_FEE[i];
                watt = WATT_HOUR[i];
            }
        }
        return fee;
    }

    private static int getFeeToWatt(int fee) {
        int watt = 0;

        for(int i = 0; i < (TYPE-1) && fee > 0; i++){
            int maxWatt = WATT_HOUR[i+1] - WATT_HOUR[i];
            int maxFee = maxWatt * WATT_FEE[i];
            if(fee >= maxFee){
                fee -= maxFee;
                watt += maxWatt;
            }else{
                watt += fee / WATT_FEE[i];
                fee = 0;
            }
        }
        if(fee > 0) watt += fee / WATT_FEE[TYPE-1];

        return watt;
    }
}
