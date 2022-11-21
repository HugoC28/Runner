import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Enemy extends AnimatedThing{
    private int min=100;
    private int max=280;
    public Enemy (Pane pane){
        super("file:img/enemy.png",3,1,150,104,0,pane);
        this.x=800;
        Random r = new Random();
        this.y=r.nextInt((max-min)+1)+min;
        this.getImageView().setViewport(new Rectangle2D(0,0,this.getSizeX(),this.getSizeY()));
        this.getImageView().setX(x);
        this.getImageView().setY(y);
        this.getImageView().setScaleX(0.8);
        this.getImageView().setScaleY(0.8);
        this.gravity=0;
        this.limitY=250;

    }
    int updateTime = 0;
    @Override
    public void update(long time){
        Random r = new Random();
        if(updateTime==5) {
            super.update(time);
            updateTime=0;
        }
        x -= GameScene.enemySpeed;
        updateTime++;
        this.imageView.setX(x);
    }
}
