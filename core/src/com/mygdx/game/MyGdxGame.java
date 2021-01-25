package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {

    public static int fps = 60;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 720;
    public static final int CELL_SIZE = 20;

    public static float zOrDepth = 1f;

    private GameOfLife gol;

    private ShapeRenderer shapeRenderer;
    private Grid grid;
    private MemoryUsage memory;

    @Override
    public void create() {
        memory = new MemoryUsage();
        shapeRenderer = new ShapeRenderer();
        grid = new Grid();
        grid.init();

        gol = new GameOfLife();
    }

    public void update() {
        memory.update();
        gol.compute(grid.getCells());
    }

    @Override
    public void render() {
        processInput();
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw all cells
        for (Cell cell : grid.getCells()) {
            cell.draw(shapeRenderer);
        }

        memory.draw(shapeRenderer);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gol.toggle();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            for (Cell cell : grid.getCells()) {
                cell.erase();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Cell cell = grid.getCellInPosition(Gdx.input.getX(), MyGdxGame.HEIGHT - Gdx.input.getY());
            if (cell != null && !cell.isPainted()) {
                cell.paint();
            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            Cell cell = grid.getCellInPosition(Gdx.input.getX(), MyGdxGame.HEIGHT - Gdx.input.getY());
            if (cell != null && cell.isPainted()) {
                cell.erase();
            }
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.MIDDLE)) {
            Cell cell = grid.getCellInPosition(Gdx.input.getX(), MyGdxGame.HEIGHT - Gdx.input.getY());
            Cell[] neighbours = cell.getNeighbours();
            int[] indexes = new int[]{1, 4, 5, 6, 7};
            for (int i : indexes) {
                Cell n = neighbours[i];
                if (n == null) {
                    continue;
                }
                n.paint();
            }
        }
    }
}
