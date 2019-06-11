package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Movement {
    private Rectangle[][] squares;
    private Rectangle equalRectangle = new Rectangle(100, 100, Color.WHITE);
    private TicTacToe tic;

    public Movement(Rectangle squares[][], TicTacToe tic) {
        this.squares = squares;
        this.tic = tic;
    }

    public void setBlock(Rectangle squares[][]) {
        boolean ifDrawAnyO = false;
        boolean ifDrawAnyX = false;
        boolean ifFoundDiagonalForO = false;
        boolean ifFoundDiagonalForX = false;

        ifDrawAnyO = checkTwoSameFields(squares, tic.imageO, true);
        ifDrawAnyX = checkTwoSameFields(squares, tic.imageX, ifDrawAnyO);

        if (ifDrawAnyX && ifDrawAnyO) {
            ifFoundDiagonalForO = checkDiagonal(squares, tic.imageO);
            ifFoundDiagonalForX = checkDiagonal(squares, tic.imageX);
        }
        if (ifDrawAnyX && ifDrawAnyO && ifFoundDiagonalForO && ifFoundDiagonalForX) {
            setAnyRectangle(squares);
        }

    }


    private void setAnyRectangle(Rectangle[][] squares) {
        Random random = new Random();
        List<Rectangle> temporaryList = Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .filter(this::ifRectangleIsEmpty)
                .collect(Collectors.toList());
        if (!temporaryList.isEmpty()) {
            Rectangle rectangle = temporaryList.get(random.nextInt(temporaryList.size()));
            rectangle.setFill(tic.imageO);
        }
    }


    private boolean checkTwoSameFields(Rectangle squares[][], ImagePattern image, boolean result) {
        if (result) {
            for (int y = 0; y < 3; y++) {
                if ((squares[0][y].getFill().equals(image)) && (squares[1][y].getFill().equals(image)) && ((squares[2][y].getFill().equals(isEmpty())))) {
                    result = setO(squares[2][y]);
                    break;
                } else if ((squares[0][y].getFill().equals(image)) && (squares[2][y].getFill().equals(image)) && (squares[1][y].getFill().equals(isEmpty()))) {
                    result = setO(squares[1][y]);
                    break;
                } else if ((squares[2][y].getFill().equals(image)) && (squares[1][y].getFill().equals(image)) && ((squares[0][y].getFill().equals(isEmpty())))) {
                    result = setO(squares[0][y]);
                    break;
                }
            }
        }
        if (result)
            for (int x = 0; x < 3; x++) {
                if ((squares[x][0].getFill().equals(image)) && (squares[x][1].getFill().equals(image)) && ((squares[x][2].getFill().equals(isEmpty())))) {
                    result = setO(squares[x][2]);
                    break;
                } else if ((squares[x][2].getFill().equals(image)) && (squares[x][1].getFill().equals(image)) && (squares[x][0].getFill().equals(isEmpty()))) {
                    result = setO(squares[x][0]);
                    break;
                } else if ((squares[x][0].getFill().equals(image)) && (squares[x][2].getFill().equals(image)) && (squares[x][1].getFill().equals(isEmpty()))) {
                    result = setO(squares[x][1]);
                    break;
                }
            }
        return result;
    }

    private boolean checkDiagonal(Rectangle squares[][], ImagePattern image) {
        boolean result = true;
        if ((squares[0][0].getFill().equals(image)) && (squares[1][1].getFill().equals(image)) && ((squares[2][2].getFill().equals(isEmpty())))) {
            result = setO(squares[2][2]);
        } else if ((squares[2][2].getFill().equals(image)) && (squares[1][1].getFill().equals(image)) && ((squares[0][0].getFill().equals(isEmpty())))) {
            result = setO(squares[0][0]);
        } else if ((squares[0][0].getFill().equals(image)) && (squares[2][2].getFill().equals(image)) && ((squares[1][1].getFill().equals(isEmpty())))) {
            result = setO(squares[1][1]);
        } else if ((squares[0][2].getFill().equals(image)) && (squares[1][1].getFill().equals(image)) && ((squares[2][0].getFill().equals(isEmpty())))) {
            result = setO(squares[2][0]);
        } else if ((squares[0][2].getFill().equals(image)) && (squares[2][0].getFill().equals(image)) && ((squares[1][1].getFill().equals(isEmpty())))) {
            result = setO(squares[1][1]);
        } else if ((squares[2][0].getFill().equals(image)) && (squares[1][1].getFill().equals(image)) && ((squares[0][2].getFill().equals(isEmpty())))) {
            result = setO(squares[0][2]);
        }

        return result;
    }

    private boolean ifRectangleIsEmpty(Rectangle rectangle) {
        return rectangle.getFill().equals(isEmpty());
    }

    private Paint isEmpty() {
        return equalRectangle.getFill();
    }

    private boolean setO(Rectangle rectangle) {
        rectangle.setFill(tic.imageO);
        rectangle.setDisable(true);
        return false;
    }
}
