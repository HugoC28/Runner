import com.sun.nio.sctp.SendFailedNotification;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class GameScene extends Scene {
    public static int jump=159;
    public static int shoot=328;
    public static int jumpAndShoot=493;
    private Camera gameCam ;
    private StaticThing leftBackground;
    private StaticThing rightBackground;
    private int score=0;
    private Text scoreText;
    private Hero me;
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private Pane pane;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long time) {
            me.update(time/1000);
            gameCam.update(time/1000);
        }
    };

    public GameScene(Pane pane, double v, double v1, boolean b) {
        super(pane, v, v1, b);
        this.gameCam = new Camera(100,0);
        this.leftBackground = new StaticThing(800,400,"file:src/img/desert.png");
        this.rightBackground = new StaticThing(800,400,"file:src/img/desert.png");
        this.scoreText=new Text(750,30,String.valueOf(score));
        this.scoreText.setFont(new Font(25));
        this.pane=pane;
        pane.getChildren().add(leftBackground.getImageView());
        pane.getChildren().add(rightBackground.getImageView());
        pane.getChildren().add(scoreText);
        leftBackground.getImageView().setX(-300);
        rightBackground.getImageView().setX(leftBackground.getImageView().getX()+800);
        this.me = new Hero(5,9,80,100,5,pane);


    }
    int countMe =0;
    int count=0;
    public void update(long time){
        leftBackground.getImageView().setX(leftBackground.getImageView().getX()-4);
        rightBackground.getImageView().setX(rightBackground.getImageView().getX()-4);
        if(leftBackground.getImageView().getX()<-800){
            leftBackground.getImageView().setX(leftBackground.getImageView().getX()+800);
            rightBackground.getImageView().setX(rightBackground.getImageView().getX()+800);
        }
        this.scoreText.setText(String.valueOf(score));

        if (countMe == me.getDuration()) {
            me.update(time);
            countMe = 0;
        }
        int maxEnemyX=600;
        boolean enemySpawnable=enemyList.size()<4;
        int indexRemove=-1;
        for(Enemy enemy : enemyList){
            if (count % enemy.getDuration()==0) {
                enemy.update(time);
            }
            if(enemy.getX()>maxEnemyX){
                enemySpawnable = false;
            }
            if(enemy.getX()<-enemy.getSizeX()){
                indexRemove=enemyList.indexOf(enemy);
            }
            if(me.getImageView().getBoundsInParent().intersects(enemy.getImageView().getBoundsInParent())){
                System.out.println("DIE");
            }
        }
        if (indexRemove!=-1){
            enemyList.remove(indexRemove);
            indexRemove=-1;
        }

        Random r = new Random();

        if(enemySpawnable && r.nextInt(100)>0){
            enemyList.add(new Enemy(pane));

        }
        gameCam.update(time);
        if(count%10==0){
            score++;
        }

        count++;
        countMe++;

    }

    public Hero getMe() {
        return me;
    }

    public void StartGame(){
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long time) {
                update(time);

                setOnKeyPressed(e ->{
                    if (e.getCode()== KeyCode.SPACE && !me.isJumping()){
                        me.Jump();
                    }
                    if (e.getCode()== KeyCode.ENTER && me.getAttitude()!=shoot){
                        me.Shoot();
                    }
                });


            }
        };
        timer.start();
    }


    public
}
