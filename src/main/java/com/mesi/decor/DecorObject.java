package com.mesi.decor;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class DecorObject {

    protected Integer x;
    protected Integer y;

    protected Integer backgroundOffsetX;
    protected Integer backgroundOffsetY;
    protected Integer foregroundOffsetX;
    protected Integer foregroundOffsetY;

    protected BufferedImage foregroundImage;
    protected BufferedImage backgroundImage;

    protected String name;

    private Integer animPhase = 0;
    private Boolean animLaunched = false;
    protected BufferedImage[] decorObjectAnim;

    protected Rectangle hitbox;

    /**
     * constructor
     **/
    public DecorObject() {
    }

    /**
     * getter setter
     **/

    public BufferedImage getForegroundImage() { return foregroundImage; }
    public void setForegroundImage(BufferedImage foregroundImage) {
        this.foregroundImage = foregroundImage;
    }
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
//    public Rectangle getHitbox() {
//        return hitbox;
//    }

    public Rectangle getHitbox() {
        Rectangle rectangle = null;
        if (hitbox != null) {
            rectangle = new Rectangle(x + hitbox.x, y + hitbox.y, hitbox.width, hitbox.height);
        }
        return rectangle;
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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//\    public Integer getAnimPhase() {
//        return animPhase;
//    }
//
//    public void setAnimPhase(Integer animPhase) {
//        this.animPhase = animPhase;
//    }
//
//    public Boolean getAnimLaunched() {
//        return animLaunched;
//    }
//
//    public void setAnimLaunched(Boolean animLaunched) {
//        this.animLaunched = animLaunched;
//\    }
}
