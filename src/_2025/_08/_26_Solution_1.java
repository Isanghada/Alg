package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27988
// - 그리디 : 리본의 [왼쪽, 오른쪽]을 기준으로 범위 내에 다른 리본이 있는지 체크하며 탐색
public class _26_Solution_1 {
    // 리볼 클래스
    static class Ribbon implements Comparable<Ribbon>{
        int left;   // 왼쪽 범위
        int right;  // 오른쪽 범위
        int num;    // 리본 번호
        char color; // 리본 색깔
        public Ribbon(int x, int l, char color, int num){
            this.left = x - l;
            this.right = x + l;
            this.color = color;
            this.num = num;
        }
        // 왼쪽 기준, 오른쪽 기준 오름차순 정렬
        @Override
        public int compareTo(Ribbon r){
            return (this.left == r.left) ? this.right - r.right : this.left - r.left;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 리본의 수
        int N = Integer.parseInt(in.readLine());
        
        StringTokenizer X = new StringTokenizer(in.readLine()); // 리본 좌표
        StringTokenizer L = new StringTokenizer(in.readLine()); // 리본 길이
        StringTokenizer C = new StringTokenizer(in.readLine()); // 리본 색깔

        // 리본 정보 입력
        Ribbon[] ribbons = new Ribbon[N];
        for(int i = 0 ; i < N; i++){
            ribbons[i] = new Ribbon(
                    Integer.parseInt(X.nextToken()),
                    Integer.parseInt(L.nextToken()),
                    C.nextToken().charAt(0),
                    i+1
            );
        }

        // 리본 정렬
        Arrays.sort(ribbons);

        // 플래그 초기화
        boolean flag = false;
        // 기준 리본 초기화
        Ribbon pivot = new Ribbon(Integer.MIN_VALUE, 0, 'R', -1);
        // 모든 리본을 대상으로 탐색
        for(Ribbon cur :  ribbons){
            // 현재 리본이 기준 리본 범위를 벗어난 경우
            // - 기준 리본 갱신
            if(cur.left > pivot.right){
                pivot = cur;
                continue;
            }

            // 범위 내에 다른 리본이 있는 경우
            // - 리본 번호 출력
            if(cur.color != pivot.color){
                sb.append("YES\n");
                sb.append(pivot.num).append(" ").append(cur.num);
                flag = true;
                break;
            }

            // 현재 리본의 범위가 더 넓은 경우
            // - 기준 리본 갱신
            if(cur.right > pivot.right) pivot = cur;
        }
        
        // 불가능한 경우 NO 출력
        if(!flag) sb.append("NO");

        // 정답 반환
        System.out.println(sb);
    }
}
