package _202304;

// 전체 경우 탐색
public class _19_Solution_1 {
    public long solution(int[] weights) {
        long answer = 0;
        long[] p = new long[100001];
        p[0] = 1;
        for(int i = 1; i <= 100000; i++) p[i] = p[i-1] * i;
        p[0] = 0;
        int[] count = new int[1001];

        int maxW = 0;
        for(int w : weights) {
            maxW = Math.max(maxW, w);
            count[w]++;
        }

        int[] multiple = new int[]{2, 3, 4};
        for(int i = 100; i <= maxW; i++){
            if(count[i] != 0){
                answer += p[count[i] - 1];
                for(int mul : multiple){
                    int value = i * mul;
                    if(value <= maxW) answer += count[i] * count[value];
                    else break;
                }
            }
        }

        return answer;
    }
}
