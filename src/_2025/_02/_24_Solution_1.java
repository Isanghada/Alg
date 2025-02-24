package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19640
// - 우선순위 큐 : 1. 덱을 활용해 각 줄에서 대기하는 사원 입력!
//                 2. 각 줄의 선두로 우선순위 큐 구성
//                 3. 화장실을 이용하는 선두의 줄에서 새로운 사람 추가!
//                 4. 화장실을 이용할 번호가 데카일 경우 종료!
public class _24_Solution_1 {
    // 사원 클래스
    static class Employee implements Comparable<Employee>{
        int num;    // 대기한 번호!
        int line;   // 줄 번호
        int d;      // 근무 일수
        int h;      // 급한 정도
        public Employee(int num, int line, int d, int h){
            this.num = num;
            this.line = line;
            this.d = d;
            this.h = h;
        }
        // 정렬
        // 1. 근무 일수 기준 내림차순
        // 2. 급한 정도 기준 내림차순
        // 3. 줄 번호 기준 오름차순
        @Override
        public int compareTo(Employee e){
            if(this.d == e.d){
                if(this.h == e.h) return this.line - e.line;
                return e.h - this.h;
            }
            return e.d - this.d;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ num=").append(this.num)
                    .append(", line=").append(this.line)
                    .append(", d=").append(this.d)
                    .append(", h=").append(this.h)
                    .append(" ]");
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사원의 수
        int M = Integer.parseInt(st.nextToken());   // 줄 개수
        int K = Integer.parseInt(st.nextToken());   // 데카 앞의 사람 수

        // 덱 초기화
        Deque<Employee>[] deques = new Deque[M];
        for(int m = 0 ; m < M; m++) deques[m] = new LinkedList<>();

        // 대기하는 순서대로 줄에 추가!
        for(int num = 0; num < N; num++){
            st = new StringTokenizer(in.readLine());
            Employee employee = new Employee(
                    num,
                    num % M,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            deques[employee.line].offerLast(employee);
        }

        // 우선순위 큐 구성 : 각 줄의 선두
        PriorityQueue<Employee> pq = new PriorityQueue<>();
        for(int m = 0; m < M; m++){
            if(deques[m].isEmpty()) continue;
            pq.offer(deques[m].pollFirst());
        }

        // 정답 초기화
        int answer = 0;
        // 데카가 화장실을 이용할 순서인 경우 반복 종료
        while(pq.peek().num != K){
            // System.out.println(pq.peek()+"----"+K);
            // 화장실을 이용할 사원 반환
            Employee employee = pq.poll();
            answer++;

            // 화장실을 이용하는 사원의 줄에서 새로운 사원 추가
            if(!deques[employee.line].isEmpty()) pq.offer(deques[employee.line].pollFirst());
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
