package com.kodilla;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Check {
    private TicTacToe tic;
    private Rectangle squares[][];
    public int scoreO = 0;
    public int scoreX = 0;


    public Check(Rectangle squares[][], TicTacToe tic) {
        this.squares = squares;
        this.tic = tic;
    }

    public void checkWin(Rectangle squares[][]) {
        for (int x = 0; x < 3; x++) {
            equalLevels(tic.imageX, x);
            equalLevels(tic.imageO, x);
        }
        equalDiagonals(tic.imageO);
        equalDiagonals(tic.imageX);
    }

    private void equalLevels(ImagePattern image, int x) {
        if (isaBoolean(image, x, x, x, 0, 1, 2)) {
            addScore(image);
            tic.gameBlock = false;

            tic.paint(squares[x][0], squares[x][2]);
        }
        if (isaBoolean(image, 0, 1, 2, x, x, x)) {
            addScore(image);
            tic.gameBlock = false;
            tic.paint(squares[0][x], squares[2][x]);
        }
    }


    private void equalDiagonals(ImagePattern image) {
        if (isaBoolean(image, 0, 1, 2, 0, 1, 2)) {
            addScore(image);
            tic.gameBlock = false;
            tic.paint(squares[0][0], squares[2][2]);
        }
        if (isaBoolean(image, 0, 1, 2, 2, 1, 0)) {
            addScore(image);
            tic.gameBlock = false;
            tic.paint(squares[0][2], squares[2][0]);
        }
    }

    private void addScore(ImagePattern image) {
        if (image.equals(tic.imageO)) {
            scoreO++;
            tic.oWin.setText("");
            tic.oWin.setText(Integer.toString(scoreO));
        } else if (image.equals(tic.imageX)) {
            scoreX++;
            tic.xWin.setText("");
            tic.xWin.setText(Integer.toString(scoreX));
        }
    }

    private boolean isaBoolean(ImagePattern image, int x, int x1, int x2, int y, int y1, int y2) {
        return (squares[x][y].getFill().equals(image)) &&
                (squares[x1][y1].getFill().equals(image)) &&
                (squares[x2][y2].getFill().equals(image));

    }
}
