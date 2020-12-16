import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Test");
        Panel panel = new Panel();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                panel.onKeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                panel.onKeyReleased(e.getKeyCode());
            }
        });

        frame.setContentPane(panel);
        frame.setUndecorated(true);
        frame.setSize(768, 768);
        frame.setLocationRelativeTo((Component)null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
