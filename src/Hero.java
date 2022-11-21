import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Hero extends AnimatedThing{
    private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    private boolean jumping=false;
    public Hero(int indexMax, int duration, int sizeX, int sizeY, int offset, Pane pane) {
        super("file:img/heros.png", indexMax, duration, sizeX, sizeY, offset,pane);
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
        this.jumping=true;
    }
    public void Shoot(){
        if(jumping) setAttitude(GameScene.jumpAndShoot, 2);
        else setAttitude(GameScene.shoot, 5);
        Bullet pew = new Bullet(this.pane,this);
        bulletList.add(pew);
    }
    int count=0;
    @Override
    public void update(long time) {
        if(jumping){
            if(count<3) {
                imageView.setViewport(new Rectangle2D(0, attitude, sizeX, sizeY));
                setY(y - 60);

            }
            else {
                setY(y + 60);
                imageView.setViewport(new Rectangle2D((offset + sizeX), attitude, sizeX, sizeY));
            }
            imageView.setY(y);
            count++;
            if(count==6){
                jumping=false;
                count=0;
                this.setAttitude(0,5);
            }
        }
        else super.update(time);
        for(Bullet bullet : bulletList){
            bullet.update(time);
            if(bullet.getX()>800){
                bullet=null;
            }
        }
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setBulletList(ArrayList<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }
}
