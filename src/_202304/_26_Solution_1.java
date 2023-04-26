package _202304;

public class _26_Solution_1 {
    public static int answer;
    public int solution(String[] board) {
        answer = 1;

        int oCount = 0;
        int xCount = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                switch (board[i].charAt(j)){
                    case 'O':
                        oCount++;
                        break;
                    case 'X':
                        xCount++;
                        break;
                }
            }
        }

        if(oCount - xCount > 1 || oCount < xCount) return 0;

        int oWin = win('O', board);
        int xWin = win('X', board);;
        if(oWin > 0 && xWin > 0) return 0;
        if(oWin > 0 && oCount == xCount) return 0;
        if(xWin > 0 && oCount > xCount) return 0;

        return answer;
    }

    private int win(char value, String[] board) {
        int win = 0;
        for(int i = 0; i< 3; i++){
            if(board[i].charAt(0) == value && board[i].charAt(1) == value && board[i].charAt(2) == value)
                win++;
            if(board[0].charAt(i) == value && board[1].charAt(i) == value && board[2].charAt(i) == value)
                win++;
        }
        if(board[0].charAt(0) == value && board[1].charAt(1) == value && board[2].charAt(2) == value)
            win++;
        if(board[0].charAt(2) == value && board[1].charAt(1) == value && board[2].charAt(0) == value)
            win++;

        return win;
    }
}
