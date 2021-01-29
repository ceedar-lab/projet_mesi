package com.mesi.decor.collectableItem;

import com.mesi.decor.DecorObject;

import java.awt.*;

public class CollectableItem extends DecorObject {

    protected Rectangle interactionBox;

    public CollectableItem() {
        super();
    }

    public Rectangle getInteractionBox() {
        Rectangle rectangle = null;
        if (interactionBox != null) {
            rectangle = new Rectangle(x + interactionBox.x, y + interactionBox.y, interactionBox.width, interactionBox.height);
        }
        return rectangle;
    }

    public void setInteractionBox(Rectangle interactionBox) {

        this.interactionBox = interactionBox;
    }
}
