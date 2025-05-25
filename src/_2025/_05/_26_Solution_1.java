package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31997
// - 누적합 : 회의 참여하고 있는 사람을 기준으로 시간별 차례로 계산
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사람 수
        long M = Long.parseLong(st.nextToken());    // 관계 수
        int T = Integer.parseInt(st.nextToken());   // 희의 종료 시간

        List<Integer>[] inList = new List[T+1];     // 회의 참여
        List<Integer>[] outList = new List[T+1];    // 회의 퇴장
        for(int t = 0; t <= T; t++){
            inList[t] = new ArrayList<>();
            outList[t] = new ArrayList<>();
        }

        List<Integer>[] friendList = new List[N+1]; // 친구 관계 리스트!
        for(int n = 0; n <= N; n++) friendList[n] = new ArrayList<>();

        // 회의 참여 정보 입력
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inList[start].add(n);
            outList[end].add(n);
        }

        // 친구 관계 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friendList[a].add(b);
            friendList[b].add(a);
        }

        // 현재 회의에 참여중인 친구 관계의 수!
        int countOfRelation = 0;
        boolean[] joined = new boolean[N+1];
        for(int t = 0; t < T; t++){
            // 회의 종료하는 경우
            for(int end : outList[t]){
                joined[end] = false;
                for(int friend : friendList[end]){
                    if(joined[friend]) countOfRelation--;
                }
            }
            // 회의 참여하는 경우
            for(int start : inList[t]){
                joined[start] = true;
                for(int friend : friendList[start]){
                    if(joined[friend]) countOfRelation++;
                }
            }
            sb.append(countOfRelation).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
