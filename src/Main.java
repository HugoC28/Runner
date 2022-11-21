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
                Menu menuScene = new Menu(pane,primaryStage,800,400,true);
                primaryStage.setScene(menuScene);
                primaryStage.show();

        }


        public static void main(String[] args) {
                launch(args);
        }
}
