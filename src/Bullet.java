import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class Bullet extends AnimatedThing{

    public Bullet(Pane pane,Hero me){
        super("file:src/img/heros.png",1,30,61,50,0,pane);
        this.setAttitude(360,1);
        this.x=me.getX()+80;
        this.y=me.getY()+20;
        this.getImageView().setViewport(new Rectangle2D(520,this.getAttitude(),this.getSizeX(),this.getSizeY()));
        this.getImageView().setX(x);
        this.getImageView().setY(y);
        this.gravity=0;
    }

    @Override
    public void update(long time){
        x+=this.duration;
        this.imageView.setX(x);
    }
}
