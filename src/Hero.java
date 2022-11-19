import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{

    public Hero(int indexMax, int duration, int sizeX, int sizeY, int offset) {
        super("file:src/img/heros.png", indexMax, duration, sizeX, sizeY, offset);
        this.getImageView().setViewport(new Rectangle2D(this.getIndex()*this.getSizeX(),this.getSizeY()*this.getAttitude(),this.getSizeX(),this.getSizeY()));
        this.getImageView().setX(this.getX());
        this.getImageView().setY(this.getY());
    }
}
