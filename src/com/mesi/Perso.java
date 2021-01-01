package com.mesi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Perso extends JPanel {

    /** Attributs **/

    private final Integer SIZE = 64;

    private Integer x = SIZE*5;
    private Integer y = SIZE*5;
    private Integer direction = 40;
    private Integer stride = 4;

    private boolean isMovingX = false;

    /** Constructeur **/

    public Perso() {}

    public Perso(Integer x, Integer y, Integer direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /** Getters / Setters **/

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isMovingX() {
        return isMovingX;
    }

    public void setMovingX(boolean moving) {
        isMovingX = moving;
    }

    /** Méthodes **/

    public void moveLeft() {
        this.x -= stride;
    }
    public void moveRight() {
        this.x += stride;
    }
    public void moveUp() {
        y -= stride;
    }
    public void moveDown() {
        y += stride;
    }


}
