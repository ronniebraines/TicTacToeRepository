package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Main extends Application {
    Stage window;
    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] board = new Tile[3][3];
    private List<Combo> combos = new ArrayList<>();
    private Line line = new Line();

    Pane root = new Pane();
    StackPane currentplayerpane = new StackPane();
    private StringProperty test;
    Functions functions = new Functions();


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.getIcons().add(new Image("tic tac toe.png"));
        //main page
        StackPane stackPane = new StackPane();
        //stackpane holds the properties for the log in page
        Scene scene1 = new Scene(root);
        //scene1 is the scene that holds the properties for the actaul game
        Scene scene2 = new Scene(stackPane);
        //scene two holds the stackpane
        GridPane loginPane = new GridPane();
        //a seperate gridpane is initialised to hold the children required specifically for logging in
        Label currentplayer = new Label();
        //initialises the variables to be used in the code

        primaryStage.setScene(scene2);
        root.setPrefSize(900, 900);
        root.setMinSize(618, 725);
        stackPane.setPrefSize(618, 600);
        stackPane.setMinSize(618, 725);

        //setting the dimensions for the login page (stackpane) and the game page (root)
        Text titletext = new Text();
        titletext.setText("Tic-Tac-Toe Quiz Game");
        titletext.setTextAlignment(TextAlignment.CENTER);
        titletext.setFont(Font.font(null, FontWeight.BOLD, 80));
        titletext.setFill(Color.DARKBLUE);
        functions.setshadowstyling(titletext);

        //the variable titletext holds the title titletext

        Image tictactoeimage = new Image("tic tac toe.png");
        ImageView tictactoeimageview = new ImageView(tictactoeimage);
        functions.setimageviewsizing(tictactoeimageview, 400, 400, 250, 250);

        //an image is created which is a plain image of a tic-tac-toe board to act as an icon
        //for the application and also an image in one corner of the login page

        Image rjimage = new Image("ronnie.png");
        ImageView rjimageview = new ImageView(rjimage);
        functions.setimageviewsizing(rjimageview, 400, 400, 250, 250);

        //ronnie.png is an image with my initials which is displayed in the login page as a form of trademarking

        Text logintext = new Text("Enter your names");
        logintext.setFont(Font.font("Tahoma", FontWeight.LIGHT, 30));
        loginPane.add(logintext, 0, 0);

        //the Text logintext prints titletext above the login textfields to prompt users to input their names

        Label lbluser1 = new Label("Player X Username:");
        loginPane.add(lbluser1, 0, 2);
        //this label specifies which players name goes in which field by showing alongside the textfield

        TextField player1username = new TextField();
        player1username.setPromptText("Username");
        loginPane.add(player1username, 1, 2);

        //the textfield player1 username allows one user to enter his name and therefore stores the data in this field

        Label lbluser2 = new Label("Player O Username");
        loginPane.add(lbluser2, 0, 4);
        //this second label signals to users to enter the name into the textfield beside it


        TextField player2username = new TextField();
        player2username.setPromptText("Username");
        loginPane.add(player2username, 1, 4);
        //the textfield player1 username allows one user to enter his name and therefore stores the data in this field


        Button loginbutton = new Button("Login");
        loginPane.add(loginbutton, 1, 7);
        loginbutton.setOnAction(e -> {
            String username11 = player1username.getText();
            String username2 = player2username.getText();
            if (username11.equals("") && username2.equals("")) {
                player1username.setStyle("-fx-control-inner-background: red");
                player2username.setStyle("-fx-control-inner-background: red");
            } else if (username2.equals("")) {
                player2username.setStyle("-fx-control-inner-background: red");
                player1username.setStyle("-fx-control-inner-background: white");
            } else if (username11.equals("")) {
                player1username.setStyle("-fx-control-inner-background: red");
                player2username.setStyle("-fx-control-inner-background: white");

            } else {
                window.setScene(scene1);
            }
        });
        //this portion of code prevents users from logging in without titletext in both boxes
        //the and gate is what allows the user to only log in once both conditions are met
        //the if statement displays to the user which box is causing the issue by making it red


        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(10));

        //this section means that the boxes in the login pane cannot overlap and will centre themselves

        StackPane.setAlignment(tictactoeimageview, Pos.TOP_LEFT);
        StackPane.setAlignment(titletext, Pos.TOP_CENTER);
        StackPane.setAlignment(loginPane, Pos.CENTER);
        StackPane.setAlignment(rjimageview, Pos.TOP_RIGHT);
        //stackpane allows me to position the title, loginpane and the two images.

        stackPane.getChildren().addAll(loginPane, titletext, rjimageview, tictactoeimageview);
        //adds the children to the stackpane once alligned


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                root.getChildren().addAll(tile);
                board[i][j] = tile;
            }
        }
        //this nested for loop creates the tiles in a 3x3 format


        //horizontal
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }
        //diagonals
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));


        primaryStage.setWidth(618); //618
        primaryStage.setHeight(725); //725


        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("I'd recommend staying full screen but press esc if you must");
        primaryStage.show();
    }

    private void checkState() {
        StackPane stackPane = new StackPane();
        stackPane.setMinSize(800, 800);
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                playable = false;
                playWinAnimation(combo);
            }
        }
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].text.setText(null);
            }
        }

        playable = true;
        turnX = true;
        root.getChildren().remove(line);
    }

    public Boolean getplayerxturn() {
        return turnX;
    }

    public void setPlayer(boolean whosgo) {
        this.turnX = whosgo;
    }


    private void playWinAnimation(Combo combo) {
        Line line = new Line();
        StackPane stackPane = new StackPane();

        stackPane.setMinSize(600, 600);
        line.setStrokeWidth(2);

        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndY(combo.tiles[0].getCenterX());
        line.setEndX(combo.tiles[0].getCenterY());

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
        timeline.play();
        turnX = getplayerxturn();
        if (turnX = true) {
            System.out.println("player x wins");
        } else {
            System.out.println("player o wins");
        }
    }


    private class Combo {
        private Tile[] tiles;

        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty()) {
                return false;
            } else {
                return tiles[0].getValue().equals(tiles[1].getValue())
                        && tiles[0].getValue().equals(tiles[2].getValue());
            }
        }
    }

    private class Tile extends StackPane {
        private Text text = new Text();
        private Text currentplayer = new Text();
        private InnerShadow is = new InnerShadow();
        Functions functions = new Functions();

        public Tile() {
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            currentplayer.setText("Hello");

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            StackPane.setAlignment(border, Pos.CENTER);
            StackPane.setAlignment(text, Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (!playable)
                    return;
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (turnX) {
                        drawX();
                        turnX = false;
                    } else {
                        drawO();
                        turnX = true;
                    }
                    checkState();

                } else if (event.getButton() == MouseButton.SECONDARY) {
                    if (turnX) {
                        return;
                    }
                    root.getChildren().removeAll(currentplayer);
                    drawO();
                    setPlayer(true);
                    turnX = true;
                    checkState();
                }
            });

        }


        public double getCenterX() {
            return getTranslateX() + 100;
        }

        public double getCenterY() {
            return getTranslateY() + 100;
        }

        public String getValue() {
            return text.getText();
        }


        private void drawX() {
            InnerShadow is = new InnerShadow();
            text.setFont(Font.font(null, FontWeight.BOLD, 90));
            text.setFill(Color.PURPLE);
            functions.setshadowstyling(text);
            text.setText("X");
        }


        private void drawO() {
            InnerShadow is = new InnerShadow();
            text.setFont(Font.font(null, FontWeight.BOLD, 100));
            text.setFill(Color.CYAN);
            functions.setshadowstyling(text);
            text.setText("O");
        }

        private void addcurrentplayertext() {
            currentplayer.setText("Player X turn");
            currentplayer.setEffect(is);
            currentplayer.setFont(Font.font(null, FontWeight.BOLD, 60));
            currentplayer.setFill(Color.DEEPSKYBLUE);
            currentplayer.setTextAlignment(TextAlignment.CENTER);
            currentplayer.setX(50);
            currentplayer.setY(650);
            root.getChildren().add(currentplayer);
        }
    }
}

