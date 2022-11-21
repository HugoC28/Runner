import javafx.scene.image.ImageView;

public class StaticThing {

    private double X;
    private double Y;

    ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public StaticThing(double X, double Y, String fileName) {
        this.X = X;
        this.Y = Y;
        this.imageView=new ImageView(fileName);

    }
}
