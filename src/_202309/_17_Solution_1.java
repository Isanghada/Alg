package _202309;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/60062
// - 완전 탐색! : 모든 경우를 탐색하여 최소값 계산
// - 취약 지점은 원형으로 시계 반향으로 탐색
// - 친구 투입 순서는 순열을 통해 계산
public class _17_Solution_1 {
    public int solution(int n, int[] weak, int[] dist) {
        // - 정답 초기화
        int answer = -1;

        // 순환 취약점, 투입 순서(순열)
        List<int[]> rotateWeak, permutationDist;

        // 취약점을 순환하며 시작점 추가
        rotateWeak = new ArrayList<>();
        for(int i = 0; i < weak.length; i++){
            int[] w = new int[weak.length];

            // 각 지점을 순서대로 시작점으로 사용
            for(int idx = 0; idx < weak.length; idx++) {
                w[idx] = weak[idx];
                // 현재 시작 지점보다 앞 시작 지점이 작을 경우!
                // - 순환형 구조이므로 n만큼 증가!
                if(idx > 0 && w[idx] < w[idx-1]) w[idx] += n;
            }
            // 리스트에 추가
            rotateWeak.add(w);
            
            // 순환
            int temp = weak[0];
            for(int idx = 0; idx < weak.length - 1; idx++) weak[idx] = weak[idx+1];
            weak[weak.length-1] = temp;
        }

//        for(int[] d : rotateWeak){
//            System.out.println(Arrays.toString(d));
//        }

        // Next Permutation을 사용하기 위해 정렬(오름차순)
        Arrays.sort(dist);
        permutationDist = new ArrayList<>();
        do{
            // 모든 순열 상태를 리스트에 추가
            int[] d = new int[dist.length];
            for(int idx = 0; idx < dist.length; idx++) d[idx] = dist[idx];
            permutationDist.add(d);
        // nextPermutation으로 다음 상태 탐색
        }while(permutation(dist));

//        for(int[] d : rotateDist){
//            System.out.println(Arrays.toString(d));
//        }

        // 모든 취약점
        for(int[] w : rotateWeak){
            // 모든 투입 순서
            for(int[] d : permutationDist){
                // 방문 체크
                boolean[] visit = new boolean[w.length];

                // 탐색 종료 지점
                int end = 0;
                // 탐색한 취약 지점 인덱스
                int idx = 0;
                
                for(int i = 0; i < d.length; i++){
                    // 취약 지점 위치에서 이동 거리를 더해 탐색 종료 지점 계산
                    end = w[idx] + d[i];
                    // idx가 취약 지점 인덱스보다 작고 end가 취약 지점 위치보다 클 경우
                    while(idx < w.length && end >= w[idx]){
                        // 방문 표시
                        visit[idx] = true;
                        // 인덱스 증가
                        idx++;
                    }

                    // 모든 지점을 점검했다면
                    if(idx == w.length){
                        // -1인 경우 현재 값으로 초기화
                        if(answer == -1) answer = i+1;
                        // 최소값으로 변경
                        else answer = Math.min(answer, i+1);
                        break;
                    }
                }
            }
        }

        return answer;
    }

    // 다음 순열을 구하는 함수
    // - input : 순열을 탐색할 배열
    public boolean permutation(int[] input){
        // 길이
        int n = input.length;

        // 앞의 값보다 큰 경우 탐색
        int i = n - 1;
        while(i > 0 && input[i -1] >= input[i]) i--;

        // 마지막 상태인 경우 false 반환
        if(i == 0) return false;

        // 값을 변경할 인덱스 탐색
        // -  i-1보다 큰 첫 번째 값이 나올 때까지 탐색
        int j = n - 1;
        while(input[i - 1] >= input[j]) j--;

        // i-1, j 값 교환
        swap(input, i-1, j);

        // i ~ n-1 각 위치 교환
        int k = n -1;
        while(i < k) swap(input, i++, k--);

        // true 반환
        return true;
    }

    public void swap(int[] input, int a, int b){
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
