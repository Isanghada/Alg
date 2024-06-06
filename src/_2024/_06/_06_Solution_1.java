package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/12980
// - 백트래킹 : 조합할 수 있는 모든 수열을 탐색
public class _06_Solution_1 {
    public static int N, S;
    public static int[] ARR;
    public static boolean[] isUsed;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 수열 크기
        S = Integer.parseInt(st.nextToken());   // 계산한 점수

        // 최대 점수
        int MAX = N*(N-1)/2;
        // 수열 정보
        ARR = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 점수가 불가능한 경우 0 반환
        if(MAX < S) sb.append(0);
        else {
            // 사용한 숫자 배열
            isUsed = new boolean[N+1];
            // 사용하지 않은 숫자 리스트
            List<Integer> noneList = new ArrayList<>();
            // 지워진 인덱스 리스트
            List<Integer> noneIndex = new ArrayList<>();

            // 숫자 사용 및 지워진 인덱스 체크
            for(int i = 0; i < N; i++) {
                isUsed[ARR[i]] = true;
                if(ARR[i] == 0) noneIndex.add(i);
            }
            // 사용하지 않은 숫자 리스트 생성
            for(int num = 1; num <= N; num++) if(!isUsed[num]) noneList.add(num);

            // 정답 초기화
            int answer = 0;
            // nextPermutation을 통해 가능한 모든 순열 탐색
            do{
                for(int i = 0; i < noneIndex.size(); i++) ARR[noneIndex.get(i)] = noneList.get(i);

//                System.out.println(Arrays.toString(ARR));

                // 점수 계산
                int count = 0;
                for(int i = 0; i < N; i++){
                    for(int j = i+1; j < N; j++){
                        if(ARR[i] < ARR[j]) count++;
                    }
                }
//                System.out.println(count+"------------");

                // S와 동일한 경우 answer 증가
                if(count == S) answer++;
            }while(nextPermutation(noneList));

            sb.append(answer);
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static boolean nextPermutation(List<Integer> input) {
        int LEN = input.size();
        if(LEN == 0) return false;

        int i = LEN - 1;
        while(i > 0 && input.get(i-1) >= input.get(i)) i--;

        if(i == 0) return false;
        int j = LEN - 1;
        while(input.get(i-1) >= input.get(j)) j--;

        Collections.swap(input, i-1, j);

        int k = LEN - 1;
        while(i < k) Collections.swap(input, i++, k--);

        return true;
    }
}
