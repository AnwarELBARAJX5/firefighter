package view;

import javafx.scene.paint.Color;

public class HexagonalGrid extends AbstractGrid {
    private static final double GAP = 4.0;
    @Override
    protected void paintBox(int row, int column, Color color) {
        getGraphicsContext2D().setFill(color);
        double shiftX = (row % 2 == 0) ? 0 : boxWidth / 2.0;
        double xLogique = (column * boxWidth) + shiftX;
        double yLogique = row * boxHeight * 0.75;
        double centerX = xLogique + boxWidth / 2.0;
        double centerY = yLogique + boxHeight / 2.0;
        double drawWidth = Math.max(1, boxWidth - GAP);
        double drawHeight = Math.max(1, boxHeight - GAP);
        double halfW = drawWidth / 2.0;
        double halfH = drawHeight / 2.0;
        double quarterH = drawHeight / 4.0;

        double[] xPoints = {
                centerX,
                centerX + halfW,
                centerX + halfW,
                centerX,
                centerX - halfW,
                centerX - halfW
        };

        double[] yPoints = {
                centerY - halfH,
                centerY - halfH + quarterH,
                centerY + halfH - quarterH,
                centerY + halfH,
                centerY + halfH - quarterH,
                centerY - halfH + quarterH
        };

        getGraphicsContext2D().fillPolygon(xPoints, yPoints, 6);
    }

    @Override
    public void setDimensions(int columnCount, int rowCount, int boxWidth, int boxHeight) {
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        super.setWidth((boxWidth * columnCount) + (boxWidth / 2.0));
        super.setHeight(boxHeight * (1 + (rowCount - 1) * 0.75));
    }

    @Override
    public void paintLines() {
    }

    @Override
    protected void clearBox(int row, int column) {
    }
}