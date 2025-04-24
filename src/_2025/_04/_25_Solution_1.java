package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

// https://www.acmicpc.net/problem/5884
// - 브루트포스 : 가장 많은 소를 감시할 수 있는 카메라를 3개 선택하여 체크
public class _25_Solution_1 {
    // 소 클래스
    static class Cow{
        int x;  // x 좌표
        int y;  // y 좌표
        public Cow(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // 감시 카메라 클래스
    static class Cctv implements Comparable<Cctv>{
        int type;   // 수직, 수평 변수(X, Y)
        int point;  // 좌표
        int count;  // 관찰할 수 있는 소의 수
        public Cctv(int type, int point, int count){
            this.type = type;
            this.point = point;
            this.count = count;
        }
        // count 기준 내림차순
        @Override
        public int compareTo(Cctv o){
            return o.count - this.count;
        }
        @Override
        public String toString(){
            return "[ type="+this.type+", point="+this.point+", count="+this.count+" ]";
        }
    }
    // COUNT : 최대 감시 카메라의 수
    // X : X 좌표
    // Y : Y 좌표
    static final int COUNT = 3, X = 0, Y = 1;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 소의 수
        int N = Integer.parseInt(in.readLine());

        Cow[] cows = new Cow[N];
        Map<Integer, Integer> xMap = new HashMap<>();   // x좌표 기준 소의 수
        Map<Integer, Integer> yMap = new HashMap<>();   // y좌표 기준 소의 수

        StringTokenizer st = null;
        // 소 정보 입력
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x, y);

            if(!xMap.containsKey(x)) xMap.put(x, 0);
            xMap.put(x, xMap.get(x)+1);

            if(!yMap.containsKey(y)) yMap.put(y, 0);
            yMap.put(y, yMap.get(y)+1);
        }

        PriorityQueue<Cctv> pq = new PriorityQueue<>();
        // pq에 xMap, yMap의 정보 추가
        for(Entry<Integer, Integer> entry : xMap.entrySet()) pq.offer(new Cctv(X, entry.getKey(), entry.getValue()));
        for(Entry<Integer, Integer> entry : yMap.entrySet()) pq.offer(new Cctv(Y, entry.getKey(), entry.getValue()));

        // 감시 카메라 리스트 추가
        List<Cctv> removeList = new ArrayList<>();
        for(int i = 0; i < COUNT && !pq.isEmpty(); i++){
            Cctv remove = pq.poll();
            removeList.add(remove);
        }

        // System.out.println(removeList);

        // 모든 소 관찰 여부
        boolean answer = true;
        for(int i = 0; i < N && answer; i++){
            // answer 변경
            answer = false;
            // 현재 소가 감시 카메라에 관찰된다면 answer -> true로 변경
            for(Cctv remove : removeList){
                if((remove.type == X && remove.point == cows[i].x) ||
                        (remove.type == Y && remove.point == cows[i].y)
                ) answer = true;
            }
        }

        // 정답 출력
        // - 관찰 가능 1, 관찰 불가능 0
        sb.append(answer ? 1 : 0);
        System.out.println(sb);
    }
}