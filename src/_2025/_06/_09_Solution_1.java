package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

// https://www.acmicpc.net/problem/32387
// - 트리 : 이진 탐색 트리(특히, 레드 블랙 트리)로 사용 가능한 콘센트를 체크!
//          Java TreeSet을 사용하면 바로 해결할 수 있으므로 이를 활용했음.
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] sockets = new int[N+1];
        TreeSet<Integer> unusedSocketSet = new TreeSet<>();
        for(int n = 1; n <= N; n++) unusedSocketSet.add(n);

        for(int q = 1; q <= Q; q++){
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(type == 1){
                Integer port = unusedSocketSet.ceiling(num);
                if(port == null) sb.append(-1);
                else {
                    sockets[port] = q;
                    unusedSocketSet.remove(port);
                    sb.append(port);
                }
            }else{
                if(sockets[num] == 0) sb.append(-1);
                else{
                    sb.append(sockets[num]);
                    unusedSocketSet.add(num);
                    sockets[num] = 0;
                }
            }
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
