package com.mesi.decor;

import com.mesi.params.Images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class DecorObject {

    protected Integer x;
    protected Integer y;

    protected Integer backgroundOffsetX;
    protected Integer backgroundOffsetY;
    protected Integer foregroundOffsetX;
    protected Integer foregroundOffsetY;

    protected BufferedImage forgroundImage;
    protected BufferedImage backgroundImage;


    protected Rectangle hitbox;


    /**
     * constructor
     **/
    public DecorObject() {
    }


    /**
     * getter setter
     **/

    public BufferedImage getForgroundImage() {
        return forgroundImage;
    }

    public void setForgroundImage(BufferedImage forgroundImage) {
        this.forgroundImage = forgroundImage;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }


    public Integer getBackgroundOffsetX() {
        return backgroundOffsetX;
    }

    public void setBackgroundOffsetX(Integer backgroundOffsetX) {
        this.backgroundOffsetX = backgroundOffsetX;
    }

    public Integer getBackgroundOffsetY() {
        return backgroundOffsetY;
    }

    public void setBackgroundOffsetY(Integer backgroundOffsetY) {
        this.backgroundOffsetY = backgroundOffsetY;
    }

    public Integer getForegroundOffsetX() {
        return foregroundOffsetX;
    }

    public void setForegroundOffsetX(Integer foregroundOffsetX) {
        this.foregroundOffsetX = foregroundOffsetX;
    }

    public Integer getForegroundOffsetY() {
        return foregroundOffsetY;
    }

    public void setForegroundOffsetY(Integer foregroundOffsetY) {
        this.foregroundOffsetY = foregroundOffsetY;
    }


}
