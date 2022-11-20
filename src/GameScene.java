import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    public static int jump=159;
    public static int shoot=328;
    private Camera gameCam ;
    private StaticThing leftBackground;
    private StaticThing rightBackground;
    private Hero me;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long time) {
            me.update(time/1000);
            gameCam.update(time/1000);
        }
    };

    public GameScene(Pane pane, double v, double v1, boolean b, Camera gameCam) {
        super(pane, v, v1, b);
        this.gameCam = gameCam;
        this.leftBackground = new StaticThing(800,400,"file:src/img/desert.png");
        this.rightBackground = new StaticThing(800,400,"file:src/img/desert.png");
        pane.getChildren().add(leftBackground.getImageView());
        pane.getChildren().add(rightBackground.getImageView());
        leftBackground.getImageView().setX(-300);
        rightBackground.getImageView().setX(leftBackground.getImageView().getX()+800);
        this.me = new Hero(5,9,80,100,5,pane);

    }
    public void update(long time){
        leftBackground.getImageView().setX(leftBackground.getImageView().getX()-4);
        rightBackground.getImageView().setX(rightBackground.getImageView().getX()-4);
        if(leftBackground.getImageView().getX()<-800){
            leftBackground.getImageView().setX(leftBackground.getImageView().getX()+800);
            rightBackground.getImageView().setX(rightBackground.getImageView().getX()+800);
        }
    }

    public Hero getMe() {
        return me;
    }

    public void StartGame(){
        AnimationTimer timer = new AnimationTimer() {
            int count =0;
            @Override
            public void handle(long time) {
                if (count == me.getDuration()) {
                    me.update(time);
                    count = 0;
                }
                gameCam.update(time);
                update(time);
                setOnKeyPressed(e ->{
                    if (e.getCode()== KeyCode.SPACE && me.getAttitude()!=jump){
                        System.out.println("JUMP");
                        me.Jump();
                    }
                    if (e.getCode()== KeyCode.ENTER && me.getAttitude()!=shoot){
                        System.out.println("PEW");
                        me.Shoot();
                    }
                });



                count++;
            }
        };
        timer.start();
    }
}
