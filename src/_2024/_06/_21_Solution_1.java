package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.acmicpc.net/problem/3655
// - 그리디 : 원본과 정렬한 경우를 비교하여 차이 게산
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 사람의 수
            int N = Integer.parseInt(in.readLine());
            // 사람의 정보
            char[] info = in.readLine().toCharArray();
            // 각 그룹별 인덱스 정보 입력
            Map<Character, List<Integer>> indexMap = new HashMap<>();
            for(int index = 0; index < N; index++){
                if(!indexMap.containsKey(info[index])) indexMap.put(info[index], new ArrayList<>());
                indexMap.get(info[index]).add(index);
            }
//            System.out.println(indexMap);
//            System.out.println("-----");

            int origin = 0; // 원본의 경우
            int change = 0; // 정렬한 경우
            for(char w : indexMap.keySet()){
                // 마지막 인덱스 계산
                int last = indexMap.get(w).get(indexMap.get(w).size() - 1);
                // 모든 인덱스가 마지막 사람을 기다리는 시간 계산
                for(int index : indexMap.get(w)) origin += last - index;
                // 정렬한 경우 기다리는 시간 계산
                // ex) 1, 2, 5일 경우 1, 2, 3으로 정렬되고
                //     기다리는 시간은 2, 1, 0 이다.
                //     따라서, 0 ~ (size-1)의 시간인 등차수열이므로 식을 이용해 바로 계산
                change += getSum(indexMap.get(w).size()-1);
            }

            // 원본에서 정렬한 경우를 빼고 5초를 곱하여 출력
            sb.append((origin - change) * 5L).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int getSum(int n) {
        return (n * (n+1)) / 2;
    }
}