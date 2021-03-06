package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    static final int TILE_SIZE = 100;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    final static int PRIORITY_FOR_NOT_REPLACING = 0;
    final static int PRIORITY_FOR_REPLACING_AND_DYING = 1;
    final static int PRIORITY_FOR_REPLACING_WITHOUT_ISSUES = 2;
    final static int PRIORITY_FOR_NOT_REPLACING_TO_CENTRAL_PART = 3; //крайние центральные клетки
    final static int PRIORITY_FOR_NOT_REPLACING_FOR_KILL_AND_DYING = 4;
    final static int PRIORITY_FOR_NOT_REPLACING_FOR_KILL = 5;
    final static int PRIORITY_FOR_NOT_REPLACING_FOR_KILL_2 = 6;
    final static int PRIORITY_FOR_NOT_REPLACING_FOR_KILL_3 = 7;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private AtomicInteger counter = new AtomicInteger();

    private Parent createRulesScene(Stage primaryStage){
        Pane rootR = new Pane();
        Label Rules = new Label();
        Rules.setText("Правила:\n" +
                "В игре принимают участие 2 игрока. Игроки располагаются на противоположных сторонах доски.\n" +
                "Игровое поле (доска) располагается таким образом, что бы угловое темное поле было расположено  с левой стороны игрока.\n" +
                "\n" +
                "Выбор цвета игроками определяется жребием или по договоренности. Шашки расставляются на четырех, ближних к игроку, рядах на темных клетках.\nПраво первого хода обычно принадлежит игроку, который играет белым (светлым) цветом. Ходы осуществляются соперниками поочередно. Ход \nсчитается сделанным, если участник игры после перемещения шашки отпустил руку. Если игрок дотронулся до шашки, он\nобязан ей сделать ход. Если, кто-либо из соперников хочет поправить шашки, обязан предупредить заранее.\n" +
                "В начале игры все шашки соперников являются простыми. Простые шашки можно перемещать только вперед по диагоналям на соседнюю свободную клетку.\n" +
                "Если простая шашка дошла до последней горизонтали, игра завершается и победителем становится тот игрок, чья шашка достигла противоположного конца доски.\n" +
                "Взятие шашки соперника производится, переносом через неё своей, в том случае, если она находится на соседней с простой шашкой диагональной клетке и за ней \nимеется свободное поле. Если после этого хода имеется продолжения для взятия, ход продолжается, \nпри этом выбирается вариант по «правилу большинства», т.е. взятие наибольшего количества шашек соперника, в \nданном случае дамка не пользуется никакими преимуществами и не накладывает на игрока \nникаких дополнительных обязательств.");
        Rules.setTranslateX(20);
        Rules.setTranslateY(20);

        Button GetBackButton = new Button();
        GetBackButton.setText("Get back");
        GetBackButton.setTranslateX(800);
        GetBackButton.setTranslateY(300);

        GetBackButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) GetBackButton.getScene().getWindow();
            stage.close();
        });

        rootR.getChildren().addAll(GetBackButton, Rules);
        return rootR;
    }

    private Parent createMenu(Stage primaryStage){
        Pane root2 = new Pane();
        Button ContinueButton = new Button();
        ContinueButton.setText("Continue");
        ContinueButton.setTranslateX(40);
        ContinueButton.setTranslateY(30);
        ContinueButton.setMinHeight(60);
        ContinueButton.setMinWidth(100);

        Button ExitToMainMenuButton = new Button();
        ExitToMainMenuButton.setText("Exit ");
        ExitToMainMenuButton.setTranslateX(40);
        ExitToMainMenuButton.setTranslateY(190);
        ExitToMainMenuButton.setMinHeight(60);
        ExitToMainMenuButton.setMinWidth(100);

        ContinueButton.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ContinueButton.getScene().getWindow();
            stage.close();

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

        root2.getChildren().addAll(ContinueButton, ExitToMainMenuButton);
        return root2;
    }

    private Parent createWonField() {
        Pane rootWon = new Pane();
        Button ContinueButton = new Button();
        ContinueButton.setText("End game");
        ContinueButton.setTranslateX(40);
        ContinueButton.setTranslateY(30);
        ContinueButton.setMinHeight(60);
        ContinueButton.setMinWidth(100);
        return rootWon;
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
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

    private MoveResult tryMove (Piece piece, int newX, int newY) {
        boolean isBlackWon = false;
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (counter.get() % 2 == 0) {
            if (piece.getType() == PieceType.WHITE) {
                if ((Math.abs(newX - x0) == 1) && (newY - y0 == -1)) {
                    counter.getAndIncrement();
                    if (newY == 0){
                        isBlackWon = true;
                    }
                    return new MoveResult(MoveType.NORMAL);
                } else if (Math.abs(newX - x0) == 2 && newY - y0 == -2) {

                    int x1 = x0 + (newX - x0) / 2;
                    int y1 = y0 + (newY - y0) / 2;
                    if (newY == 0){
                        isBlackWon = true;
                    }
                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                        counter.getAndIncrement();
                        if (newY == 0){
                            isBlackWon = true;
                        }
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
        if (isBlackWon){
            Scene scene2 = new Scene(createWonField());
            Stage stage = new Stage();
            stage.setHeight(310);
            stage.setWidth(200);

            stage.setScene(scene2);
            stage.setResizable(false);
            stage.show();
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE){
                Scene scene2 = new Scene(createMenu(primaryStage));
                Stage stage = new Stage();
                stage.setHeight(310);
                stage.setWidth(200);
                stage.setScene(scene2);
                stage.setResizable(false);
                stage.show();
            }
            if (event.getCode() == KeyCode.F1){
                Scene RulesScene = new Scene(createRulesScene(primaryStage));
                Stage RulesStage = new Stage();
                RulesStage.setTitle("Info about rules");
                RulesStage.setHeight(400);
                RulesStage.setWidth(1000);
                RulesStage.setScene(RulesScene);
                RulesStage.setResizable(false);
                RulesStage.show();
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
