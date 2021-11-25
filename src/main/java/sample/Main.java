package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    static final int TILE_SIZE = 100;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private AtomicInteger counter = new AtomicInteger();

    private Parent createMenu(Stage primaryStage){
        Pane root2 = new Pane();
        Button ContinueButton = new Button();
        ContinueButton.setText("Continue");
        ContinueButton.setTranslateX(40);
        ContinueButton.setTranslateY(30);
        ContinueButton.setMinHeight(60);
        ContinueButton.setMinWidth(100);

        Button RestartButton = new Button();
        RestartButton.setText("Restart");
        RestartButton.setTranslateX(40);
        RestartButton.setTranslateY(110);
        RestartButton.setMinHeight(60);
        RestartButton.setMinWidth(100);

        Button ExitToMainMenuButton = new Button();
        ExitToMainMenuButton.setText("Exit to main \n    menu");
        ExitToMainMenuButton.setTranslateX(40);
        ExitToMainMenuButton.setTranslateY(190);
        ExitToMainMenuButton.setMinHeight(60);
        ExitToMainMenuButton.setMinWidth(100);

        ContinueButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ContinueButton.getScene().getWindow();
            stage.close();

        });

        RestartButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) RestartButton.getScene().getWindow();
            stage.close();


            primaryStage.show();
        });

        ExitToMainMenuButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ContinueButton.getScene().getWindow();
            stage.close();
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Tile tile = new Tile((x + y) % 2 == 0, x, y);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);

                    Piece piece = null;

                    if (y <= 2 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.RED, x, y);
                    }

                    if (y >= 5 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.WHITE, x, y);
                    }

                    if (piece != null) {
                        tile.setPiece(piece);
                        pieceGroup.getChildren().add(piece);
                    }
                }
            }
            primaryStage.close();
        });

        root2.getChildren().addAll(ContinueButton, ExitToMainMenuButton, RestartButton);
        return root2;
    }

    private Parent createMainMenu(){
        Pane root3 = new Pane();
        Button PlayButton = new Button();
        PlayButton.setText("Play");
        PlayButton.setTranslateX(40);
        PlayButton.setTranslateY(30);
        PlayButton.setMinHeight(60);
        PlayButton.setMinWidth(100);

        Button ExitButton = new Button();
        ExitButton.setText("Exit");
        ExitButton.setTranslateX(40);
        ExitButton.setTranslateY(110);
        ExitButton.setMinHeight(60);
        ExitButton.setMinWidth(100);

        ExitButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ExitButton.getScene().getWindow();
            stage.close();
            Stage primaryStage = (Stage) tileGroup.getScene().getWindow();
            primaryStage.close();
        });

        PlayButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ExitButton.getScene().getWindow();
            stage.close();
            Stage primaryStage = (Stage) tileGroup.getScene().getWindow();
            primaryStage.show();
        });
        root3.getChildren().addAll(PlayButton, ExitButton);

        return root3;
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
/*
        Button RestartButton = new Button();
        RestartButton.setText("menu");
        RestartButton.setTranslateX(900);
        RestartButton.setTranslateY(80);
        RestartButton.setPrefSize(70,30);

        Button menuButton = new Button();
        menuButton.setText("Menu");
        menuButton.setTranslateX(900);
        menuButton.setTranslateY(30);
        menuButton.setPrefSize(70,30);

        menuButton.setOnMouseClicked(mouseEvent -> {
            root.getChildren().addAll(RestartButton);
        });

        RestartButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) RestartButton.getScene().getWindow();
            stage.hide();
            stage.show();


            /*
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Tile tile = new Tile((x + y) % 2 == 0, x, y);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);

                    Piece piece = null;

                    if (y <= 2 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.RED, x, y);
                    }

                    if (y >= 5 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.WHITE, x, y);
                    }

                    if (piece != null) {
                        tile.setPiece(piece);
                        pieceGroup.getChildren().add(piece);
                    }
                }
           }
        } );
*/
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());
        if (counter.get() % 2 == 0) {
            if (piece.getType() == PieceType.WHITE) {
                if ((Math.abs(newX - x0) == 1) && (newY - y0 == -1)) {
                    counter.getAndIncrement();
                    System.out.println(counter.get());
                    return new MoveResult(MoveType.NORMAL);
                } else if (Math.abs(newX - x0) == 2 && newY - y0 == -2) {

                    int x1 = x0 + (newX - x0) / 2;
                    int y1 = y0 + (newY - y0) / 2;

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                        counter.getAndIncrement();
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                } else if (Math.abs(newX - x0) == 2 && y0 - newY == -2) {//можно упростить?
                    int x1 = x0 + (newX - x0) / 2;
                    int y1 = y0 + (newY - y0) / 2;

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                        counter.getAndIncrement();
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                }
            }
        } else {
            if (piece.getType() == PieceType.RED) {
                if (Math.abs(newX - x0) == 1 && newY - y0 == 1) {
                    counter.getAndIncrement();
                    return new MoveResult(MoveType.NORMAL);
                } else if (Math.abs(newX - x0) == 2 && newY - y0 == 2) {
                    int x1 = x0 + (newX - x0) / 2;
                    int y1 = y0 + (newY - y0) / 2;
                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                        counter.getAndIncrement();
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                } else if (Math.abs(newX - x0) == 2 && y0 - newY == 2) {//можно упростить?
                    int x1 = x0 + (newX - x0) / 2;
                    int y1 = y0 + (newY - y0) / 2;

                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                        counter.getAndIncrement();
                        return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                    }
                }
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        //Scene scene = new Scene(createContent());


        primaryStage.setTitle("Шашки");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE){
                Scene scene2 = new Scene(createMenu(primaryStage));
                Stage stage = new Stage();
                stage.setHeight(310);
                stage.setWidth(200);

                scene2.setFill(Color.BLUE);
                stage.setScene(scene2);
                stage.setResizable(false);
                stage.show();
            }
        });


    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(piece, newX, newY);
            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });

        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
