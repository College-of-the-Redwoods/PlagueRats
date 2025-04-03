package edu.redwoods.cis18.plaguerats;

// Abstract Command class
abstract class Command {
    public abstract void execute();
    public abstract void undo();
}

// Character class with movement and attack functionalities
class Character {
    private int x, y;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveTo(int x, int y) {
        System.out.println("Character moves to (" + x + ", " + y + ")");
        this.x = x;
        this.y = y;
    }

    public void attack(Enemy target) {
        System.out.println("Character attacks the enemy");
        target.healLastDamage(); // Assuming attack may heal target for demo purposes.
    }
}

// Enemy class with health and recovery functionality
class Enemy {
    private int health;

    public Enemy(int health) {
        this.health = health;
    }

    public void healLastDamage() {
        System.out.println("Enemy heals from last damage.");
        // Some logic for healing (or could be recovering health points)
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Enemy takes " + damage + " damage. Remaining health: " + health);
    }
}

// MoveCommand to move a character
class MoveCommand extends Command {
    private Character character;
    private int x, y;
    private int prevX, prevY;

    public MoveCommand(Character character, int x, int y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        // Save the previous position to undo the move
        prevX = character.x;
        prevY = character.y;
        character.moveTo(x, y);
    }

    @Override
    public void undo() {
        // Revert to previous position
        character.moveTo(prevX, prevY);
    }
}

// AttackCommand to make a character attack an enemy
class AttackCommand extends Command {
    private Character character;
    private Enemy target;

    public AttackCommand(Character character, Enemy target) {
        this.character = character;
        this.target = target;
    }

    @Override
    public void execute() {
        character.attack(target);
    }

    @Override
    public void undo() {
        // In this simple model, there's no undo for an attack (but you could implement it)
        System.out.println("Undoing attack (could restore health or status).");
    }
}

// CommandInvoker to store and execute commands
class CommandInvoker {
    private Stack<Command> commandHistory = new Stack<>();

    public void executeCommand(Command cmd) {
        cmd.execute();
        commandHistory.push(cmd);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}





