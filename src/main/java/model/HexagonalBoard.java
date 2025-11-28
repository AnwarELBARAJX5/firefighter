package model;

import java.util.List;
import util.Position;
import java.util.*;
public class HexagonalBoard extends AbstractBoard{
    public HexagonalBoard(int columnCount, int rowCount, Map<ModelElement, Integer> config) {
        super(columnCount, rowCount, config);
    }

    @Override
    public void initializeNeighbors() {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                List<Position> list = new ArrayList<>();
                if (col < columnCount - 1) list.add(positions[row][col + 1]);
                if (col > 0) list.add(positions[row][col - 1]);

                int offset = (row % 2 == 0) ? -1 : 0;
                if (row > 0) {
                    if (col + offset + 1 < columnCount) list.add(positions[row - 1][col + offset + 1]);
                    if (col + offset >= 0) list.add(positions[row - 1][col + offset]);

                }
                if (row < rowCount - 1) {
                    if (col + offset + 1 < columnCount) list.add(positions[row + 1][col + offset + 1]);
                    if (col + offset >= 0) list.add(positions[row + 1][col + offset]);

                }
                neighbors.put(positions[row][col], list);
            }
        }
    }
}
