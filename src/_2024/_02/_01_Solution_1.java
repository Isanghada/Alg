package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14567
// 참고 : https://blog.naver.com/ndb796/221236874984
// - 위상 정렬 : 각 과목의 선수 과목을 체크하고, 이수할 수 있을 때마다 새로운 학기에 등록!
public class _01_Solution_1 {
    public static int[] POINT;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 과목의 수
        int M = Integer.parseInt(st.nextToken());   // 선수 조건의 수

        // 선수 과목 리스트
        List<Integer>[] prerequisiteList = new ArrayList[N+1];
        for(int subject = 1 ; subject <= N; subject++)
            prerequisiteList[subject] = new ArrayList<>();
        // 선수 과목 미이수 개수
        int[] prerequisiteCount = new int[N+1];
        // 선수 과목 등록
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // B 과목을 이수하기 위해 A 과목을 먼저 이수해야 한다.
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            prerequisiteList[A].add(B);
            prerequisiteCount[B]++;
        }

        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        // 이수 과목이 없는 경우 추가!
        for(int subject = 1; subject <= N; subject++){
            if(prerequisiteCount[subject] == 0) deque.offerLast(subject);
        }

        // 정답 배열 초기화
        int[] answerArr = new int[N+1];
        // - 이수 학기
        int answer = 1;

        // 덱이 빌때까지 반복
        while(!deque.isEmpty()){
            // 이수 가능한 과목의 수
            int size = deque.size();
            while(size-- > 0){
                // 현재 과목
                int cur = deque.pollFirst();
                // 이수 가능한 학기 입력
                answerArr[cur] = answer;
                // 연결된 과목의 미이수 개수 감소
                for(int child : prerequisiteList[cur]){
                    // 모든 선수 과목을 이수한 경우 덱에 추가
                    if(--prerequisiteCount[child] == 0){
                        deque.offerLast(child);
                    }
                }
            }
            // 학기 증가
            answer++;
        }
        
        // 과목별 이수 학기 출력
        for(int subject = 1; subject <= N; subject++){
            sb.append(answerArr[subject]).append(" ");
        }

        // 정답 입력
        System.out.println(sb);
    }
}
