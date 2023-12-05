import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.ClientSocket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ClientSocket.getInstance().getSocket();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setMinHeight(580);
        primaryStage.setMinWidth(350);
        primaryStage.setTitle("Поликлиника");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
