package dk.easv.tictactoe.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private Random random;

    public ComputerPlayer() {
        random = new Random();
    }

    public int[] getBestMove(int[][] board) {
        List<int[]> emptyCells = new ArrayList <> ();

        for(int c = 0; c < 3; c++) {
            for(int r = 0; r < 3; r++) {
                if(board[c][r] == -1) {
                    emptyCells.add(new int[]{c, r});
                }
            }
        }
        if(emptyCells.isEmpty())
            return null;

        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}