package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Cell {

    //math representation of size and position
    private Rectangle rectangle;
    private Color color;
    private boolean painted;
    private Cell[] neighbours;

    public Cell(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);
        painted = false;
        color = Color.LIGHT_GRAY;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);

        ShapeRenderer.ShapeType shapeType = (painted) ? ShapeRenderer.ShapeType.Filled : ShapeRenderer.ShapeType.Line;
        shapeRenderer.begin(shapeType);

        shapeRenderer.box(rectangle.x,
                rectangle.y,
                MyGdxGame.zOrDepth,
                rectangle.width,
                rectangle.height,
                MyGdxGame.zOrDepth
        );

        shapeRenderer.end();
    }

    public void paint() {
        painted = true;
    }

    public boolean isPainted() {
        return painted;
    }

    public void erase() {
        painted = false;
    }

    public float getX() {
        return rectangle.getX();
    }

    public float getY() {
        return rectangle.getY();
    }

    public void setNeighbours(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public Cell[] getNeighbours() {
        return neighbours;
    }
}
