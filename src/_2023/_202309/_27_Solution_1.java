package _2023._202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1082
// - DP 활용
// - 최소 가격의 숫자 모두 구매
// - 첫 자리수부터 바꿀 수 있는 숫자가 있다면 변경하며 가장 큰 방 번호 출력
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202309/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 숫자의 개수
        int N = Integer.parseInt(in.readLine());

        // 숫자의 가격
        st = new StringTokenizer(in.readLine());
        // - 숫자 가격 배열
        int[] P = new int[N];
        // - 최소 가격 숫자 인덱스
        int minIndex = 0;
        // - 최소 숫가 가격
        int minPrice = Integer.MAX_VALUE;
        for(int idx = 0; idx < N; idx++) {
            // - 숫자 가격 입력
            P[idx] = Integer.parseInt(st.nextToken());

            // 최소값 업데이트
            if(minPrice >= P[idx]){
                minIndex = idx;
                minPrice = P[idx];
            }
        }

        // 사용할 수 있는 금액
        int M = Integer.parseInt(in.readLine());

        // 구매한 숫자로 만들 수 있는 가장 큰 수
        char[] arr = new char[51];
        int cnt = 0;    // 숫자 자릿수

        // 최소 가격의 숫자를 살 수 있는 만큼 모두 구매
        while(M >= minPrice){
            arr[cnt++] = (char) (minIndex + '0');
            M -= minPrice;
        }

        // 첫째 자리수부터 확인하며 큰 값으로 교체할 수 있는 경우 교체
        int start = 0;  // 첫 자리수
        for(int i = 0; i < cnt; i++){
            for(int j = N - 1; j > minIndex; j--){
                // 현재 금액에서 현재 자리수를 사지않았을 때 살 수 있다면 값 변경
                if(P[j] <= M + minPrice){
                    arr[i] = (char) (j + '0');
                    M += minPrice - P[j];
                    break;
                }
            }
            // 첫 자리수가 0이라면 반품!
            if(arr[start] == '0'){
                start++;    // 시작 자릿수 증가
                M += minPrice;  // 금액 증가
            }
//            System.out.println(start+", "+Arrays.toString(arr));
        }

        // 0 제외하고 살 수 없는 경우 0 반환
        if(start == cnt) sb.append(0);
        // start ~ cnt 반복을 돌며 숫자 반환
        else for(int i = start; i < cnt; i++) sb.append(arr[i]);
        System.out.println(sb);
    }
}
