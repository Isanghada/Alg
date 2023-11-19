package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14222
// - 배열에서 작은 값부터 확인하며 중복된 경우 K씩 증가하며 체크!
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());   // 배열의 크기
        int K = Integer.parseInt(st.nextToken());   // 증가 변수

        // 초기 배열 입력
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 배열 오름 차순 정렬
        Arrays.sort(arr);

        // 숫자 확인용 배열
        boolean[] check = new boolean[N+1];

        // 모든 배열
        for(int num : arr){
            // N 이하의 정수인 경우
            if(num <= N){
                // 이미 포함된 경우
                if(check[num]) {
                    // K만큼 증가시키며 체크
                    while(num <= N){
                        if(check[num]) num += K;
                        // 체크되지 않은 경우 체크!
                        else{
                            check[num] = true;
                            break;
                        }
                    }
                }
                // 포함되지 않은 경우 : 체크
                else check[num] = true;
            // N을 초과할 경우 : 종료
            }else break;
        }

        // 정답 출력
        // flag 초기화
        boolean flag = true;
        // 1~N 모든 정수가 나왔다면 flag는 true, 아니라면 flase로 변경
        for(int i = 1; i <= N; i++){
            if(!check[i]) {
                flag = false;
                break;
            }
        }
        // flag에 따라 1, 0 반환
        sb.append(flag ? 1 : 0);
        System.out.println(sb);
    }
}
