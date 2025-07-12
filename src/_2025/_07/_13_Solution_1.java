package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30827
// -
public class _13_Solution_1 {
    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Meeting o) {
            int diff = Integer.compare(this.end, o.end);
            return diff == 0 ? Integer.compare(o.start, this.start) : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Meeting[] meetings = new Meeting[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            meetings[i] = new Meeting(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(meetings);

        int[] time = new int[K];

        int answer = 0;
        for(Meeting meeting : meetings){
            int idx = 0;
            int curTime = -1;
            boolean flag = false;
            for(int i = 0; i < K; i++){
                if(time[i] < meeting.start){
                    if(curTime < time[i]){
                        idx = i;
                        curTime = time[i];
                        flag = true;
                    }
                }
            }

            if(flag){
                answer++;
                time[idx] = meeting.end;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
