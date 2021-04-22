package com.mesi.decor.collectable;

import com.mesi.decor.DecorObject;

import java.awt.*;

public abstract class CollectableItem extends DecorObject {

    /**********  Attributes  **********/

    protected Rectangle interactionBox;
    protected Integer quantity;
    protected String category;

    /**********  Constructors  **********/

    protected CollectableItem() {
        super();
    }

    /**********  Getters / Setters  **********/

    public Rectangle getInteractionBox() {
        Rectangle rectangle = null;
        if (interactionBox != null) {
            rectangle = new Rectangle(x + interactionBox.x, y + interactionBox.y, interactionBox.width, interactionBox.height);
        }
        return rectangle;
    }
    public void setInteractionBox(Rectangle interactionBox) { this.interactionBox = interactionBox; }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
