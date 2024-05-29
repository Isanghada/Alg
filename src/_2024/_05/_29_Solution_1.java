package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/10975
// - 그리디 : 만들어둔 덱에 추가할 수 있는지 여부를 차례로 확인
//            기존의 덱에 추가하기 위해서는, 인접한 수가 덱에 추가되어 있어야 한다.
//            인접한 수가 사용되었는지 여부를 확인하여 이를 체크할 수 있다.
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수의 개수
        int N = Integer.parseInt(in.readLine());

        // 수 배열
        int[] numbers = new int[N+2];
        // 정렬된 배열
        int[] sort = new int[N+2];
        sort[0] = -10000;
        sort[N+1] = 10000;
        
        // 숫자 사용 여부
        boolean[] used = new boolean[N+2];
        // 수 입력
        for(int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
            sort[i] = numbers[i];
        }
        // 오름차순 정렬
        Arrays.sort(sort);

        // 덱의 수 초기화
        int answer = 0;
        // 배열의 수를 차례로 확인
        for(int i = 1; i <= N; i++){
            // 현재 수의 정렬된 인덱스 찾기
            int index = 1;
            while(sort[index] != numbers[i]) index++;

            // 사용 체크!
            used[index] = true;
            // 인접한 수 중 사용한 것이 없는 경우 새로운 덱 생성!
            if(!used[index-1] && !used[index+1]) answer++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}