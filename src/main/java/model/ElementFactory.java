package model;

import util.Position;

public class ElementFactory {

    public static Element create(ModelElement type, Position position) {
        return switch (type) {
            case FIRE -> new Fire(position);
            case FIREFIGHTER -> new FireFighter(position);
            case CLOUD -> new Cloud(position);
            case MOTORIZEDFIREFIGHTER -> new MotorizedFireFighter(position);
            case MOUNTAIN -> new Mountain(position);
            case ROAD -> new Road(position);
            case ROCK -> new Rock(position);
            case VIRUS ->new Virus(position);
            case PERSON ->new Person(position);
            case DOCTOR ->new Doctor(position);
        };
    }
}