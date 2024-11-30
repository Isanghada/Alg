package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21939
// - 우선순위 큐 : 오름차순, 내림차순 기준 2개의 우선순이 큐로 구성!
//                 해결한 문제의 경우 삭제하며 recommend 반환
public class _30_Solution_1 {
    // 문제 클래스
    public static class Problem implements Comparable<Problem>{
        int num;    // 문제 번호
        int level;  // 난이도
        public Problem(int num, int level){
            this.num = num;
            this.level = level;
        }
        // 난이도 기준 내림차순, 문제 번호 기준 내림차순
        @Override
        public int compareTo(Problem p){
            int diff = p.level - this.level;
            return (diff == 0) ? p.num-this.num : diff;
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.num, this.level);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Problem){
                Problem p = (Problem) obj;
                if(this.num == p.num && this.level == p.level) return true;
            }
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문제 개수
        int N = Integer.parseInt(in.readLine());
        // 오름차순 정렬
        PriorityQueue<Problem> asc = new PriorityQueue<>();
        // 내림차순 정렬
        PriorityQueue<Problem> desc = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Problem> problemMap = new HashMap<>();
        // 해결한 문제
        Set<Problem> solved = new HashSet<>();

        // 초기 문제 입력
        StringTokenizer st = null;
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            Problem p = new Problem(Integer.parseInt(st.nextToken()),
                                        Integer.parseInt(st.nextToken())
                                    );
            problemMap.put(p.num, p);
            asc.offer(p);
            desc.offer(p);
        }

        // 명령 개수
        int M = Integer.parseInt(in.readLine());
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // 명령
            String command = st.nextToken();
            // 추천 작업
            if(command.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    while(solved.contains(asc.peek())) asc.poll();
                    sb.append(asc.peek().num);
                }
                else {
                    while(solved.contains(desc.peek())) desc.poll();
                    sb.append(desc.peek().num);
                }
                sb.append("\n");
            // 추가 작업
            }else if(command.equals("add")){
                Problem p = new Problem(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
                problemMap.put(p.num, p);
                asc.offer(p);
                desc.offer(p);
            // 해결 작업
            }else {
                int num = Integer.parseInt(st.nextToken());
                solved.add(problemMap.get(num));
                problemMap.remove(num);
            }
        }

        // 정답 반환
        System.out.println(sb);
    }
}