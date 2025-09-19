package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9080
// -
public class _19_Solution_1 {
    static final int PM24 = 24 * 60;
    static final int PM10 = 22 * 60;
    static final int AM08 = 8 * 60;
    static final int AM03 = 3 * 60;
    static final int HOUR4 = 4 * 60;
    static final int HOUR = 1 * 60;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            String startTimeStr = st.nextToken();
            int curTime = getStringTimeToInt(startTimeStr);
            int useTime = Integer.parseInt(st.nextToken());

            int result = 0;
            while(useTime > 0){
                if(curTime < AM03){
                    if(useTime > HOUR4){
                        useTime -= AM08 - curTime;
                        curTime = AM08+1;
                        result += 5;
                    }else{
                        result += (useTime + 59) / 60;
                        useTime = 0;
                    }
                }else if(curTime >= PM10){
                    if(useTime > HOUR4){
                        useTime -= PM24 - curTime + AM08;
                        curTime = AM08+1;
                        result += 5;
                    }else{
                        result += (useTime + 59) / 60;
                        useTime = 0;
                    }
                }else{
                    useTime -= HOUR;
                    curTime += HOUR;
                    result++;
                }
            }
            sb.append(result * 1000).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
    private static int getStringTimeToInt(String startTimeStr) {
        String[] time = startTimeStr.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
