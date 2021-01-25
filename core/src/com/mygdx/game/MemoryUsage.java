package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class MemoryUsage {

    private Rectangle total;
    private final Color totalColor = Color.CYAN;
    private Rectangle used;
    private final Color usedColor = Color.FIREBRICK;

    public MemoryUsage() {
        this.total = new Rectangle(0, 0, 10, (Runtime.getRuntime().totalMemory() / 1_000_000f));
        this.used = new Rectangle(0, 0, 10,((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1_000_000f));
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // draw total
        shapeRenderer.setColor(totalColor);
        shapeRenderer.box(total.x,
                total.y,
                MyGdxGame.zOrDepth,
                total.width,
                total.height,
                MyGdxGame.zOrDepth
        );

        // draw used
        shapeRenderer.setColor(usedColor);
        shapeRenderer.box(used.x,
                used.y,
                MyGdxGame.zOrDepth,
                used.width,
                used.height,
                MyGdxGame.zOrDepth
        );

        shapeRenderer.end();
    }

    public void update() {
        used.setHeight((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1_000_000f);
    }
}
