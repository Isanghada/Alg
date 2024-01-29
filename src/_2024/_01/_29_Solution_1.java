package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14719
// - 각 열에서 고일 수 있는 빗물 계산!
//   - 각 열에서 왼쪽, 오른쪽 최대 블록 높이를 구하여 비교!
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int H = Integer.parseInt(st.nextToken());   // 세로 길이
        int W = Integer.parseInt(st.nextToken());   // 가로 길이

        // 블록 높이 배열
        int[] blockArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        int answer = 0;
        // 양끝은 빗물이 고일 수 없으므로 제외하고 반복!
        for(int col = 1; col < W-1; col++){
            int left = 0;   // 왼쪽 최대 높이
            int right = 0;  // 오른쪽 최대 높이

            // 왼쪽 최대 높이 계산
            for(int leftCol = 0; leftCol < col; leftCol++){
                left = Math.max(left, blockArr[leftCol]);
            }

            // 오른쪽 최대 높이 계산
            for(int rightCol = col+1; rightCol < W; rightCol++){
                right = Math.max(right, blockArr[rightCol]);
            }

            // 현재 높이가 left, right 보다 낮을 경우 고일 수 있는 빗물 계산하여 증가
            if(blockArr[col] < left && blockArr[col] < right) answer += Math.min(left, right) - blockArr[col];
        }


        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}