package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8983
// - 이분 탐색 : 각 동물마다 가장 가까운 사대의 위치를 이분 탐색으로 확인
//               거리가 사정거리 안이라면 정답 증가!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());   // 사대 개수
        int N = Integer.parseInt(st.nextToken());   // 동물의 수
        int L = Integer.parseInt(st.nextToken());   // 사정 거리

        // 사대 정보 : 오름차순 정렬
        int[] hunters = Arrays.stream(in.readLine().split(" " )).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(hunters);

        // 정답 초기화
        int answer = 0;
        // 모든 동물 확인
        while(N-- > 0){
            // 동물 정보
            st = new StringTokenizer(in.readLine());
            int[] point = new int[]{Integer.parseInt(st.nextToken()),   // x 좌표
                                    Integer.parseInt(st.nextToken())};  // y 좌표

            // 사대에서의 최소 거리
            int minDistance = 2_000_000_000;
            // 사대 정보로 이분 탐색
            int left = 0;
            int right = M-1;
            while(left <= right){
                // 좌표 계산
                int mid = (left + right) / 2;
                // 거리 계산
                int distance = Math.abs(point[0] - hunters[mid]) + point[1];
                // 최소 거리 갱신
                if(distance < minDistance) {
//                    idx = mid;
                    minDistance = distance;
                }
                // 사대와 동물의 좌표가 같다면 가장 가까운 경우 이므로 종료
                if(point[0] == hunters[mid]) break;
                // 동물이 사대보다 오른쪽일 경우 left 갱신
                else if(point[0] > hunters[mid]) left = mid+1;
                // 동물이 사대보다 왼쪽일 경우 right 갱신
                else right = mid-1;
            }
//            System.out.println(point[0]+", "+point[1]+", "+hunters[idx]+", "+minDistance);
            // 최소 거리가 사정 거리 이하라면 정답 증가
            if(minDistance <= L) answer++;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
