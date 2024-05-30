package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/3649
// - 이분 탐색
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            // 구멍의 너비
            int X = 0;
            // 더이상 입력이 없다면 종료!
            try{
                X = Integer.parseInt(in.readLine()) * 10000000;
            }catch (Exception e) {
                break;
            }
            // 레고 조각의 수
            int N = Integer.parseInt(in.readLine());
            // 레고 길이 배열 입력 : 이분 탐색을 위해 오름차순 정렬
            int[] sizeArr = new int[N];
            for(int i = 0; i < N; i++) sizeArr[i] = Integer.parseInt(in.readLine());
            Arrays.sort(sizeArr);

//            System.out.println(X);
//            System.out.println(Arrays.toString(sizeArr));
//            System.out.println(sizeArr[2]+sizeArr[3]);

            // l1, l2 초기화
            int l1 = 0, l2 = 0;
            int maxValue = -1;
            // l1을 정하고 X를 만들기 위한 l2가 있는지 확인
            for(int start = 0; start < N; start++){
                // l2 계산
                int target = X-sizeArr[start];
                // 정답을 갱신할 수 있는 경우
                // 이분 탐색을 통해 target이 존재하는지 확인
                if(target - sizeArr[start] > maxValue &&
                        binarySearch(start+1, N-1, target, sizeArr)){
                    l1 = sizeArr[start];
                    l2 = target;
                    maxValue = target - sizeArr[start];
                }
            }
            // 정답이 없는 경우 danger 출력
            if(l1 == 0 && l2 == 0) sb.append("danger\n");
            // 정답이 있는 경우 사용한 레고 조각 출력
            else sb.append("yes ").append(l1).append(" ").append(l2).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean binarySearch(int left, int right, int target, int[] sizeArr) {
        while(left <= right){
            int mid = (left + right) / 2;
            if(sizeArr[mid] == target) return true;
            else if(sizeArr[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}