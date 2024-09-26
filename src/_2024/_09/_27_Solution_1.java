package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/3151
// - 투 포인터 : 하나의 값을 선택 후 나머지 2개의 값을 투 포인터를 통해 선택!
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 학생 수
        int N = Integer.parseInt(in.readLine());
        // 학생 정보
        int[] data = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        // 오름차순 정렬
        Arrays.sort(data);

        // 정답 초기화
        long answer = 0;

        // 모든 학생을 기준으로 탐색!
        for(int i = 0; i < N; i++){
            // 현재 기준 학생
            int cur = data[i];
            // 현재 학생 기준 오른쪽 범위만 탐색
            int left = i+1;
            int right = N-1;
            
            while(left < right){
                // 합 계산
                int sum = cur + data[left] + data[right];
                // 코딩 실력 합이 0인 경우
                if(sum == 0){
                    // left, right의 값이 동일한 경우 : 조합을 통해 팀의 수 계산
                    if(data[left] == data[right]){
                        int count = right - left + 1;
                        answer += Combination2(count);
                        break;
                    // left, right의 값이 다른 경우 : 각 값의 수를 구하여 팀의 수 계산
                    }else{
                        // left 값의 수
                        int leftCount = 1;
                        while(data[left] == data[left+1]){
                            leftCount++;
                            left++;
                        }

                        // right 값의 수
                        int rightCount = 1;
                        while(data[right] == data[right-1]){
                            rightCount++;
                            right--;
                        }
                        // left의 수 * right의 수
                        answer += leftCount * rightCount;
                        left++;
                        right--;
                    }
                // 0보다 큰 경우 right 감소
                }else if(sum > 0) right--;
                // 0보다 작은 경우 left 증가
                else left++;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    // size의 개수 중 2개를 선택하는 조합
    private static long Combination2(int size) {
        return size * (size-1) / 2;
    }
}
