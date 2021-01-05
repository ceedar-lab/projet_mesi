package com.mesi.panels;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;

public class Test2 extends JPanel {

    public Test2() {
        setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 32, 32, 32);
    }
}