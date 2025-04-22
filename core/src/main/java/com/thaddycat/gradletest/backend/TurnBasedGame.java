package com.thaddycat.gradletest.backend;

import java.util.List;

public class TurnBasedGame {

    public TurnBasedGame() {
        MapGenerator.generateCellArray(5, 5);
    }


    public void executeStep() {
        System.out.println("[DEBUG] TurnBasedGame.executeStep() called.");

        for (Character c : Character.getCharacterArrayList()) {
            CommandManager cm = c.getCommandManager();
            if (cm == null) {
                System.err.println("CommandManager is null for " + c.getName());
                continue;
            }
            // TEMP: add a fake command for testing purposes
            cm.addCommand(new Command() {
                @Override
                public void execute() {
                    System.out.println("[TEST] Executed dummy command for " + c.getName());
                }

                @Override
                public void undo() {
                    System.out.println("[TEST] Undid dummy command for " + c.getName());
                }
                @Override
                public void reset() {
                    // No-op for dummy command
                }
            });

            System.out.println("Executing CommandManager for " + c.getName());
            cm.execute();
        }

        System.out.println("[DEBUG] Game Step Successfully Executed.\n");
    }

    public void enqueueCommand(Command command) {
        /* Remove any existing command for this character
        commandQueue.removeIf(cmd -> cmd.getCharacter() == newCommand.getCharacter());
        commandQueue.add(newCommand); */


        if (command instanceof MoveCommand) {
            MoveCommand move = (MoveCommand) command;
            move.getCharacter().getCommandManager().addCommand(move);
        } else if (command instanceof AttackCommand) {
            AttackCommand attack = (AttackCommand) command;
            attack.getAttacker().getCommandManager().addCommand(attack);
        } else {
            System.err.println("Unknown command type: " + command.getClass().getSimpleName());
        }
    }

    public void undoStep() {
        System.out.println("[DEBUG] TurnBasedGame.undoStep() called.");
        for (Character c : Character.getCharacterArrayList()) {
            CommandManager cm = c.getCommandManager();
            if (cm == null) {
                System.err.println("CommandManager is null for " + c.getName());
                continue;
            }
            System.out.println("[DEBUG] " + c.getName() + " undoing CommandManager");
            cm.undo();
        }

        System.out.println("[DEBUG] Game Step Successfully Undone.\n");
    }
    public List<Command> getCommandQueue() {
        List<Command> allQueued = new java.util.ArrayList<>();
        for (Character c : Character.getCharacterArrayList()) {
            CommandManager cm = c.getCommandManager();
            if (cm != null) {
                allQueued.addAll(cm.getQueuedCommands()); // ← call to per-character queue
            }
        }
        return allQueued;
    }


}
