package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32358
// - 정렬, 이분탐색, 투 포인터 : 1번 쿼리인 경우 리스트에 좌표를 추가하고, 2번 쿼리인 경우 이동하는 거리 계산!
//                               1. 리스트를 정렬하여 이분 탐색 준비
//                               2. 이분 탐색을 통해 현재 좌표에서 가장 가까운 좌표 탐색
//                               3. 탐색한 좌표를 기준으로 가까운 좌표로 차례로 이동하며 이동 거리 계산
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        long answer = 0;
        int point = 0;
        List<Integer> pointList = new ArrayList<>();
        StringTokenizer st = null;
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type == 1){
                int x = Integer.parseInt(st.nextToken());
                pointList.add(x);
            }else{
                if(pointList.size() > 0){
                    Collections.sort(pointList);
                    // System.out.println(pointList+"==========");
                    int leftIdx = binarySearch(pointList, point, pointList.size());
                    int rightIdx = leftIdx + 1;

                    while(leftIdx >= 0 && rightIdx < pointList.size()){
                        int leftDiff = Math.abs(pointList.get(leftIdx) - point);
                        int rightDiff = Math.abs(pointList.get(rightIdx) - point);

                        if(leftDiff <= rightDiff){
                            point = pointList.get(leftIdx--);
                            answer += leftDiff;
                        }else{
                            point = pointList.get(rightIdx++);
                            answer += rightDiff;
                        }
                    }

                    while(leftIdx >= 0){
                        answer += Math.abs(pointList.get(leftIdx) - point);
                        point = pointList.get(leftIdx--);
                    }

                    while(rightIdx < pointList.size()){
                        answer += Math.abs(pointList.get(rightIdx) - point);
                        point = pointList.get(rightIdx++);
                    }

                    pointList.clear();
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static int binarySearch(List<Integer> pointList, int point, int size) {
        int result = 0;
        int min = Integer.MAX_VALUE;

        int left = 0;
        int right = size - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int curPoint = pointList.get(mid);

            int diff = Math.abs(curPoint - point);

            if(diff < min){
                result = mid;
                min = diff;
            }else if(diff == min){
                if(mid < result) result = mid;
            }

            if(curPoint <= point) left = mid + 1;
            else right = mid - 1;
        }
        return result;
    }
}

