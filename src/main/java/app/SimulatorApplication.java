package app;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelElement;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimulatorApplication extends javafx.application.Application {
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final String APP_NAME = "Firefighter simulator";
  private static final int ROW_COUNT = 20;
  private static final int COLUMN_COUNT = 20;
  private static final int BOX_WIDTH = 30;
  private static final int BOX_HEIGHT = 30;
  private Stage primaryStage;
  private Parent view;
  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(true);
    this.primaryStage.sizeToScene();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    initializePrimaryStage(primaryStage);
    initializeView();
    showScene();
  }

  private void initializeView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    URL location = SimulatorApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loader.load();
    Controller controller = loader.getController();
    Map<ModelElement, Integer> config1 = new HashMap<>();
    Map<ModelElement, Integer> config2 = new HashMap<>();
    config1.put(ModelElement.FIRE, 6);
    config1.put(ModelElement.FIREFIGHTER, 10);
    config1.put(ModelElement.CLOUD, 6);
    config1.put(ModelElement.MOUNTAIN, 10);
    config1.put(ModelElement.ROCK, 15);
    config1.put(ModelElement.MOTORIZEDFIREFIGHTER, 5);
    config2.put(ModelElement.VIRUS, 5);
    config2.put(ModelElement.DOCTOR, 3);
    config2.put(ModelElement.PERSON, 40);
    controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,config2);
  }

  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
