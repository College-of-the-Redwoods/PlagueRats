classDiagram
class Command {
+execute()
+undo()
}

    class MoveCommand {
        -character: Character
        -x: int
        -y: int
        -prevX: int
        -prevY: int
        +execute()
        +undo()
    }

    class AttackCommand {
        -character: Character
        -target: Enemy
        +execute()
        +undo()
    }

    class Character {
        -x: int
        -y: int
        +moveTo(x: int, y: int)
        +attack(target: Enemy)
    }

    class Enemy {
        -health: int
        +healLastDamage()
    }

    class CommandInvoker {
        -commandHistory: Stack<Command>
        +executeCommand(cmd: Command)
        +undoLastCommand()
    }

    Command <|-- MoveCommand
    Command <|-- AttackCommand

    MoveCommand --> Character : "controls"
    AttackCommand --> Character : "controls"
    AttackCommand --> Enemy : "targets"

    CommandInvoker --> Command : "executes"
    Character --> Enemy : "attacks"