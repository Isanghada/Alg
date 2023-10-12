package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1114
// - 이분 탐색을 활용해 해결
// - 최대 길이(mid) 기준으로 이분 탐색 진행
// - mid로 자를 수 없다면 right 값을 줄이고 mid로 자를 수 있다면 left 값을 늘림.
// - 30 * 10000 => 시간복잡도는 최대가 300000
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력
        int L = Integer.parseInt(st.nextToken()); // 통나무 길이
        int K = Integer.parseInt(st.nextToken()); // 자를 수 있는 위치의 수
        int C = Integer.parseInt(st.nextToken()); // 자를 수 있는 횟수

        // 자를 수 있는 위치 입력
        st = new StringTokenizer(in.readLine());
        // 위치 리스트 초기화
        List<Integer> positionList = new ArrayList<>();
        // 첫 위치 추가
        positionList.add(0);
        // 입력되는 값을 차례로 추가
        while(st.hasMoreTokens()) positionList.add(Integer.parseInt(st.nextToken()));
        // 마지막 위치가 없다면 추가
        if(positionList.get(positionList.size() - 1) != L) positionList.add(L);
        // 오름차순 정렬
        Collections.sort(positionList);

        // 가장 길이가 긴 값 초기화
        int answerLongest = L;
        // 첫 자르는 위치 초기화
        int answerFirst = 0;
        // 이분 탐색을 위해 left, right 설정 : 통나무 최대 길이
        int left = 0;
        int right = L;
        // 이분 탐색 진행
        while(left <= right){
            // 통나무 최대 길이 설정
            int mid = (left+ right) / 2;
            // 통나무를 자른 횟수
            int cutCnt = 0;
            // 잘라낸 통나무의 길이
            int diff = 0;
            // 자를 수 있는지 여부
            boolean flag = false;
            // 가장 뒤에서 부터 확인
            for(int i = positionList.size() - 2; i >= 0; i--){
                // i, i+1 위치의 차 만큼 길이 증가
                diff += positionList.get(i+1) - positionList.get(i);
                // mid보다 클 경우!
                if(diff > mid){
                    // 통나무 길이 새로 설정 => i-1에서 통나무를 자름
                    diff = positionList.get(i+1) - positionList.get(i);
                    // 자른 횟수 증가
                    cutCnt++;
                    // 새로운 통나무 길이가 mid를 넘거나 자른 횟수가 제한을 넘을 경우
                    // - flag 변경 후 종료
                    if(diff > mid || cutCnt > C){
                        flag = true;
                        break;
                    }
                }
            }
            // 자를 수 없는 경우 : 최대 통나무 길이 증가 (left 증가)
            if(flag){
                left = mid + 1;
            // 자를 수 있는 경우 : 최대 통나무 길이 감소 (right 감소)
            }else{
                // 최대 길이 mid로 변경
                answerLongest = mid;
                // 첫 위치 변경
                // - 자를 수 있는 횟수가 남은 경우 : 첫번째 위치로 설정
                // - 모두 자른 경우 : 남은 통나무 길이로 설정
                answerFirst = cutCnt < C ? positionList.get(1) : diff;
                // right 변경
                right = mid - 1;
            }
        }

        // 정답 반환
        sb.append(answerLongest).append(" ").append(answerFirst);
        System.out.println(sb);
    }
}
