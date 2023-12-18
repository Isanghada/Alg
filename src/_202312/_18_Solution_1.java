package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28070
// - 누적합 : (년, 월)을 인덱스로 입대, 전역 표시 후 누적합 계산
public class _18_Solution_1 {
    // 인덱스 LIMIT 계산!
    public static final int LIMIT = 10000*12+1;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 친구의 수
        int N = Integer.parseInt(in.readLine());

        // 누저합 초기화
        int[] sum = new int[LIMIT];

        // 모든 친구 정보 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            // 입대 시기 계산
            int start = getDateToInt(st.nextToken());
            /// 전역 시기+1 계산
            int end = getDateToInt(st.nextToken())+1;
            sum[start]++;   // 입대 시기 증가
            sum[end]--;     // 전역 시기 감소
        }

        // 친구 최대값
        int maxCount = 0;
        // 최대값일 경우 최소 시기
        String answer = "2000-01";
        // 2000-01부터 모든 날짜 계산
        for(int date = 2000*12; date < LIMIT; date++){
            // 이번 시기의 누적합 계산
            sum[date] += sum[date-1];
            // 편지의 수가 더 많을 경우 갱신
            if(maxCount < sum[date]){
                maxCount = sum[date];           // 친구 최대값 갱신
                answer = getIntToDate(date);    // 날짜 갱신
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
    // 날짜를 정수(인덱스)로 바꾸는 함수
    private static int getDateToInt(String date) {
        // 년, 월 구분
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);      // 년
        int month = Integer.parseInt(split[1]) - 1; // 월 (0 ~ 11)

        // 인덱스 계산하여 반환
        return year*12 + month;
    }

    // 정수(인덱스)를 날짜로 바꾸는 함수
    private static String getIntToDate(int date) {
        int year = date / 12;       // 년
        int month = date % 12 + 1;  // 월

        // YYYY-DD 형태로 변환
        StringBuilder result = new StringBuilder();
        result.append(year).append("-");
        if(month < 10) result.append("0");
        result.append(month);

        // 날짜 반환
        return result.toString();
    }
}
