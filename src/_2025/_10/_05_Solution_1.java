package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23349
// -
public class _05_Solution_1 {
    static final int MAX = 50_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        Set<String> names = new HashSet<>();
        Map<String, int[]> map = new TreeMap<>();

        StringTokenizer st = null;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            String place = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            if(names.contains(name)) continue;

            names.add(name);
            if(!map.containsKey(place)) map.put(place, new int[MAX+5]);
            map.get(place)[start]++;
            map.get(place)[end+1]--;
        }

        for(String place : map.keySet()){
            for(int t = 1; t <= MAX; t++){
                map.get(place)[t] += map.get(place)[t-1];
            }
        }

        String placeAnswer = "";
        int startAnswer = 0;
        int endAnswer = 0;
        int answer = 0;

        for(Map.Entry<String, int[]> entry : map.entrySet()){
            String place = entry.getKey();
            int[] timeTable = entry.getValue();

            int start = 0;
            int end = 0;
            int count = 0;
            for(int t = 1; t <= MAX; t++) {
                if(timeTable[t] <= timeTable[t-1]) count = Math.max(timeTable[t], count);
            }

            if(answer <= count){
                List<int[]> list = new ArrayList<>();
                for(int t = 1; t <= MAX; t++){
                    if(timeTable[t] >= count){
                        start = t;
                        while(t <= MAX && timeTable[t] >= count) t++;
                        t--;
                        end = t;

                        list.add(new int[]{start, end});
                    }
                }
                Collections.sort(list, (o1, o2) -> {
                    int time1 = o1[1] - o1[0];
                    int time2 = o2[1] - o2[0];

                    int diff = Integer.compare(time2, time1);
                    return (diff == 0) ? Integer.compare(o1[0], o2[0]) : diff;
                });

                start = list.get(0)[0];
                end = list.get(0)[1];

                if(answer == count){
                    int answerTime = end - start;
                    int curTime = endAnswer - startAnswer;

                    if(curTime <= answerTime) break;
                }

                placeAnswer = place;
                startAnswer = list.get(0)[0];
                endAnswer = list.get(0)[1];
                answer = count;
            }
        }

        // 정답 반환
        sb.append(placeAnswer).append(" ").append(startAnswer).append(" ").append(endAnswer);
        System.out.println(sb);
    }
}
