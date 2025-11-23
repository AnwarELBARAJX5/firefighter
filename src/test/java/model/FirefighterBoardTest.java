package model;

import org.junit.jupiter.api.Test;
import util.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
public class FirefighterBoardTest {
  private Map<ModelElement, Integer> config=new HashMap<>();
  @BeforeEach
  public void initializer(){
    Map<ModelElement, Integer> config = new HashMap<>();
    config.put(ModelElement.FIRE, 6);
    config.put(ModelElement.FIREFIGHTER, 10);
    config.put(ModelElement.CLOUD, 6);
    config.put(ModelElement.MOUNTAIN, 10);
    config.put(ModelElement.ROCK, 15);
    config.put(ModelElement.MOTORIZEDFIREFIGHTER, 5);
  }

  @Test
  void testColumnCount(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, config);
    assertThat(board.columnCount()).isEqualTo(20);
  }
  @Test
  void testRowCount(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, config);
    assertThat(board.rowCount()).isEqualTo(10);
  }
  @Test
  void testStepNumber(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, config);
    for(int index = 0; index < 10; index++){
      assertThat(board.stepNumber()).isEqualTo(index);
      board.updateToNextGeneration();
    }
    assertThat(board.stepNumber()).isEqualTo(10);
  }
  @Test
  void testGetState_afterSet(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, config);
    Position position = new Position(1,2);
    assertThat(board.getState(position)).isEmpty();
    board.setState(List.of(ModelElement.FIRE), position);
    assertThat(board.getState(position)).containsExactly(ModelElement.FIRE);
  }

}
