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
import java.util.Scanner;

public class SimulatorApplication extends javafx.application.Application {
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final String APP_NAME = "Firefighter simulator";
  private static final int ROW_COUNT = 20;
  private static final int COLUMN_COUNT = 20;
  private static final int BOX_WIDTH = 30;
  private static final int BOX_HEIGHT = 30;
  private static final Map<ModelElement, Integer> userConfig = new HashMap<>();
  private static boolean useHexagonal = false;
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
    controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,userConfig, useHexagonal);
  }

  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("==================================");
    System.out.println("   SIMULATEUR - MENU PRINCIPAL    ");
    System.out.println("==================================");


    System.out.println("\n---> Quelle Grille voulez-vous ?");
    System.out.println("1. Grille Carrée (Classique)");
    System.out.println("2. Grille Hexagonale (Moderne)");
    System.out.print("Votre choix (1 ou 2) : ");
    int gridChoice = askInt(scanner,"");
    useHexagonal = (gridChoice == 2);

    System.out.println("\n---> Quel Scénario voulez-vous jouer ?");
    System.out.println("1. Pompiers vs Feu");
    System.out.println("2. Pandémie (Virus)");
    System.out.print("Votre choix (1 ou 2) : ");
    int gameChoice = askInt(scanner," ");

    if (gameChoice == 1) {
      System.out.println("\n--- CONFIGURATION POMPIERS ---");
      userConfig.put(ModelElement.FIRE, askInt(scanner, "Nombre de Feux "));
      userConfig.put(ModelElement.FIREFIGHTER, askInt(scanner, "Nombre de Pompiers "));
      userConfig.put(ModelElement.MOTORIZEDFIREFIGHTER, askInt(scanner, "Nombre de Pompiers Motorisés "));
      userConfig.put(ModelElement.CLOUD, askInt(scanner, "Nombre de Nuages "));
      userConfig.put(ModelElement.ROCK, askInt(scanner, "Nombre de Rochers "));
      userConfig.put(ModelElement.MOUNTAIN, askInt(scanner, "Nombre de Montagnes "));
      userConfig.put(ModelElement.ROAD, askInt(scanner, "Nombre de Routes "));

      System.out.println("Lancement de la simulation Pompiers...");

    } else {
      System.out.println("\n--- CONFIGURATION VIRUS ---");
      userConfig.put(ModelElement.VIRUS, askInt(scanner, "Nombre de Virus initiaux "));
      userConfig.put(ModelElement.PERSON, askInt(scanner, "Population initiale "));
      userConfig.put(ModelElement.DOCTOR, askInt(scanner, "Nombre de Médecins "));
      System.out.println("Lancement de la simulation Virus...");
    }
    launch(args);
  }
  private static int askInt(Scanner s, String message) {
    System.out.print(message + " : ");
    while (!s.hasNextInt()) {
      s.next();
      System.out.print("Erreur. " + message );
    }
    return s.nextInt();
  }
}

