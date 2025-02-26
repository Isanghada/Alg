package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20943
// -  구현 : 1. (a, b)를 기울기로 가지는 직선!
//           2. a와 b의 최대 공약수로 나누어 기울기 계산!
//           3. 기울기마다 개수를 체크!
public class _26_Solution_1 {
    static class Slope{
        int a;
        int b;
        public Slope(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Slope){
                Slope s = (Slope) obj;
                if(this.a == s.a && this.b == s.b) return true;
                else return false;
            }
            return false;
        }
    }
    // A : b가 0인 경우
    // B : a가 0인 경우
    static final Slope A = new Slope(1, 0), B = new Slope(0, 1);
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;

        // 유저의 수
        int N = Integer.parseInt(in.readLine());
        
        // map 초기화
        Map<Slope, Long> map = new HashMap<>();
        map.put(A, 0L);
        map.put(B, 0L);

        // 모든 유저의 직선 체크
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a 혹은 b가 0인 경우 해당 기울기 증가!
            if(a == 0) map.put(A, map.get(A)+1);
            else if(b == 0) map.put(B, map.get(B)+1);
            else{
                // 최대 공약수
                int gcd = gcd(Math.abs(a), Math.abs(b));
                
                // 기울기 계산
                a /= gcd;
                b /= gcd;

                Slope s = null;
                // 기울기가 음수인 경우 (-1, 1), (1, -1)은 동일한 경우이므로 통합!
                if((a < 0 && b > 0) || (a > 0 && b < 0)) s = new Slope(Math.abs(a), -Math.abs(b));
                else s = new Slope(Math.abs(a), Math.abs(b));
                
                // 기울기 개수 증가
                map.put(s, map.getOrDefault(s, 0L)+1);
            }
        }

        // 정답 초기화
        long answer = 0;
        // 모든 기울기의 쌍 계산
        for(Map.Entry<Slope, Long> e : map.entrySet()){
            N -= e.getValue();
            answer += N * e.getValue();
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
