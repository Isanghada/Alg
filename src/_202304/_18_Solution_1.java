package _202304;

// 전체 경우 탐색
public class _18_Solution_1 {
    public static int maxNumOfSubscriber;   // 최대 구독자수
    public static int maxSalePrice;         // 최대 구독자수일 경우의 최대 판매액
    public static final int[] DISCOUNT_RATE = {40, 30, 20 ,10}; // 할인율
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        // 계산용 변수 초기화.
        maxNumOfSubscriber = 0;
        maxSalePrice = 0;

        // 재귀를 통해 모든 경우의 수 탐색
        calculator(0, emoticons.length, new int[emoticons.length], users, emoticons);

        // 정답 입력
        answer[0] = maxNumOfSubscriber;
        answer[1] = maxSalePrice;

        return answer;
    }

    private void calculator(int step, int emoticonLength, int[] input, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인액을 결정한 경우 현재 상태 계산하여 최대값 변경
        if(step == emoticonLength){
            int numOfSubscriber = 0;    // 현재 경우의 구독자 수
            int salePrice = 0;          // 현재 경우의 이모티콘 판매액

            // 모든 유저 계산
            for(int[] user : users){
                int userDiscountRate = user[0]; // 유저가 구매하는 최소 할인액
                int userLimit = user[1];        // 유저의 현재 잔액

                int sum = 0;    // 이모티콘 구매 액수의 합
                
                // 모든 이모티콘 중에서 할인액 이상인 경우만 구매
                for(int i = 0; i < emoticonLength; i++){
                    if(input[i] >= userDiscountRate){
                        sum += emoticons[i] * (100 - input[i]) / 100;
                    }
                }
                // 유저의 현재 잔액 이상일 경우 구독
                if(sum >= userLimit) numOfSubscriber++;
                // 아닐경우 이모티콘만 구매
                else salePrice += sum;
            }

            // 최대 구독자수보다 현재 구독자수가 많다면 모두 변경 
            if(maxNumOfSubscriber < numOfSubscriber){
                maxNumOfSubscriber = numOfSubscriber;
                maxSalePrice = salePrice;
            // 최대 구독자수와 같을 경우 이모티콘 판매액만 최대값으로 변경
            }else if(maxNumOfSubscriber == numOfSubscriber){
                maxSalePrice = Math.max(maxSalePrice, salePrice);
            }
        }else{
            // 모든 할인율을 이모티콘마다 적용시키며 탐색
            for(int i = 0; i < 4; i++){
                input[step] = DISCOUNT_RATE[i];
                calculator(step+1, emoticonLength, input, users, emoticons);
            }
        }
    }
}
