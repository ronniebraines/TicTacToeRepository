package sample;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Functions {
    public void setimageviewsizing(ImageView imageView, double x, double y, double height, double width) {
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
    }
    public void setshadowstyling(Text text){
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4.0f);
        innerShadow.setOffsetY(4.0f);
        text.setEffect(innerShadow);
    }

}
