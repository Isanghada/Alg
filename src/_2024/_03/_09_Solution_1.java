package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/12731
// - 그리디 : 열차 출발이 빠른 것부터 차례로 출발시키며 확인
public class _09_Solution_1 {
    // 기차 클래스
    private static class Train implements Comparable<Train>{
        int start;  // 출발 시간
        int end;    // 도착 시간
        char type;  // 출발역
        public Train(int start, int end, char type){
            this.start = start;
            this.end = end;
            this.type = type;
        }
        // 출발 시간 기준 오름차순 정렬, 도착 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Train o){
            int diff = this.start - o.start;
            return (diff == 0 ? this.end - o.end : diff);
        }
        @Override
        public String toString(){
            return "[ start="+this.start+", end="+this.end+", type="+this.type+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;
        for(int t = 1; t <= T; t++){
            // 회차 시간
            int turnTime = Integer.parseInt(in.readLine());

            // 역에서 출발하는 열차의 개수
            st = new StringTokenizer(in.readLine());
            int[] count = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
            // 전체 열차 리스트
            List<Train> trainList = new ArrayList<>();
            // A역 시간표 추가
            trainList.addAll(getTrain(count[0], in, 'A'));
            // B역 시간표 추가
            trainList.addAll(getTrain(count[1], in, 'B'));
            // 전체 열차 리스트 정렬
            Collections.sort(trainList);

            // 정답 초기화
            int[] answerArr = new int[2];
            // A역에서 대기하는 열차 우선순위 큐
            PriorityQueue<Integer> aToB = new PriorityQueue<>();
            // B역에서 대기하는 열차 우선순위 큐
            PriorityQueue<Integer> bToA = new PriorityQueue<>();
            // 열차 차례로 출발 : 출발역에서 가능한 열차가 없는 경우 새로운 열차 할당
            for(Train cur : trainList){
                // A역에서 B로 이동하는 경우
                if(cur.type == 'A') answerArr[0] += isAddTrain(cur, aToB, bToA, turnTime);
                // B역에서 A로 이동하는 경우
                else answerArr[1] += isAddTrain(cur, bToA, aToB, turnTime);
            }


            sb.append("Case #").append(t).append(": ")
                    .append(answerArr[0]).append(" ")
                    .append(answerArr[1]).append("\n");
        }


        // 정답 출력
        System.out.println(sb);
    }
    // 열차 탐색 함수 : start 역에서 가능한 열차가 없다면 새로운 열차 추가
    private static int isAddTrain(Train cur, PriorityQueue<Integer> start, PriorityQueue<Integer> end, int turnTime) {
//        System.out.println(cur);
//        System.out.println(start);
        // 도착역에 새로운 기차 정보 추가
        end.offer(cur.end+turnTime);
        // 출발역이 비었거나 가능한 기차가 없는 경우 : 열차 추가
        if(start.isEmpty() || (cur.start < start.peek())) {
            return 1;
        }
        // 출발역에 가능한 기차가 있는 경우 : 열차 활용
        else {
            start.poll();
            return 0;
        }
    }
    // 기차 입력 함수
    private static Collection<? extends Train> getTrain(int count, BufferedReader in, char type) throws Exception {
        List<Train> train = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < count; i++){
            st = new StringTokenizer(in.readLine());
            train.add(new Train(
                    getTime(st.nextToken()),
                    getTime(st.nextToken()),
                    type
            ));
        }

        return train;
    }
    // 시간 반환 함수 : "HH:MM"으로 구성된 시간을 정수로 반환
    private static int getTime(String time) {
        int result = 0;
        String[] split = time.split(":");
        result += Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);

        return result;
    }
}
