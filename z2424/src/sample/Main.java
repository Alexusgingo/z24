package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private AnchorPane anchorPane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.anchorPane = (AnchorPane)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        this.stage.setScene(new Scene(this.anchorPane));
        this.stage.setTitle("Baturin");
        this.stage.setResizable(false);
        this.stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
