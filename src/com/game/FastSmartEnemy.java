package com.game;

import java.awt.*;

public class FastSmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public FastSmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        for (int i = 0; i < handler.object.size(); i++)
            if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        if (x <= player.getX())
            velX = ((-1 / distance) * diffX) + 1.2f;
        else
            velX = ((-1 / distance) * diffX) - 1.2f;
        if (y <= player.getY())
            velY = ((-1 / distance) * diffY) + 1.2f;
        else
            velY = ((-1 / distance) * diffY) - 1.2f;

        if (x <= 0) x = 0;
        if (x >= Game.WIDTH - 16) x = Game.WIDTH;
        if (y <= 0) y = 0;
        if (y >= Game.HEIGHT - 16) y = Game.HEIGHT;
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.BLUE, 19, 19, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
