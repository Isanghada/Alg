package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6068
// - 늦게 끝내도 되는 일부터 역순으로 계산!
public class _26_Solution_1 {
    // 일 정보를 담을 클래스
    private static class Work implements Comparable<Work>{
        int time;   // 일 하는데 걸리는 시간
        int limit;  // 일 최대 마감 시간
        public Work(int time, int limit){
            this.time = time;
            this.limit = limit;
        }
        // limit 기준 내림차순 정렬
        @Override
        public int compareTo(Work o) {
            return o.limit - this.limit;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 일의 개수
        int N = Integer.parseInt(in.readLine());
        // 일 정보 입력
        Work[] workArr = new Work[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            workArr[i] = new Work(Integer.parseInt(st.nextToken())
                                , Integer.parseInt(st.nextToken()));
        }
        // limit 기준 내림차순 정렬
        Arrays.sort(workArr);

        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 마감이 늦은 일부터 처리하며 계산
        for(Work w : workArr){
//            System.out.println(w.time+", "+w.limit);
            // 현재 정답과 일의 마감 시간 중 최소값 선택
            answer = Math.min(answer, w.limit);
            // 일 하는데 걸리는 시간만큼 감소
            answer -= w.time;
        }

        // 정답이 0 미만이면 일을 끝낼 수 없으므로 -1 반환
        // 0이상이라면 해당 값 반환
        sb.append(answer >= 0 ? answer : -1);
        System.out.println(sb);
    }
}
