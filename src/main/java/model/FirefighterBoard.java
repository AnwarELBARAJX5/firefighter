package model;

import util.Position;
import util.TargetStrategy;
import java.util.*;
import java.util.stream.Collectors;

import static view.ViewElement.MOTORIZEDFIREFIGHTER;


public class FirefighterBoard extends AbstractBoard{
  public FirefighterBoard(int columnCount, int rowCount,Map<ModelElement, Integer> config) {
    super(columnCount,rowCount,config);
  }

}