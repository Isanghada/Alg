package _2023._202305;

public class _24_Solution_2 {
    public long solution(int r1, int r2) {
        long answer = 0;

        for(long i = 0; i < r2; i++){
            long r1Y = (long)Math.ceil(Math.sqrt((r1 + i)*(r1 - i)));
            long r2Y = (long)Math.floor(Math.sqrt((r2 + i)*(r2 - i)));

            if(r1Y < 0) r1Y = 0;
            if(r1Y == 0) answer--;
            if(r2Y == 0) answer--;
            answer += (r2Y - r1Y + 1);
        }

        return answer * 4;
    }
}
