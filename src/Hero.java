import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{
    public Hero(int indexMax, int duration, int sizeX, int sizeY, int offset) {
        super("file:src/img/heros.png", indexMax, duration, sizeX, sizeY, offset);
        this.x=50;
        this.y=250;
        this.getImageView().setViewport(new Rectangle2D(this.getIndex()*this.getSizeX(),this.getAttitude(),this.getSizeX(),this.getSizeY()));
        this.getImageView().setX(x);
        this.getImageView().setY(y);
        this.gravity=10;
        this.limitY=250;

    }

    public void Jump(){
        setAttitude(159, 2);
        setY(getY()-50);
    }
}
