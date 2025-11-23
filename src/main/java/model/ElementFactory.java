package model;

import util.Position;

public class ElementFactory {

    public static Element create(ModelElement type, Position position) {
        switch (type) {
            case FIRE:
                return new Fire(position);
            case FIREFIGHTER:
                return new FireFighter(position);
            case CLOUD:
                return new Cloud(position);
            case MOTORIZEDFIREFIGHTER:
                return new MotorizedFireFighter(position);
            case MOUNTAIN:
                return new Mountain(position);
            case ROAD:
                return new Road(position);
            case ROCK:
                return new Rock(position);
            default:
                return null; // Ou throw new IllegalArgumentException("Type inconnu");
        }
    }
}