import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Hero extends AnimatedThing{
    private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    public Hero(int indexMax, int duration, int sizeX, int sizeY, int offset, Pane pane) {
        super("file:src/img/heros.png", indexMax, duration, sizeX, sizeY, offset,pane);
        this.x=50;
        this.y=250;
        this.getImageView().setViewport(new Rectangle2D(this.getIndex()*this.getSizeX(),this.getAttitude(),this.getSizeX(),this.getSizeY()));
        this.getImageView().setX(x);
        this.getImageView().setY(y);
        this.gravity=10;
        this.limitY=250;

    }

    public void Jump(){
        setAttitude(GameScene.jump, 2);
        setY(getY()-50);
    }
    public void Shoot(){
        setAttitude(GameScene.shoot, 5);
        Bullet pew = new Bullet(this.pane,this);
        bulletList.add(pew);
    }

    @Override
    public void update(long time) {
        super.update(time);
        for(Bullet bullet : bulletList){
            bullet.update(time);
            if(bullet.getX()>800){
                bullet=null;
            }
        }
    }
}
