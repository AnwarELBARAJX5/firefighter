package model;

import view.ViewElement;

public enum ModelElement {

  FIREFIGHTER(ViewElement.FIREFIGHTER), FIRE(ViewElement.FIRE) ,CLOUD(ViewElement.CLOUD),MOTORIZEDFIREFIGHTER(ViewElement.MOTORIZEDFIREFIGHTER),MOUNTAIN(ViewElement.MOUNTAIN),ROAD(ViewElement.ROAD),ROCK(ViewElement.ROCK);
  public ViewElement view;
  ModelElement(ViewElement view){
    this.view=view;
  }
  public ViewElement getView(){
    return this.view;
  }


}
