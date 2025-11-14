package view;

import javafx.scene.paint.Color;
import model.Cloud;

public enum ViewElement {
  FIREFIGHTER(Color.BLUE), FIRE(Color.RED), EMPTY(Color.WHITE),CLOUD(Color.CYAN);
  final Color color;
  ViewElement(Color color) {
    this.color = color;
  }
}
