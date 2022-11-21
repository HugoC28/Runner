import com.sun.nio.sctp.SendFailedNotification;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
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
    private Stage primaryStage;

    public GameScene(Pane pane, Stage primaryStage, double v, double v1, boolean b) {
        super(pane, v, v1, b);
        this.gameCam = new Camera(100,0);
        this.leftBackground = new StaticThing(0,0,"file:img/desert.png");
        this.rightBackground = new StaticThing(0,0,"file:img/desert.png");
        this.scoreText=new Text(745,30,String.valueOf(score));
        this.scoreText.setFont(new Font(25));
        this.pane=pane;
        this.primaryStage=primaryStage;
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
                stop();
            }
            int indexBulletRemove=-1;
            for(Bullet bullet : me.getBulletList()){
                if (bullet.getImageView().getBoundsInParent().intersects(enemy.getImageView().getBoundsInParent())){
                    pane.getChildren().remove(enemy.getImageView());
                    indexRemove=enemyList.indexOf(enemy);
                    indexBulletRemove=me.getBulletList().indexOf(bullet);
                    score+=10;
                }
            }
            if(indexBulletRemove!=-1){
                pane.getChildren().remove(me.getBulletList().get(indexBulletRemove).getImageView());
                me.getBulletList().remove(indexBulletRemove);
                me.setBulletList(me.getBulletList());
                indexBulletRemove=-1;
            }

        }
        if (indexRemove!=-1){
            pane.getChildren().remove(enemyList.get(indexRemove).getImageView());
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

    AnimationTimer timer;
    public void startGame(){
        timer = new AnimationTimer() {

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


    public void stop(){
        Group gameRoot = new Group();
        Pane gamePane = new Pane(gameRoot);
        Menu menuScene = new Menu(gamePane,primaryStage,800,400,true);
        Text txt1=new Text(250,100,"GAME OVER ");
        Text txt2=new Text(275,340,"Score : "+String.valueOf(score));
        txt1.setFont(new Font(50));
        txt1.setTextAlignment(TextAlignment.CENTER);
        txt2.setTextAlignment(TextAlignment.CENTER);

        txt2.setFont(new Font(50));
        gamePane.getChildren().add(txt1);
        gamePane.getChildren().add(txt2);
        timer.stop();
        primaryStage.setScene(menuScene);
    }
}
