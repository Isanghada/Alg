package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22981
// - 조합 : 각 조합이 될 수 있는 경우 계산
public class _13_Solution_1 {
    public static final int MAX = 41;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 동물의 수
        int N = Integer.parseInt(in.readLine());

        // 0 ~ 40까지 대답의 수
        int[] animalCount = new int[MAX];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++)	animalCount[Integer.parseInt(st.nextToken())]++;

        // 이전 결과
        int prevCount = 2;
        int twoCount = 0;   // 동물이 2마리인 경우
        int oneCount = 0;   // 동물이 1마리인 경우
        // 차례대로 확인
        for(int i = 0; i < MAX; i++){
            // 이전 결과보다 더 많은 동물이 대답한 경우 : 불가능한 경우
            // - 0 출력 후 종료
            if(prevCount < animalCount[i]){
                sb.append(0);
                break;
            // 가능한 경우
            }else{
                // prevCount 갱신
                prevCount = animalCount[i];
                // i라고 대답한 동물이 2마리인 경우 twoCount 증가
                if(animalCount[i] == 2) twoCount++;
                // i라고 대답한 동물이 1마리인 경우 twoCount 증가
                else if(animalCount[i] == 1) oneCount++;
            }
        }
//        System.out.println(Arrays.toString(animalCount));
//        System.out.println(twoCount+", "+oneCount);
        // 가능한 경우인 경우! : 2^(twoCount+(oneCount가 1이상인 경우 1 : 아닐 경우 0))
        // - 2마리인 경우 토끼, 고양이 모두 될 수 있으므로 2를 제곱!
        // - 1마리인 경우 전부 토끼 혹은 전부 고양이가 되어야 하므로 1 증가
        if(sb.length() == 0) sb.append(pow(2, twoCount+(oneCount > 0 ? 1 : 0)));
        System.out.println(sb.toString());
    }
    public static int pow(int a, int b){
        if(b == 0) return 1;
        else if((b & 1) == 1) return a * pow(a, b-1);
        else return pow(a*a, b/2);
    }
}
