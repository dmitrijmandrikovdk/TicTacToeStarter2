
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.scene.shape.Line;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    @FXML
    public Line line1Row;

    @FXML
    public Line line2Row;

    @FXML
    public Line line3Row;

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Turn of Player: ";
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r))
            {
                Button btn = (Button) event.getSource();
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
                else
                    setPlayer();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        String playerSymbol = (game.getNextPlayer() == 0) ? "X" : "O";
        lblPlayer.setText(TXT_PLAYER + playerSymbol);
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                String winnerSymbol = (winner == 0) ? "X" : "O";
                message = "Player " + winnerSymbol + " wins!!!";
                showWinningLine(game.getWinningLineType());
                break;
        }
        lblPlayer.setText(message);
    }
    private void showWinningLine(String lineType){
    switch (lineType){
        case "ROW_0": line1Row.setVisible(true); break;
        case "ROW_1": line2Row.setVisible(true); break;
        case "ROW_2": line3Row.setVisible(true); break;
    }
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            //this thing (instanceof Button) checks wheter the element is a button, before working with it. Without it, lines would cause a ClassCastException.
            if (n instanceof Button) {
                Button btn = (Button) n;
                btn.setText("");
            }
        }
        if (line1Row != null) line1Row.setVisible(false);
        if (line2Row != null) line2Row.setVisible(false);
        if (line3Row != null) line3Row.setVisible(false);
    }
}
