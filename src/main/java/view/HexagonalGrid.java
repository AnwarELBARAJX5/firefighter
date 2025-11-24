package view;

import javafx.scene.paint.Color;

public class HexagonalGrid extends AbstractGrid {
    @Override
    protected void paintBox(int row, int column, Color color) {
        getGraphicsContext2D().setFill(color);
        double shiftX = (row % 2 == 0) ? 0 : boxWidth/ 2.0;
        getGraphicsContext2D().fillOval(
                (column * boxWidth) + shiftX,
                row * boxHeight * 0.85,
                boxWidth * 0.9,
                boxHeight * 0.9
        );
    }
    @Override
    public void paintLines() {

    }
    @Override
    public void setDimensions(int columnCount, int rowCount, int boxWidth, int boxHeight) {
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        super.setWidth((boxWidth * columnCount) + (boxWidth / 2.0));
        super.setHeight(boxHeight * rowCount);
    }
    @Override
    protected void clearBox(int row, int column) {
        // On recalcule les mêmes coordonnées que pour le dessin (paintBox)
        double shiftX = (row % 2 == 0) ? 0 : boxWidth / 2.0;
        double x = (column * boxWidth) + shiftX;
        double y = row * boxHeight * 0.85;

        // On efface une zone un peu plus large pour être sûr de tout nettoyer
        // (Le +1 et le boxWidth sont pour couvrir les marges de dessin)
        getGraphicsContext2D().clearRect(x, y, boxWidth, boxHeight);
    }
}
