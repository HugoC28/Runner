import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Scene {

    private Pane pane;
    private StaticThing background;
    private StaticThing playButton;
    private Stage primaryStage;

    public Menu(Pane pane, Stage primaryStage, double v, double v1, boolean b) {
        super(pane, v, v1, b);
        this.background = new StaticThing(800,400,"file:img/desert.png");
        this.pane=pane;
        this.playButton = new StaticThing(40,40,"file:img/startbutton.png");
        this.primaryStage=primaryStage;
        primaryStage.setScene(this);
        primaryStage.show();
        playButton.getImageView().setX(-250);
        playButton.getImageView().setScaleX(0.3);
        playButton.getImageView().setScaleY(0.3);
        pane.getChildren().add(background.getImageView());
        pane.getChildren().add(playButton.getImageView());
        playButton.getImageView().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            start();
        });

    }

    public void start(){
        Group gameRoot = new Group();
        Pane gamePane = new Pane(gameRoot);
        GameScene playScene = new GameScene(gamePane, primaryStage, 800, 400,true);
        playScene.startGame();
        primaryStage.setScene(playScene);
    }

}
