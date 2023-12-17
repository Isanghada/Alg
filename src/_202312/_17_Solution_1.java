package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/27172
// - 에라토스테네스의 체 : 모든 경우 체크!
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수의 개수
        int N = Integer.parseInt(in.readLine());
        int[] numArr = new int[N];          // 각 사람의 수
        int[] numPosArr = new int[1000001]; // 각 수를 가진 사람의 인덱스
        Arrays.fill(numPosArr, -1); // -1로 초기화
        
        // 인덱스 초기화
        int idx = 0;
        // 모든 입력값 설정
        for(int num : Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray()){
            numArr[idx] = num;      // 현재 인덱스의 숫자 입력
            numPosArr[num] = idx++; // 숫자를 가진 인덱스 입력
        }

        // 정답 초기화
        int[] answerArr= new int[N];
        // 모든 숫자가 이기는 경우로 설정!
        for(int win : numArr){
            // win 기준 지는 모든 숫자 탐색
            int lose = win * 2;
            // 1000000 이하인 경우 반복
            while(lose <= 1000000){
                // 존재하는 숫자일 경우
                if(numPosArr[lose] != -1){
                    answerArr[numPosArr[lose]]--;   // 패배 스코어 감소
                    answerArr[numPosArr[win]]++;    // 승리 스코어 증가
                }
                // 패배하는 경우 증가
                lose += win;
            }
        }

        // 정답 출력
        for(int score : answerArr) sb.append(score).append(" ");
        System.out.println(sb);
    }
}
