package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard {
    private int[][] board;
    private int currentPlayer;
    private int winner;
    private int movesCount;

    public GameBoard() {
        board = new int[3][3];
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer() {
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row) {
        if (isGameOver()) {
            return false;
        }

        if (col < 0 || col > 2 || row < 0 || row > 2 || board[col][row] != -1) {
            return false;
        }

        board[col][row] = currentPlayer;
        movesCount++;

        checkGameStatus();

        if (currentPlayer == 0) {
            currentPlayer = 1;
        } else {
            currentPlayer = 0;
        }

        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver() {
        return winner != -1 || movesCount == 9;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
                board[c][r] = -1;
            }
        }
        currentPlayer = 0;
        winner = -1;
        movesCount = 0;
    }

    private void checkGameStatus() {
        for (int c = 0; c < 3; c++) {
            if (board[c][0] != -1 && board[c][0] == board[c][1] && board[c][1] == board[c][2]) {
                winner = board[c][0];
                return;
            }
        }

        for (int r = 0; r < 3; r++) {
            if (board[0][r] != -1 && board[0][r] == board[1][r] && board[1][r] == board[2][r]) {
                winner = board[0][r];
                return;
            }
        }

        if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = board[0][0];
            return;
        }

        if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = board[0][2];
        }
    }
}