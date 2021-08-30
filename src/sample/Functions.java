package sample;

import javafx.scene.image.ImageView;

public class Functions {
    public static void setimageviewsizing(ImageView imageView, double x, double y, double height, double width) {
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
    }
}
