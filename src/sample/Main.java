package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
//import javafx.scene.image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import java.awt.*;
import javafx.scene.image.Image;

public class Main extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Group root = new Group();
        StackPane root = new StackPane();
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        Scene scene = new Scene(root,400,400, Color.CORAL);
        Stage stage = new Stage();

        primaryStage.getIcons().add(new Image("tic tac toe.png"));
        primaryStage.setTitle("Tic-Tac-Toe Quizzer");

        Text text = new Text();
        text.setText("Tic-Tac-Toe Quizzer");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font(null,FontWeight.BOLD, 80));
        text.setEffect(is);
        text.setFill(Color.DARKBLUE);
        text.setId("fancytext");

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStroke(Color.CYAN);
        line.setStrokeWidth(15);

        Image image = new Image("ronnie.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(400);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);


        root.getChildren().add(text);
        root.getChildren().add(imageView);
        StackPane.setAlignment(imageView, Pos.TOP_RIGHT);
        StackPane.setAlignment(text, Pos.TOP_CENTER);
        //root.getChildren().add(line);
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Press ESC if you're weird and don't like full screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
