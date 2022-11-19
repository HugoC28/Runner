import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Camera gameCam ;
    private StaticThing leftBackground;
    private StaticThing rightBackground;

    public GameScene(Pane pane, Group root, double v, double v1, boolean b, Camera gameCam) {
        super(pane, v, v1, b);
        this.gameCam = gameCam;
        this.leftBackground = new StaticThing(800,400,"file:src/img/desert.png");
        this.rightBackground = new StaticThing(800,400,"file:src/img/desert.png");
        root.getChildren().add(leftBackground.getImageView());
        root.getChildren().add(rightBackground.getImageView());
        leftBackground.getImageView().setX(-300);
        rightBackground.getImageView().setX(leftBackground.getImageView().getX()+800);
        Hero me = new Hero(6,30,80,100,0);
        root.getChildren().add(me.getImageView());

    }


}
