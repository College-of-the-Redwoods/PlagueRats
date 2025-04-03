package edu.redwoods.cis18.plaguerats;
// Main class to simulate game logic
public class GameSimulation {
    public static void main(String[] args) {
        // Create character and enemy
        Character hero = new Character(0, 0);
        Enemy enemy = new Enemy(100);

        // Create invoker for commands
        CommandInvoker invoker = new CommandInvoker();

        // Create and execute move and attack commands
        Command moveCommand = new MoveCommand(hero, 5, 5);
        invoker.executeCommand(moveCommand);

        Command attackCommand = new AttackCommand(hero, enemy);
        invoker.executeCommand(attackCommand);

        // Undo last command (attack)
        invoker.undoLastCommand();

        // Undo move command
        invoker.undoLastCommand();
    }
}
}
