import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Camera gameCam ;
    private StaticThing leftBackground;
    private StaticThing rightBackground;

    public GameScene(Pane pane, double v, double v1, boolean b, Camera gameCam) {
        super(pane, v, v1, b);
        this.gameCam = gameCam;
        this.leftBackground = new StaticThing(800,400,"src/img/desert.png");
        this.rightBackground = new StaticThing(800,400,"src/img/desert.png");


    }


}
