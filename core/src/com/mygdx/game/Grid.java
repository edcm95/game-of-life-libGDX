package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;

public class Grid {

    private LinkedList<Cell> cells;
    private final int cellSize = MyGdxGame.CELL_SIZE;

    public Grid() {
        cells = new LinkedList<>();
    }

    public void init() {
        cells.clear();

        int numberOfCellsX = MyGdxGame.WIDTH / cellSize;
        int numberOfCellsY = MyGdxGame.HEIGHT / cellSize;

        for (int x = 0; x < numberOfCellsX; x++) {

            for (int y = 0; y < numberOfCellsY; y++) {
                float posX = x * cellSize;
                float posY = y * cellSize;

                Cell cell = new Cell(posX, posY, cellSize, cellSize);
                cells.add(cell);
            }
        }

        // for each cell define the neighbours
        for (Cell cell : cells) {
            cell.setNeighbours(this.getNeighbours(cell));
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getCellInPositionFromMouse(float x, float y) {
         // mouse position is reversed
        return getCellInPosition(x, y);
    }

    public Cell getCellInPosition(float x, float y) {
        for (Cell cell : cells) {
            if ((x >= cell.getX() && x <= cell.getX() + cellSize) &&
                    (y >= cell.getY() && y <= cell.getY() + cellSize)) {

                return cell;
            }
        }
        return null;
    }

    public Cell[] getNeighbours(Cell cell) {
        Cell[] neighbours = new Cell[8];
        final float step = cellSize >> 1;

        final float originX = cell.getX() + step;
        final float originY = cell.getY() + step;

        // up left
        float ulX = originX - cellSize;
        float ulY = originY + cellSize;
        neighbours[0] = getCellInPosition(ulX, ulY);

        // up
        float uY = originY + cellSize;
        neighbours[1] = getCellInPosition(originX, uY);

        // up right
        float urX = originX + cellSize;
        float urY = originY + cellSize;
        neighbours[2] = getCellInPosition(urX, urY);

        // left
        float lx = originX - cellSize;
        neighbours[3] = getCellInPosition(lx, originY);

        // right
        float rx = originX + cellSize;
        neighbours[4] = getCellInPosition(rx, originY);

        // down left
        float dlX = originX + cellSize;
        float dlY = originY - cellSize;
        neighbours[5] = getCellInPosition(dlX, dlY);

        // down
        float dY = originY - cellSize;
        neighbours[6] = getCellInPosition(originX, dY);

        // down right
        float drX = originX - cellSize;
        float drY = originY - cellSize;
        neighbours[7] = getCellInPosition(drX, drY);

        return neighbours;
    }
}