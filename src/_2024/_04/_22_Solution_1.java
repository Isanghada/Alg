package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23740
// - 정렬 : 버스 노선을 시작 지점 기준으로 정렬하여 구간 통합 진행
public class _22_Solution_1 {
    // 버스 클래스
    private static class Bus implements Comparable<Bus>{
        private int s;  // 시작 지점
        private int e;  // 도착 지점
        private int c;  // 비용
        public Bus(int s, int e, int c){
            this.s = s;
            this.e = e;
            this.c = c;
        }
        public Bus(Bus bus){
            this(bus.s, bus.e, bus.c);
        }
        // 시작 지점 기준 오름차순 정렬
        @Override
        public int compareTo(Bus o){
            return this.s - o.s;
        }
        public void setS(int s){ this.s = s; };
        public void setE(int e){ this.e = e; };
        public void setC(int c){ this.c = c; };
        public int getS(){ return this.s; }
        public int getE(){ return this.e; }
        public int getC(){ return this.c; }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 버스 노선의 수
        int N = Integer.parseInt(in.readLine());
        // 버스 노선 배열 초기화
        Bus[] busArr =new Bus[N];

        // 버스 노선 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            busArr[i] = new Bus(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 시작 지점 기준 오름차순 정렬
        Arrays.sort(busArr);

        // 통합 버스 노선 리스트
        List<Bus> busList = new ArrayList<>();
        // 초기값으로 첫 노선 추가!
        busList.add(new Bus(busArr[0]));
        // 첫 노선부터 마지막 노선까지 차례로 통합 진행
        for(int i = 1; i < N; i++){
            // 리스트에 포함된 마지막 버스 노선
            Bus cur = busList.get(busList.size()-1);
        
            // i번째 버스와 cur이 겹치는 경우
            if(cur.e >= busArr[i].s){
                // 도착 지점 갱신
                cur.setE(Math.max(cur.e, busArr[i].e));
                // 비용 갱신
                cur.setC(Math.min(cur.c, busArr[i].c));
            // i번째 버스와 cur이 겹치지 않는 경우 : 새로운 버스 노선 리스트에 추가
            }else busList.add(new Bus(busArr[i]));
        }

        // 버스 노선 개수 반환
        sb.append(busList.size()).append("\n");
        // 버스 노선 정보 반환
        for(int i = 0; i < busList.size(); i++){
            sb.append(busList.get(i).getS()).append(" ")
                    .append(busList.get(i).getE()).append(" ")
                    .append(busList.get(i).getC()).append("\n");
        }
        System.out.println(sb);
    }
}
