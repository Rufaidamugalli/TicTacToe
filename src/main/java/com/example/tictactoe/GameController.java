package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.TicTacToe;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private Text winnerId;
    @FXML
    private GridPane gridePane;

    @FXML
    private Pane mat00;
    @FXML
    private Pane mat01;
    @FXML
    private Pane mat02;
    @FXML
    private Pane mat10;
    @FXML
    private Pane mat11;
    @FXML
    private Pane mat12;
    @FXML
    private Pane mat20;
    @FXML
    private Pane mat21;
    @FXML
    private Pane mat22;
    private TicTacToe ticTacToeModel = null;
    private List<Integer> movement = null;


    @FXML
    protected void onHelloButtonClick() {
        winnerId.setText("you are the winner");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mat00.setOnMouseClicked(this::handlePaneBoard);
        mat01.setOnMouseClicked(this::handlePaneBoard);
        mat02.setOnMouseClicked(this::handlePaneBoard);
        mat10.setOnMouseClicked(this::handlePaneBoard);
        mat11.setOnMouseClicked(this::handlePaneBoard);
        mat12.setOnMouseClicked(this::handlePaneBoard);
        mat20.setOnMouseClicked(this::handlePaneBoard);
        mat21.setOnMouseClicked(this::handlePaneBoard);
        mat22.setOnMouseClicked(this::handlePaneBoard);
        ticTacToeModel = new TicTacToe();
        winnerId.setText("");
        movement = new ArrayList<>();
    }

    private void updateGameBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Pane mat = ((Pane) gridePane.lookup("#mat" + i + j));
                Text text = new Text();
                text.setText("" + board[i][j]);
                Font font = new Font(45);
                text.setFont(font);
                text.setFill(Color.MEDIUMSEAGREEN);
                text.setX(15);
                text.setY(50);
                mat.getChildren().add(text);
            }
        }

    }

    public void handlePaneBoard(MouseEvent event) {
        Node zelle = (Node) event.getSource();
        String fxId = zelle.getId();
        int length = fxId.length();
        int i = Integer.parseInt(fxId.substring(length - 2, length - 1));
        int j = Integer.parseInt(fxId.substring(length - 1, length));
        updateGameBoard(ticTacToeModel.takePosition('X', i, j));
        if (ticTacToeModel.isWinnerMovement(ticTacToeModel.chooseRandomPosition())) {
            winnerId.setText("YOU WON!");
            return;
        }
        //computer plays
        List<Integer> position = ticTacToeModel.chooseRandomPosition();
        updateGameBoard(
                ticTacToeModel.takePosition('O', position.get(0), position.get(1))
        );
        if (ticTacToeModel.isWinnerMovement(position)) {
            winnerId.setText("YOU LOSE!");
        }

    }

    // tie condtion


}