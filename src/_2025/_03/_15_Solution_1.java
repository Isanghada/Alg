package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31264
// - 이분 탐색 : 이분 탐색을 통해 체크
//               1. 초기 사격 실력을 이분 탐색으로 선택
//               2. 이분 탐색을 통해 현재 사격 실력으로 가능한 최대 점수 선택!
//               3. 사격 횟수만큼 2번을 반복하여 점수 체크
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 표적 개수
        int M = Integer.parseInt(st.nextToken());   // 사격 횟수
        int A = Integer.parseInt(st.nextToken());   // 최소 사격 점수

        // 표적 정보
        int[] target = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 표적 정렬
        Arrays.sort(target);

        
        // 정답 초기화
        int answer = Integer.MAX_VALUE;

        // 초기 사격 실력 이분 탐색!
        int left = 1;
        int right = target[N-1];
        while(left <= right){
            int mid = (left + right) / 2;
            // 가능한 경우 : answer, right 갱신 => 초기 사격 실력 감소
            if(check(N, target, A, M, mid)){
                answer = mid;
                right = mid - 1;
            // 불가능한 경우 : left 갱신 => 초기 사격 실력 증가
            }else left = mid + 1;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean check(int n, int[] target, int goalScore, int m, int skill) {
        // 사격 점수 초기화
        int score = 0;

        // 표적 중 최소 점수를 맞출 수 있는 경우만 진행!
        if(target[0] <= skill){
            // 사격 진행
            while(m-- > 0){
                // 현재 skill로 획득 가능한 사격 점수
                int s = binarySearch(n, target, skill);

                skill += s; // 사격 실력 갱신
                score += s; // 사격 점수 갱신

                // 최소 사격 점수 이상이 될 경우 true 반환
                if(goalScore <= score) return true;
            }
        }
        // 불가능한 경우 false 반환
        return false;
    }

    private static int binarySearch(int n, int[] target, int skill) {
        int left = 0;
        int right = n-1;
        while(left <= right){
            int mid = (left + right) / 2;

            if(target[mid] == skill) return skill;
            else if(target[mid] > skill) right = mid -1;
            else left = mid + 1;
        }

        return target[right];
    }
}
