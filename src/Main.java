import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
                primaryStage.setTitle("Runner");
                Group root = new Group();
                Pane pane = new Pane(root);

                Camera gameCam = new Camera(100,0);

                GameScene playScene = new GameScene(pane, 800, 400,true,gameCam);
                primaryStage.setScene(playScene);
                primaryStage.show();
                playScene.StartGame();

        }


        public static void main(String[] args) {
                launch(args);
        }
}
