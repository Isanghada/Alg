package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/20916
// - 이분 탐색 : 가능한 안녕한 정수는 총 12개! 모든 값에서 안녕한 정수를 만들기 위한
//               값을 게산하여 이분 탐색으로 확인!
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 가능한 안녕한 정수 배열
        int[] integers = new int[]{
                202_021, 20_202_021, 202_002_021, 202_012_021,
                202_022_021, 202_032_021, 202_042_021, 202_052_021,
                202_062_021, 202_072_021, 202_082_021, 202_092_021
        };

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 수열 크기
            int N = Integer.parseInt(in.readLine());
            // 각 수의 개수
            Map<Integer, Long> countMap = new HashMap<>();
            for(String a : in.readLine().split(" ")){
                int num = Integer.parseInt(a);
                if(!countMap.containsKey(num)) countMap.put(num, 0L);
                countMap.put(num, countMap.get(num)+1);
            }
            // 수열 생성! : Map의 key로 배열 생성!
            int[] A = countMap.keySet().stream().mapToInt(Integer::new).toArray();
            Arrays.sort(A);

            // 정답 초기화
            long answer = 0;
            // 모든 수 탐색
            for(int cur = 0; cur < A.length; cur++){
                for(int helloInteger : integers){
                    // 타겟 계산
                    int target = helloInteger - A[cur];
                    // 찾을 수 없는 경우 패스
                    if(target <= A[cur]) continue;

                    // 시작 지점은 [현재 수 인덱스 + 1]
                    int left = cur+1;
                    // 끝 지점은 마지막!
                    int right = A.length-1;
                    // 이분 탐색 진행
                    while(left <= right){
                        int mid = (left + right) / 2;
                        // 타겟을 찾은 경우 : 가능한 경우의 수 만큼 증가
                        if(A[mid] == target){
                            answer += countMap.get(A[cur]) * countMap.get(target);
                            break;
                        }else if(A[mid] < target) left = mid + 1;
                        else right = mid - 1;
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}