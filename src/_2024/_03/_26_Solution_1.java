package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10453
// - a나 b 하나를 기준으로 잡고 차례로 위치 변경
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 좋은 문자열 입력
            StringTokenizer st = new StringTokenizer(in.readLine());
            String A = st.nextToken();  // 기존 문자열
            String B = st.nextToken();  // 변경 문자열

            // A에서 a의 위치를 담을 리스트
            List<Integer> aIdx = new ArrayList<>();
            for(int i = 0; i < A.length(); i++){
                if(A.charAt(i) == 'a') aIdx.add(i);
            }
            
            int answer = 0; // 정답 초기화
            int idx = 0;    // aIdx 인덱스
            // B에서 a의 위치로 aIdx에 있는 a를 옮길 때 값 계산
            for(int i = 0; i < B.length(); i++){
                if(B.charAt(i) == 'a') answer += Math.abs(aIdx.get(idx++) - i);
            }

            // 정답 출력
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
