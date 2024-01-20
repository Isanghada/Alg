package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28449
// - 투 포인터 : HI, ARC 팀의 첫 순서부터 차례로 확인
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // HI팀의 인원
        int M = Integer.parseInt(st.nextToken());   // ARC팀의 인원

        // 팀 정보 입력
        int[] HI = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        int[] ARC = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 오름차순 정렬
        Arrays.sort(HI);
        Arrays.sort(ARC);

        // 정답 초기화
        // - answerArr[0] : HI팀 승리 횟수
        // - answerArr[1] : ARC팀 승리 횟수
        // - answerArr[2] : 무승부 횟수
        long[] answerArr = new long[3];

        // 이분 탐색
        for(int hi : HI){
            int lowerLimit = getLowerLimit(hi, ARC);    // 하한 계산
            int upperLimit = getUpperLimit(hi, ARC);    // 상한 계산

//            System.out.println(hi+", "+lowerLimit+", "+upperLimit+", "+Arrays.toString(ARC));
            // HI 팀의 승리 횟수 증가
            answerArr[0] += lowerLimit;
            // 무승부 증가
            answerArr[2] += upperLimit - lowerLimit;
        }

        // 전체 횟수 계산
        long total = (long)N * M;
        // ARC 팀 승리 횟수 계산 : 전체 횟수 - (HI팀 승리 횟수 + 무승부 횟수)
        answerArr[1] = total - (answerArr[0] + answerArr[2]);

        for(long answer : answerArr) sb.append(answer).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    // 하한 함수 : hi 기준 하한값 계산
    private static int getUpperLimit(int hi, int[] arc) {
        int left = 0;
        int right = arc.length;
        while(left < right){
            int mid = (left+right) / 2;

            if(arc[mid] <= hi) left = mid+1;
            else right = mid;
        }

        return left;
    }
    
    // 상한 함수 : hi 기준 상한값 계산
    private static int getLowerLimit(int hi, int[] arc) {
        int left = 0;
        int right = arc.length;
        while(left < right){
            int mid = (left+right) / 2;

            if(arc[mid] >= hi) right = mid;
            else left = mid+1;
        }

        return left;
    }
}
