package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15553
// - 그리디 : 친구가 방문하는 간격의 시간을 최소로 줄여야한다!
//            각 친구별로 방문하는데 간격을 계산하여 간격이 좁은 경우부터 전구를 켠 채로 유지!
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 친구의 수
        int K = Integer.parseInt(st.nextToken());   // 성냥의 수

        // 친구 방문 정보!
        int[] T = new int[N];
        for(int i = 0; i < N;i ++) T[i] = Integer.parseInt(in.readLine());

        // 친구간 간격의 시간 계산!
        // - 붙어있는 경우는 추가하지 않음!
        List<Integer> interval = new ArrayList<>();
        // - 첫 친구가 나가는 시간!
        int end = T[0]+1;
        for(int i = 1; i < N; i++){
            // 나가는 시간과 새로운 친구가 오는 시간이 다른 경우!
            // - 리스트에 간격 정보 추가
            if(end != T[i])interval.add(T[i] - end);
            // 친구가 나가는 시간 갱신!
            end = T[i]+1;
        }

        // 간격 정보 정렬
        Collections.sort(interval);

        // 전구를 끄지 않고 대기해야하는 구간의 수!
        // - 첫 친구에서 성냥을 무조건 사용해야하므로 나머지 구간에서 사용 가능한 성냥의 수는 : (K-1)
        final int limit = interval.size() - (K - 1);
        int answer = N;
        for(int i = 0; i < limit; i++) answer += interval.get(i);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
