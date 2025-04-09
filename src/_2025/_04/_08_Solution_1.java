package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4055
// - 그리디 : 파티의 시간을 분으로 표현하고, (끝 시간, 시작 시간)을 기준으로 오름차순 정렬
//            각 파티에 참석할 수 있는지 체크
public class _08_Solution_1{
    // 파티 클래스 : 시간은 분으로 표현
    // - 30분 씩 더하기 위함!
    static class Party implements Comparable<Party>{
        int start;  // 시작 시간
        int end;    // 끝 시간
        public Party(int start, int end){
            this.start = start;
            this.end = end;
        }
        // 1. 끝 시간 기준 오름차순 정렬
        // 2. 시작 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Party p){
            if(this.end == p.end) return this.start - p.start;
            return this.end - p.end;
        }
        @Override
        public String toString(){
            return "[ start="+this.start+", end="+this.end+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        int T = 1;  // 테스트 케이스 번호
        while(true){
            // 파티 개수
            int p = Integer.parseInt(in.readLine());
            // 종료 조건 : p가 0인 경우
            if(p == 0) break;

            // 파티 정보 입력 : 24시간을 30분 단위로 표현하기 위해 (시간 * 2)
            //                  - 30분은 1, 1시간은 2
            Party[] parties = new Party[p];
            boolean[] visited = new boolean[50];
            for(int i = 0; i < p; i++){
                st = new StringTokenizer(in.readLine());
                parties[i] = new Party(
                        Integer.parseInt(st.nextToken()) * 2,
                        Integer.parseInt(st.nextToken()) * 2
                );
            }

            // 파티 정보 정렬
            Arrays.sort(parties);
//            for(Party party : parties) System.out.println(party);
//            System.out.println("----");

            int answer = 0;     // 방문 가능한 파티 개수
            // 모든 파티 체크
            for(int i = 0; i < p; i++){
                // 현재 파티 정보
                int start = parties[i].start;
                int end = parties[i].end;

                // 파티에 방문할 수 있는지 체크
                for(int time = start; time < end; time++){
                    // 다른 파티에 방문한 경우 패스
                    if(visited[time]) continue;
                    visited[time] = true;
                    answer++;
                    break;
                }
            }

            // 정답 출력
            sb.append(String.format("On day %d Emma can attend as many as %d parties.\n", T++, answer));
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }
}
