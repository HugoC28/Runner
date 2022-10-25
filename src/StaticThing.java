import javafx.scene.image.ImageView;

public class StaticThing {

    private double sizeX;
    private double sizeY;

    ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public StaticThing(double sizeX, double sizeY, String fileName) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.imageView=new ImageView(fileName);
    }
}
