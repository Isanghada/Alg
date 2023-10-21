package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2087
// - 비트 마스킹과 이분 탐색 활용. => 투 포인터를 활용했어도 좋았을거 같다
// - 전체를 한 번에 계산하면 2^40이 되니 이를 반으로 나누어 진행
// - bit 수를 절반으로 나누어 A, B로 나누고 식을 (K = At + Bt) => (K - Bt) = At 으로 변환.
// - 최대 비트수는 2^20 = 1048576
public class _21_Solution_1 {
    // 각 비트의 값을 따로 계산하면 오래걸리므로 static 선언
    // - BIT[idx] : (2^idx)
    public static int[] BIT;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 비트 수 입력
        int N = Integer.parseInt(in.readLine());
        // A, B로 나누어 입력
        int lenA = N / 2;
        int lenB = N - lenA;
        long[] A = new long[lenA];
        long[] B = new long[lenB];
        for(int i = 0; i < lenA; i++) A[i] = Long.parseLong(in.readLine());
        for(int i = 0; i < lenB; i++) B[i] = Long.parseLong(in.readLine());
        // 암호화된 숫자 입력
        int K = Integer.parseInt(in.readLine());

        // 각 비트 계산
        BIT = new int[lenB];
        BIT[0] = 1;
        for(int idx = 1; idx < lenB; idx++) BIT[idx] = BIT[idx-1] * 2;

        // A의 모든 조합 계산
        long[] sumA = new long[(int) Math.pow(2, lenA)];
        setSumArr(sumA, A);
        // B의 모든 조합 계산 
        long[] sumB = new long[(int) Math.pow(2, lenB)];
        setSumArr(sumB, B);

        // A를 (bitmask, sum)으로 변환 : 비트마스킹과 값을 유지한 채로 정렬하기 위해!
        long[][] sortSumA = new long[sumA.length][2];
        for(int bitmask = 0; bitmask < sumA.length; bitmask++)
            sortSumA[bitmask] = new long[] {bitmask, sumA[bitmask]};
        // sum을 기준으로 오름차순 정렬
        Arrays.sort(sortSumA, (o1, o2) -> {
            long diff = o1[1] - o2[1];
            if(diff > 0) return 1;
            else if(diff == 0) return 0;
            else return -1;
        });

        // 정답 플래그
        boolean flag = false;
        // sumB의 모든 값에 대해 가능한 sortSumA가 있는지 확인
        // - flag가 true인 경우 종료!
        for(int bBit = 0; bBit < sumB.length && !flag; bBit++){
            // sortSumA의 목표값 계산
            long result = K - sumB[bBit];
            // 음수라면 패스 : 모든 값이 0 혹은 양수이므로 음수가 될 수 없음.
            if(result < 0) continue;

            // 이분 탐색을 통해 목표값이 있는지 확인
            // left 초기화
            int left = 0;
            // right 초기화
            int right = sortSumA.length -1;
            // 이분 탐색
            while(left <= right){
                // 중간 인덱스 계산
                int mid = (left + right) / 2;

                // 목표값과 동일한 경우
                if(sortSumA[mid][1] == result){
                    // A의 이진 암호문 입력
                    sb.append(getResult(sortSumA[mid][0], lenA));
                    // B의 이진 암호문 입력
                    sb.append(getResult(bBit, lenB));
                    // flag 변경
                    flag = true;
                    // 종료
                    break;
                // 현재 값이 목표값보다 큰 경우
                }else if(sortSumA[mid][1] > result){
                    // - right 변경
                    right = mid - 1;
                // 현재 값이 목표값보다 작은 경우
                }else{
                    // - left 변경
                    left = mid + 1;
                }
            }
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 이진 암호문 반환 함수
    // - bitmask : 현재 비트마스킹
    // - len : 현재 암호문 길이
    private static String getResult(long bitmask, int len) {
        // StringBuilder을 통해 입력
        StringBuilder result = new StringBuilder();
        // 비트 연산을 통해 1 이상인 경우 1 반환, 아닐 경우 0 반환
        for(int bit = 0; bit < len; bit++) result.append((bitmask & BIT[bit]) >= 1 ? 1 : 0);

        return result.toString();
    }

    // 암호문의 합 계산 함수 : 전체 반복이라 계산된 값을 또 계산해야하는 구조. 재귀를 사용했다면 더 효율적이였을 것.
    // - sum : bitmask에 대한 합을 담을 배열
    // - numbers : 이진값과 곱해질 숫자 배열
    private static void setSumArr(long[] sum, long[] numbers) {
        // 모든 bitmask에 대해 계산
        for(int bitmask = 0; bitmask < sum.length; bitmask++){
            // 비트 연산을 통해 각 비트가 1 이상인 경우 값 증가
            for(int idx = 0; idx < numbers.length; idx++){
                if((bitmask & BIT[idx]) >= 1) sum[bitmask] += numbers[idx];
            }
        }
    }
}
