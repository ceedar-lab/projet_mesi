package com.mesi.panels.maps;

import com.mesi.decor.DecorObject;
import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel
{

    /**********  Attributes  **********/

    private Integer x = 0;
    private Integer y = 0;

    private Rectangle hitbox;

    private boolean traversable;
    private boolean teleport;

    private DecorObject decorObject;

    /**********  Constructors  **********/

    public Tile(Integer x, Integer y)
    {
        if (traversable)
        {
            setBackground(Color.RED);
        }
        this.x           = x;
        this.y           = y;
        this.traversable = true;
        this.teleport    = false;
    }

    /**********  Getters / Setters  **********/

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isTraversable()
    {
        return traversable;
    }

    public void setTraversable(boolean traversable)
    {
        this.traversable = traversable;
    }

    public boolean isTeleport()
    {
        return teleport;
    }

    public void setTeleport(boolean teleport)
    {
        this.teleport = teleport;
    }

    public Rectangle getHitbox()
    {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox)
    {
        Rectangle rect = hitbox;
        rect.x += getX();
        rect.y += getY();
        this.hitbox = rect;
    }

    public DecorObject getDecorObject()
    {
        return decorObject;
    }

    public void setDecorObject(DecorObject decorObject)
    {
        decorObject.setX(x);
        decorObject.setY(y);
        decorObject.initPositions();
        this.decorObject = decorObject;
    }

    /**********  Methods  **********/

    /**
     * Récupère toutes bornes de la case.
     *
     * @return Rectangle.
     */
    public Rectangle getTeleportBounds()
    {
        return new Rectangle(x, y, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    /**
     * Récupère les bornes ouest de la case.
     *
     * @return Rectangle.
     */
    public Rectangle getLeftBound()
    {
        return new Rectangle(x, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2 * Constant.BOUND_THICKNESS);
    }

    /**
     * Récupère les bornes est de la case.
     *
     * @return Rectangle.
     */
    public Rectangle getRightBound()
    {
        return new Rectangle(x + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2 * Constant.BOUND_THICKNESS);
    }

    /**
     * Récupère les bornes nord de la case.
     *
     * @return Rectangle.
     */
    public Rectangle getUpperBound()
    {
        return new Rectangle(x + Constant.BOUND_THICKNESS, y, Constant.TILE_SIZE - 2 * Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS);
    }

    /**
     * Récupère les bornes sud de la case.
     *
     * @return Rectangle.
     */
    public Rectangle getLowerBound()
    {
        return new Rectangle(x + Constant.BOUND_THICKNESS, y + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2 * Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS);
    }
}
