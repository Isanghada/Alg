package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1593
// - 슬라이딩 윈도우 : 순열을 찾아야하므로 범위의 알파벳 개수를 체크하여 확인!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int G = Integer.parseInt(st.nextToken());   // W의 길이
        int S = Integer.parseInt(st.nextToken());   // target의 길이

        // W의 알파벳 분포 입체크
        Map<Character, Integer> gMap = new HashMap<>();
        for(char w : in.readLine().toCharArray()){
            if(!gMap.containsKey(w)) gMap.put(w, 0);
            gMap.put(w, gMap.get(w)+1);
        }

        // 추출한 문자열을 char 배열로 변환!
        char[] target = in.readLine().toCharArray();
        // 초기 G 범위만큼 알파벳 개수 체크!
        Map<Character, Integer> sMap = new HashMap<>();
        for(int idx = 0; idx < G; idx++){
            if(!sMap.containsKey(target[idx])) sMap.put(target[idx], 0);
            sMap.put(target[idx], sMap.get(target[idx])+1);
        }

        // 정답 초기화
        int answer = 0;
//        System.out.println(gMap);
        // 범위별로 체크!
        for(int idx = G; ; idx++){
//            System.out.println(sMap);
            // 현재 상태가 가능한지 확인!
            if(isEquals(gMap, sMap)) answer++;
            // 마지막까지 탐색한 경우 종료
            if(idx == S) break;

            // 이전 범위 감소
            if(!sMap.containsKey(target[idx-G])) sMap.put(target[idx-G], 0);
            sMap.put(target[idx-G], sMap.get(target[idx-G]) - 1);

            // 새로운 범위 증가
            if(!sMap.containsKey(target[idx])) sMap.put(target[idx], 0);
            sMap.put(target[idx], sMap.get(target[idx])+1);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean isEquals(Map<Character, Integer> checkMap, Map<Character, Integer> targetMap) {
        for(char w : checkMap.keySet()){
            if(!targetMap.containsKey(w) || checkMap.get(w) != targetMap.get(w)) return false;
        }
        return true;
    }
}
