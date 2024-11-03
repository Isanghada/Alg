package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1477
// - 이분 탐색 : 휴게소가 존재하지 않는 최대 범위를 기준으로 이분 탐색 진행!
//               해당 값을 기준으로 가능한 새로운 휴게소의 개수 계산
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 현재 휴게소의 개수
        int M = Integer.parseInt(st.nextToken());   // 새로운 휴게소의 개수
        int L = Integer.parseInt(st.nextToken());   // 전체 길이
        
        // 휴게소 정보
        List<Integer> restingPlaces = new ArrayList<>();
        restingPlaces.add(0);
        restingPlaces.add(L);
        if(N > 0){
            st = new StringTokenizer(in.readLine());
            while(st.hasMoreTokens()) restingPlaces.add(Integer.parseInt(st.nextToken()));
        }

        // 휴게소 정보 정렬 : 오름차순
        Collections.sort(restingPlaces);

        // 이분 탐색
        int left = 1;
        int right = L;
        while(left <= right){
            // 휴게소간 최대 거리!
            int mid = (left + right) / 2;
            int count = 0;

            // mid 길이 만큼의 범위로 지을 수 있는 휴게소 개수 계산
            for(int n = 0; n <= N; n++){
                count += (restingPlaces.get(n+1) - restingPlaces.get(n) - 1) / mid;
            }

            // M 초과인 경우 : 휴게소가 많이 만들어졌으므로 거리를 늘려 정상화
            if(count > M) left = mid + 1;
            // M 이하인 경우 : 휴게소가 적게 만들어졌으므로 최대 거리를 줄여 정상화
            else right = mid - 1;
        }

        // 정답 반환
        sb.append(left);
        System.out.println(sb);
    }
}
