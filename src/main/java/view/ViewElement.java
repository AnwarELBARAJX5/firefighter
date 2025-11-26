package view;

import javafx.scene.paint.Color;


public enum ViewElement {
  FIREFIGHTER(Color.BLUE), FIRE(Color.RED), EMPTY(Color.WHITE),CLOUD(Color.CYAN),MOTORIZEDFIREFIGHTER(Color.CADETBLUE),MOUNTAIN(Color.BROWN),ROAD(Color.GRAY),ROCK(Color.LIGHTSALMON),VIRUS(Color.LIMEGREEN), // Vert maladif
          PERSON(Color.YELLOW),   // Jaune (neutre)
          DOCTOR(Color.GREY);
  final Color color;
  ViewElement(Color color) {
    this.color = color;
  }
}