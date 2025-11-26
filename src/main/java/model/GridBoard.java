package model;

import java.util.*;
import util.Position;

public class GridBoard extends AbstractBoard{
    public GridBoard(int columnCount, int rowCount, Map<ModelElement, Integer> config) {
        super(columnCount,rowCount,config);
    }

    @Override
    public void initializeNeighbors() {
        for (int column = 0; column < columnCount; column++)
            for (int row = 0; row < rowCount; row++) {
                List<Position> list = new ArrayList<>();
                if (row > 0) list.add(positions[row - 1][column]);
                if (column > 0) list.add(positions[row][column - 1]);
                if (row < rowCount - 1) list.add(positions[row + 1][column]);
                if (column < columnCount - 1) list.add(positions[row][column + 1]);
                neighbors.put(positions[row][column], list);
            }
            }
}