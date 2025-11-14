package model;

import util.Position;

import java.util.List;
import java.util.Random;
public class Cloud extends AbstractAgent{
    Random randomNumbers = new Random();
    public Cloud(Position startPosition){
        super(startPosition);
    }
    @Override
    public void update(BoardContext context) {
        int row=randomNumbers.nextInt(context.rowCount());
        int columnCount=randomNumbers.nextInt(context.columnCount());
        Position newPosition=new Position(row,columnCount);
        this.position=newPosition;
        List<Position> neighbors=context.getNeighbors(this.position);
        for(Position neighborsPos:neighbors){
            if(context.getFirePositions().contains(neighborsPos)){
                context.extinguish(neighborsPos);
            }
        }

    }
}
