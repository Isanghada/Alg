package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1206
// - 브루트포스 : 소수점 셋째자리까지 주어지므로 최대 인원수는 1000명!
//                1명부터 차례로 확인하여 최소값 계산!
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문항수
        int N = Integer.parseInt(in.readLine());
        // 문항별 평균 점수
        int[] avg = new int[N];
        for(int i = 0; i < N; i++){
            StringTokenizer split = new StringTokenizer(in.readLine(), ".");
            StringBuilder s = new StringBuilder();
            s.append(split.nextToken()).append(split.nextToken());

            avg[i] = Integer.parseInt(s.toString());
        }

//        System.out.println(Arrays.toString(avg));
        // 각 인원수별로 가능한지 탐색! : 모두 가능하다면 인원수 반환!
        for(int num = 1; num <= 1000; num++){
            if(isPossible(num, avg)){
                sb.append(num);
                break;
            }
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean isPossible(int num, int[] avg) {
        for(int a: avg){
            int left = 0;
            int right= 10 * num;
            boolean flag = false;
            while(left <= right){
                int mid = (left + right) / 2;
                int currentAvg = (mid * 1000) / num;
                if(currentAvg == a){
                    flag = true;
                    break;
                }else if(currentAvg > a) right = mid -1;
                else left = mid + 1;
            }
            if(!flag) return false;
        }

        return true;
    }
}
