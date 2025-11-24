package view;

import javafx.scene.paint.Color;

public class HexagonalGrid extends AbstractGrid {
    @Override
    protected void paintBox(int row, int column, Color color) {
        getGraphicsContext2D().setFill(color);
        // Dessin décalé
        double shiftX = (row % 2 == 0) ? 0 : boxWidth / 2.0;
        getGraphicsContext2D().fillOval(
                (column * boxWidth) + shiftX,
                row * boxHeight * 0.85,
                boxWidth * 0.9,
                boxHeight * 0.9
        );
    }
}
