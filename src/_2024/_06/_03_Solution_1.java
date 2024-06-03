package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2469
// - 구현 : 시작과 끝 지점에서 가려진 부분까지 탐색하여 결과 비교!
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 참가자 수
        int K = Integer.parseInt(in.readLine());
        // 가로 줄의 수
        int N = Integer.parseInt(in.readLine());

        // 끝 지점
        char[] end = in.readLine().toCharArray();
        // 시작 지점
        char[] start = new char[K];
        for(int i = 0; i < K; i++) start[i] = (char)('A' + i);

        // 가려진 사다리 인덱스
        int targetIdx = 0;
        // 사다리 정보 입력
        char[][] map = new char[N][K];
        for(int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
            if(map[i][0] == '?') targetIdx = i;
        }

        // 시작 지점 -> targetIdx까지 탐색
        for(int i = 0; i < targetIdx; i++){
            swap(start, map, i, K);
        }
        // 끝 지점 -> targetIdx까지 역탐색
        for(int i = N-1; i > targetIdx; i--){
            swap(end, map, i, K);
        }

        // 각 자리 비교하며 확인
        for(int i = 0; i < K-1; i++){
            // 위치가 같을 경우 * 출력
            if(start[i] == end[i]) sb.append("*");
            // 위치가 1칸 차이일 경우 - 출력
            else if(start[i] == end[i+1] || start[i+1] == end[i]){
                sb.append("-");
                char temp = start[i];
                start[i] = start[i+1];
                start[i+1] = temp;
            // 불가능할 경우 : x 출력!
            }else{
                sb.setLength(0);
                for(int j = 0; j < K-1; j++) sb.append("x");
                break;
            }
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void swap(char[] target, char[][] map, int n, int k) {
        for(int c = 0; c < k -1; c++){
            if(map[n][c] == '-'){
                char temp = target[c];
                target[c] = target[c+1];
                target[c+1] = temp;
            }
        }
    }
}
