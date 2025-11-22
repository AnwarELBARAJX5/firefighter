classDiagram
    direction TB

    class BoardContext {
        <<interface>>
        +getNeighbors(p: Position) List~Position~
        +getFirePositions() Set~Position~
        +extinguish(p: Position)
        +createFire(p: Position)
        +isOccupied(p: Position) boolean
    }

    class FirefighterBoard {
        -agents: List~AbstractAgent~
        -neighbors: Map~Position, List~Position~~
        +updateToNextGeneration()
        +initializeElements()
    }

    class AbstractAgent {
        <<abstract>>
        #position: Position
        +update(context: BoardContext)*
        +getType()* ModelElement
    }

    class Fire {
        +update(context: BoardContext)
    }

    class Firefighter {
        -strategy: TargetStrategy
        +update(context: BoardContext)
    }

    class Cloud {
        +update(context: BoardContext)
    }

    %% Relations
    FirefighterBoard ..|> BoardContext : Implements
    FirefighterBoard o-- "0..*" AbstractAgent : Composes

    Fire --|> AbstractAgent : Extends
    Firefighter --|> AbstractAgent : Extends
    Cloud --|> AbstractAgent : Extends

    Firefighter ..> BoardContext : Uses
    Fire ..> BoardContext : Uses