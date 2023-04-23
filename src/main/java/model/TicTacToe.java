package model;

import java.util.*;

public class TicTacToe {

    char[][] gameBoard = null;
    private char[] board = null;

    int size = 3;
    char playerX = 'X';
    char computer = 'O';


    public TicTacToe() {
        this.gameBoard = new char[size][size];
        board = new char[9];


        // init nets
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = ' ';
            }
        }

    }

    public List<Integer> chooseRandomPosition() {
        ArrayList<String> emptyPositionsList = new ArrayList<>();
        String colInString = "";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[j][i] == ' ') {
                    colInString = String.valueOf(j);
                    colInString += "," + String.valueOf(i);
                    emptyPositionsList.add(colInString);
                }
            }
        }
        Collections.shuffle(emptyPositionsList);
        return List.of(
                Integer.parseInt(emptyPositionsList.get(0).split(",")[0]),
                Integer.parseInt(emptyPositionsList.get(0).split(",")[1])
                );
//        gameBoard
//                [Integer.parseInt(emptyPositionsList.get(0).split(",")[0])]
//                [Integer.parseInt(emptyPositionsList.get(0).split(",")[1])]
//                = 'O';
    }

    public char[][] takePosition(Character playerSymbol, Integer row, Integer col) {
        if (gameBoard[row][col] == ' ') {
            this.gameBoard[row][col] = playerSymbol;
        } else {
            throw new RuntimeException("position is Already taken");
        }
        return gameBoard;
    }

    public void playO(int i, int j) {
        gameBoard[i][j] = 'O';
    }

    public boolean isWinnerMovement(List<Integer> movement) {

        int counterH = 0;
        int counterV = 0;
        int counterDiagonal = 0;
        int counterXDiagonal = 0;
        boolean gewonnen = false;

        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[movement.get(1)][i] == 'X') {
                counterV++;
            }
            if (gameBoard[i][movement.get(0)] == 'X') {
                counterH++;
            }
            if (gameBoard[i][i] == 'X') {
                counterDiagonal++;
            }
            if (gameBoard[i][gameBoard.length - 1 - i ] == 'X') {
                counterXDiagonal++;
            }

        }

        if (counterV == 3 || counterH == 3 || counterDiagonal == 3 || counterXDiagonal == 3) {
            gewonnen = true;
        } else {
            gewonnen = false;
        }


        return gewonnen;

    }

    public void visualisieren() {

        for (int i = 0; i < this.gameBoard.length; i++) {
            System.out.println("");
            System.out.println("|-----------|");
            System.out.print("|");
            for (int j = 0; j < this.gameBoard[i].length; j++) {
                System.out.print(" " + gameBoard[i][j] + " |");
            }
        }
        System.out.println("");
        System.out.println("|-----------|");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToe ticTacToe = (TicTacToe) o;
        return size == ticTacToe.size && playerX == ticTacToe.playerX && computer == ticTacToe.computer && Arrays.equals(gameBoard, ticTacToe.gameBoard) && Arrays.equals(board, ticTacToe.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, playerX, computer);
        result = 31 * result + Arrays.hashCode(gameBoard);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    public static void main(String[] args) {
        TicTacToe tic = new TicTacToe();
        Scanner scan = new Scanner(System.in);
        System.out.println("enter postion you wanted");
        int i = 0;
        while (i < 10) {
            List<Integer> playerMovement = new ArrayList<>();
            playerMovement.add(scan.nextInt());
            playerMovement.add(scan.nextInt());
            tic.takePosition('X', playerMovement.get(0), playerMovement.get(1));
            tic.visualisieren();
            if (tic.isWinnerMovement(playerMovement)){
                System.out.println("Player wins!!");
                break;
            }
            List<Integer> randomMovement = tic.chooseRandomPosition();
            tic.takePosition('O', randomMovement.get(0), randomMovement.get(1));
            if (tic.isWinnerMovement(playerMovement)){
                System.out.println("Computer wins!!");
                break;
            }
            tic.visualisieren();
            i++;
        }
    }
}


