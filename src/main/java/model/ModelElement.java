package model;

import view.ViewElement;

public enum ModelElement {

  FIREFIGHTER(ViewElement.FIREFIGHTER,10), FIRE(ViewElement.FIRE,5) ,CLOUD(ViewElement.CLOUD,8),MOTORIZEDFIREFIGHTER(ViewElement.MOTORIZEDFIREFIGHTER,10),MOUNTAIN(ViewElement.MOUNTAIN,1),ROAD(ViewElement.ROAD,0),ROCK(ViewElement.ROCK,2),DOCTOR(ViewElement.DOCTOR, 10),
  VIRUS(ViewElement.VIRUS, 5),
  PERSON(ViewElement.PERSON, 8);
  public ViewElement view;
  private final int priority;
  ModelElement(ViewElement view,int priority){
    this.view=view;
    this.priority=priority;
  }
  public ViewElement getView(){
    return this.view;
  }
  public int getPriority(){
    return this.priority;
  }


}
