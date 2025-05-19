package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30686
// - 브루트포스 : 문제를 푸는 순서는 최대 7! = 5040개 이므로 모든 경우 탐색!
public class _20_Solution_1 {
    static final int MAX = 100_000; // 최대값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 지식의 수
        int M = Integer.parseInt(st.nextToken());   // 문제의 수

        // 지식을 기억하는 시간 정보 입력
        int[] times = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) times[i] = Integer.parseInt(st.nextToken());

        // 문제 정보 입력
        List<Integer>[] problemList = new List[M+1];
        // 문제 풀이 순서
        int[] order = new int[M];
        for(int p = 1; p <= M; p++){
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());

            problemList[p] = new ArrayList<>();
            while(k-- > 0) problemList[p].add(Integer.parseInt(st.nextToken()));
            order[p-1] = p;
        }

        // 정답 초기화
        int answer = MAX;
        // nextPermutation을 활용해 모든 문제 풀이 순서 탐색!
        do{
            // 지식 학습 횟수 초기화
            int count = 0;
            // 지식 상태 초기화
            int[] knowledgeArr = new int[N+1];
            for(int problem : order){
                // 문제 풀이를 위한 지식 체크
                for(int knowledge : problemList[problem]){
                    // 지식을 기억하지 못하는 경우 학습!
                    if(knowledgeArr[knowledge] == 0){
                        count++;
                        knowledgeArr[knowledge] = times[knowledge];
                    }
                }
                // 하루에 1문제 이므로 모든 지식 시간 감소!!
                for(int i = 1; i <= N; i++) knowledgeArr[i] = Math.max(0, knowledgeArr[i]-1);
            }
            // answer 최소값 갱신
            answer = Math.min(answer, count);
        }while(nextPermutation(order));

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString().trim());
    }

    private static boolean nextPermutation(int[] order) {
        int N = order.length;

        int i = N - 1;
        while(i > 0 && order[i-1] > order[i]) i--;
        if(i == 0) return false;

        int j = N -1;
        while(j > i && order[i-1] > order[j]) j--;

        int temp = order[i - 1];
        order[i-1] = order[j];
        order[j] = temp;

        Arrays.sort(order, i, N);

        return true;
    }
}
