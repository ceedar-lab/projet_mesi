import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {

    private final Integer SIZE = 64;

    private Integer offsetX = 0, offsetY = 0;
    private Integer stride = 4, strideX = 0, strideY = 0;
    private Integer direction = 40;
    private Integer originX = 5, originY = 5;
    private boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private Integer animX = 0, animY = 2;

    Perso perso = new Perso();

    public Panel() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    repaint();
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        offsetX+=strideX;
        offsetY+=strideY;

        // Carreaux
        for (int ax = 0; ax < 12; ax++) {
            for (int ay = 0; ay < 12; ay++) {
                g.setColor(Color.BLACK);
                g.drawRect(ax*SIZE, ay*SIZE, SIZE, SIZE);
            }
        }

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(2*SIZE, 6*SIZE, SIZE/2, SIZE/2);

        System.out.println("Stride : " +strideX+ " / StrideY : " +strideY+ " / OffsetX : " +offsetX+ " / OffsetY : " +offsetY+ " / Direction : " +direction);

        // Personnage
        try {
            g.setColor(new Color(0, 0, 0, .5f));
            g.fillOval(SIZE*originX + SIZE/4 + offsetX, SIZE*(originX+1) - SIZE/5 + offsetY, 32, 16);

            Image img;
            if (isMovingRight || isMovingLeft || isMovingUp || isMovingDown) {
                switch (direction) {
                    case 37:
                        img = ImageIO.read(new File("res/images/BODY_male-0" + animX + "-01.png"));
                        break;
                    case 38:
                        img = ImageIO.read(new File("res/images/BODY_male-0" + animY + "-00.png"));
                        break;
                    case 39:
                        img = ImageIO.read(new File("res/images/BODY_male-0" + animX + "-03.png"));
                        break;
                    default:
                        img = ImageIO.read(new File("res/images/BODY_male-0" + animY + "-02.png"));
                }
            } else {
                switch (direction) {
                    case 37:
                        img = ImageIO.read(new File("res/images/BODY_male-00-01.png"));
                        break;
                    case 38:
                        img = ImageIO.read(new File("res/images/BODY_male-00-00.png"));
                        break;
                    case 39:
                        img = ImageIO.read(new File("res/images/BODY_male-00-03.png"));
                        break;
                    default:
                        img = ImageIO.read(new File("res/images/BODY_male-00-02.png"));
                }
            }
            g.drawImage(img, SIZE * originX + offsetX, SIZE * originY + offsetY, this);

            if (offsetX%16 == 0) animX+=1;
            if (offsetY%16 == 0) animY+=1;
            if (animX == 9) animX = 0;
            if (animY == 9) animY = 2;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onKeyPressed(int keyCode) {
        direction = keyCode;
        System.out.println("Press / Code : " +keyCode);
        if (keyCode == 37 && !isMovingLeft) {
            strideX-=stride;
            isMovingLeft = true;
        }
        if (keyCode == 39 && !isMovingRight) {
            strideX+=stride;
            isMovingRight = true;
        }
        if (keyCode == 38 && !isMovingUp) {
            strideY-=stride;
            isMovingUp = true;
        }
        if (keyCode == 40 && !isMovingDown) {
            strideY+=stride;
            isMovingDown = true;
        }
    }

    public void onKeyReleased(int keyCode) {
        System.out.println("Release / Code : " +keyCode);
        if (keyCode == 37) {
            strideX+=stride;
            isMovingLeft = false;
        }
        if (keyCode == 39) {
            strideX-=stride;
            isMovingRight = false;
        }
        if (keyCode == 38) {
            strideY+=stride;
            isMovingUp = false;
        }
        if (keyCode == 40) {
            strideY-=stride;
            isMovingDown = false;
        }
    }


        /*
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int ax = 0; ax < 12; ax++) {
            for (int ay = 0; ay < 12; ay++) {
                g.setColor(Color.BLACK);
                g.drawRect(ax*SIZE, ay*SIZE, SIZE, SIZE);
            }
        }

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(SIZE*1, SIZE*2, SIZE, SIZE);

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(SIZE*5, SIZE*8, SIZE, SIZE);

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(SIZE*7, SIZE*1, SIZE, SIZE);

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(SIZE*8, SIZE*5, SIZE, SIZE);

        perso.paintComponent(g);

        if (perso.isMoving()) {
            perso = new Perso(perso.getX(), SIZE*5 + SIZE/2);
            perso.moveLeft();
            perso.moveRight();
        }
    }

    public void onKeyPressed(int keyCode) {
        System.out.println("Press / Code : " +keyCode);
        if (keyCode == 37) {
            perso.moveLeft();
            perso.setMoving(true);
        }
        if (keyCode == 39) {
            perso.moveRight();
            perso.setMoving(true);
        }
        if (keyCode == 38) {
            perso.moveUp();
            perso.setMoving(true);
        }
        if (keyCode == 40) {
            perso.moveDown();
            perso.setMoving(true);
        }
    }

    public void onKeyReleased(int keyCode) {
        System.out.println("Release / Code : " +keyCode);
        if (keyCode == 37 || keyCode == 39) {
            perso.setMoving(false);
        }
    }*/


}
