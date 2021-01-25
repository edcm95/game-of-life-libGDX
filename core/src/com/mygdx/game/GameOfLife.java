package com.mygdx.game;

import java.util.HashMap;
import java.util.List;

public class GameOfLife {

    private boolean running;
    private HashMap<Cell, Action> actions;

    public GameOfLife() {
        running = false;
        actions = new HashMap<>();
    }

    public void compute(List<Cell> cells) {
        if (!running) {
            return;
        }

        actions.clear();
        for (Cell cell : cells) {
            evaluateCell(cell);
        }

        for (Cell cell : actions.keySet()) {
            Action action = actions.get(cell);
            switch (action) {
                case DIE:
                    cell.erase();
                    break;
                case REVIVE:
                    cell.paint();
                    break;

            }
        }
    }

    private void evaluateCell(Cell cell) {
        int liveNeighbours = 0;
        for (Cell neighbour : cell.getNeighbours()) {
            if (neighbour == null) {
                continue;
            }

            if (neighbour.isPainted()) {
                liveNeighbours++;
            }
        }

        if (liveNeighbours < 2 || liveNeighbours > 3) {
            actions.put(cell, Action.DIE);
            return;
        }

        if (liveNeighbours == 2) {
            return;
        }
        actions.put(cell, Action.REVIVE);
    }

    private enum Action {
        DIE,
        REVIVE
    }

    public void toggle() {
        if (running) {
            running = false;
            return;
        }

        running = true;
    }
}
