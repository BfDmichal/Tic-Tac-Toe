package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class TicTacToe extends Application {
    private Image oPage = new Image("file:resources/O.png");
    private Image xPage = new Image("file:resources/X.png");
    ImagePattern imageX = new ImagePattern(xPage);
    ImagePattern imageO = new ImagePattern(oPage);
    public boolean playable = true;
    public boolean gameBlock = true;
    Check check;
    AnchorPane panel;
    Movement simpleComputer;
    private Button resetButton;
    private Button playAgainBtn;
    private CheckBox computerOnOff;
    private Line line;
    private Label labelX;
    private Label labelO;
    Label xWin;
    Label oWin;
    private int centerOfRectangle = 50;
    private int constant = 7;
    Rectangle[][] squares = new Rectangle[3][3];

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        simpleComputer = new Movement(squares, this);
        check = new Check(squares, this);
        panel = new AnchorPane();
        panel.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        labelO = new Label("O Score:");
        labelX = new Label("X Score:");
        oWin = new Label("0");
        xWin = new Label("0");
        resetButton = new Button("Reset Table");
        playAgainBtn = new Button("Play Again");
        computerOnOff = new CheckBox("computer");
        GuiView();
        tableView();
        Scene scene = new Scene(panel);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void move(Rectangle rectangle) {
        if (computerOnOff.isSelected()) {
            setRectangle(imageX, rectangle);
            if (gameBlock) {
                simpleComputer.setBlock(squares);
                check.checkWin(squares);
            }
        } else {
            if (playable) {
                setRectangle(imageX, rectangle);
                playable = false;
            } else {
                setRectangle(imageO, rectangle);
                playable = true;
            }
        }
    }

    private void setRectangle(ImagePattern image, Rectangle rectangle) {
        rectangle.setFill(image);
        rectangle.setDisable(true);
        check.checkWin(squares);
    }

    public void paint(Rectangle rectangle0, Rectangle rectangle2) {
        Line line = new Line();
        line.setStartX(rectangle0.getX() + centerOfRectangle);
        line.setStartY(rectangle0.getY() + centerOfRectangle);
        line.setEndX(rectangle2.getX() + centerOfRectangle);
        line.setEndY(rectangle2.getY() + centerOfRectangle);
        line.setStrokeWidth(5);
        line.setStroke(Color.DARKGREEN);
        this.panel.getChildren().add(line);

    }

    private void tableView() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle rectangle = new Rectangle(j * 100 + constant * j, i * 100 + constant * i, 100, 100);
                rectangle.setFill(Color.WHITE);
                rectangle.setOnMouseClicked(this::mouseClick);
                panel.getChildren().add(rectangle);
                squares[i][j] = rectangle;
            }
        }
    }

    private void mouseClick(MouseEvent mouseEvent) {
        Rectangle gameRectangle = (Rectangle) mouseEvent.getSource();
        if (gameBlock)
            move(gameRectangle);
    }


    private void clearGame() {
        gameBlock = true;
        playable = true;
        this.panel.getChildren().remove(line);
        tableView();
    }

    private void resetGame() {
        clearGame();
        check.scoreX = 0;
        check.scoreO = 0;
        xWin.setText("0");
        oWin.setText("0");
    }

    private void GuiView() {
        computerOnOff.setLayoutX(450);
        computerOnOff.setLayoutY(50);
        computerOnOff.setTextFill(Color.WHITE);
        computerOnOff.setSelected(true);
        panel.setMinWidth(450);
        labelO.setTextFill(Color.WHITE);
        labelO.setLayoutX(350);
        labelO.setLayoutY(50);
        labelX.setTextFill(Color.WHITE);
        labelX.setLayoutX(350);
        labelX.setLayoutY(70);
        oWin.setTextFill(Color.WHITE);
        xWin.setTextFill(Color.WHITE);
        oWin.setLayoutY(50);
        oWin.setLayoutX(410);
        xWin.setLayoutY(70);
        xWin.setLayoutX(410);
        playAgainBtn.setLayoutX(360);
        playAgainBtn.setLayoutY(280);
        playAgainBtn.setOnMouseClicked(event -> resetGame());
        resetButton.setLayoutX(370);
        resetButton.setLayoutY(20);
        resetButton.setOnMouseClicked(event -> clearGame());
        panel.getChildren().add(resetButton);
        panel.getChildren().add(playAgainBtn);
        panel.getChildren().add(labelX);
        panel.getChildren().add(labelO);
        panel.getChildren().add(xWin);
        panel.getChildren().add(oWin);
        panel.getChildren().add(computerOnOff);
    }
}

