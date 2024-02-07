package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1360
// - 구현 : 각 명령에 대한 결과를 리스트에 저장
public class _07_Solution_1 {
    // 텍스트 클래스
    public static class Text{
        String value;   // 입력된 테스트
        int time;       // 시간
        public Text(String value, int time){
            this.value = value;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 명령의 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        // 명령 결과 리스트
        List<Text> list = new ArrayList<>();
        while(N-- > 0){
            // 명령 입력
            st = new StringTokenizer(in.readLine());

            // 문자 추가 명령
            if(st.nextToken().equals("type")){
                // 새로운 텍스트
                String next = (list.size() == 0 ? "" : list.get(list.size()-1).value) + st.nextToken();
                // 명령 시간
                int time = Integer.parseInt(st.nextToken());

                // 리스트에 새로운 텍스트 추가
                list.add(new Text(next, time));
            // 되돌리기 명령
            }else{
                // 되돌릴 시간
                int undoTime = Integer.parseInt(st.nextToken());
                // 명령 시간
                int curTime = Integer.parseInt(st.nextToken());

                undoTime = curTime - undoTime;

                // 리스트에 텍스트가 없거나 되돌릴 시간이 리스트의 최소 시간 이하일 경우
                // : 공백 추가
                if(list.size() == 0 || undoTime <= list.get(0).time)
                    list.add(new Text("", curTime));
                // 명령 결과를 역순으로 살펴보며 되돌릴 시간 미만인 경우의 값으로 추가
                else{
                    for(int idx = list.size() - 1; idx >= 0; idx--){
                        Text cur = list.get(idx);
                        if(cur.time < undoTime) {
                            list.add(new Text(cur.value, curTime));
                            break;
                        }
                    }
                }
            }
        }

        // 정답 출력
        sb.append(list.get(list.size()-1).value);
        System.out.println(sb);
    }
}
