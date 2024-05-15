package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/30023
// - 그리디 : 첫 전구를 결정하고 이후의 전구 차례로 계산!
public class _16_Solution_1 {
    // 최대값
    public static final int LIMIT = 1000000;
    // 전구 변경 Map
    public static Map<Character, Character> CHANGE_MAP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 전구 변경 정보 추가
        CHANGE_MAP = new HashMap<>();
        CHANGE_MAP.put('R', 'G');
        CHANGE_MAP.put('G', 'B');
        CHANGE_MAP.put('B', 'R');

        // 전구의 수
        int N = Integer.parseInt(in.readLine());
        // 전구 상태
        char[] stateArr = in.readLine().toCharArray();

        // 최대값 설정
        int answer = LIMIT;
        // 첫 전구의 상태에 따라 최소값 계산
        for(int start = 0; start < 3; start++){
            // 변경 횟수 최소값으로 갱신
            answer = Math.min(answer, getMinCount(Arrays.copyOf(stateArr, N), N) + start);
            // 초기 전구 상태 변경!
            setChangeState(stateArr, 0);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 최소값 계산 함수 : 첫 전구 기준으로 모든 전구 변경!
    private static int getMinCount(char[] stateArr, int n) {
        // 변경 횟수
        int count = 0;

        // 각 전구를 첫 전구 색에 맞추어 변경
        for(int i = 1; i < n-2; i++){
            while(stateArr[0] != stateArr[i]){
                count++;
                setChangeState(stateArr, i);
            }
        }

        // 첫 전구, 마지막 전구, 마지막 앞 전구가 같다면 count 반환
        if(stateArr[0] == stateArr[n-1] && stateArr[0] == stateArr[n-2]) return count;
        // 동일할 수 없다면 LIMIT 반환
        return LIMIT;
    }

    // 전구 상태 변경 함수 : i 기준 3개의 전구를 변경!
    private static void setChangeState(char[] stateArr, int i) {
        for(int index = i; index < i+3; index++){
            stateArr[index] = CHANGE_MAP.get(stateArr[index]);
        }
    }
}
