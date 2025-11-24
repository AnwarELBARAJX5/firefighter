package view;

import javafx.scene.paint.Color;

public class SquareGrid extends AbstractGrid{
    @Override
    protected void paintBox(int row, int column, Color color) {
        getGraphicsContext2D().setFill(color);
        getGraphicsContext2D().fillRect(column * boxWidth, row * boxHeight, boxWidth, boxHeight);
    }

}