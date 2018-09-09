import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*<applet code="GameFrame.class" width="710" height="510"> </applet>  */

public class GameFrame extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable {
    int frameXLimit = 700;
    int frameYLimit = 500;

    boolean isFirstFrame = true;
    Font font;

    /**************Safe Modify****************/
    float fps = (float) 4;  // DEFAULT GAME SPEED
    int initialLength = 3;  // DEFAULT INITIAL SNAKE LENGTH

    Color headColor = Color.blue;
    Color bodyColor = Color.black;
    Color scoreColor = Color.white;
    Color backGroundColor = Color.gray;
    Color borderColor = Color.black;
    Color textColor = Color.green;
    Color foodColor = Color.green;

    /*****************************************/
    Snake snake;
    Food food;
    Thread theFrameThread;
    Thread snakeThread;


    int fpsSleepTime = Math.round(1000 / fps);

    @Override
    public void run() {
//            System.out.println("frame is running");

        snakeThread.start();
        while (!snake.gameOver) {
//            DEFAULT MOVE
            if (!snake.moveMadeByPlayer) {
                snake.moveSnake();
                snake.frameChance = true;
            }

//            PAINTING FRAME IF SNAKE CHANCE
            if (snake.frameChance) {
                repaint();
                try {
                    Thread.sleep(fpsSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void paint(Graphics g) {

        Graphics2D G = (Graphics2D) g;

//        BORDER PRINTING
        g.setColor(borderColor);
        g.drawRect(1, 1, frameXLimit, frameYLimit);

//        FIRST SPECIAL FRAMES
        if (isFirstFrame) {

            g.setColor(textColor);
            font = new Font(Font.MONOSPACED, Font.BOLD, 40);
            g.setFont(font);

//            GAME BEGINS COUNTDOWN
            for (int i = 0; i < 7; i++) {
                g.clearRect(10, 10, frameXLimit - 10, frameYLimit - 10);

                if (i == 0)
                    g.drawString("GAME BEGINS IN", Math.round(frameXLimit / 2) - 170, Math.round(frameYLimit / 2));
                else
                    g.drawString(Integer.toString(6 - i), Math.round(frameXLimit / 2), Math.round(frameYLimit / 2));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isFirstFrame = false;
        }


//        CHECKING IF FOOD IS EATEN
        if (!food.isFoodValid()) {
            snake.addTail();
            food.createFood();
        }

        if (snake.isAliveee()) {
//            FOOD PRINTING
            g.setColor(foodColor);
            G.fillOval(food.foodX, food.foodY, 10, 10);

//            BODY PRINTING
            g.setColor(Color.black);
            for (int i = 0; i < snake.x.size(); i++) {
                if (i == 0)
                    g.setColor(headColor);
                else
                    g.setColor(bodyColor);
                G.fillOval(snake.x.get(i), snake.y.get(i), 10, 10);
            }

//            SCORE PRINTING
            g.setColor(scoreColor);
            font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
            g.drawString(Integer.toString((snake.x.size() - initialLength) * 7), 30, 30);
        }

//        GAME OVER PRINTING
        else  {
            snake.gameOver = true;
            font = new Font(Font.MONOSPACED, Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.green);
            g.drawString("GAME OVER", Math.round(frameXLimit / 2) - 120, Math.round(frameYLimit / 2));
        }

//        RESETS AFTER EACH FRAME
        snake.frameChance = false;
        snake.snakeChance = true;
        snake.moveMadeByPlayer = false;
        snake.keyPressedInSnake = false;

//        System.out.println("snake's chance"); //DEBUG
    }

    @Override
    public void init() {
//        System.out.println("initializing game applet"); //DEBUG
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        addKeyListener(snake);

        this.setSize(new Dimension(frameXLimit, frameYLimit));

        snake = new Snake(initialLength, frameXLimit, frameYLimit);
        snakeThread = new Thread(snake);

        theFrameThread = new Thread(this);
        snake.theFrameThread = theFrameThread;

        food = new Food(snake, this);

        setBackground(backGroundColor);
    }

    @Override
    public void start() {
        theFrameThread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("snake chance now inn frame"); //DEBUG
        if (snake.snakeChance && !snake.keyPressedInSnake && !snake.moveMadeByPlayer) {
            snake.keyPressedInSnake = true;
//            System.out.println("key pressed in frame" + e.getKeyChar()); //DEBUG
            snake.setDirection(e.getKeyChar());
            if (snake.snakeChance && snake.keyPressedInSnake) {
//                System.out.println("moving by player's will - " + snake.direction); //DEBUG
                snake.moveSnake();
                snake.snakeChance = false;
                snake.frameChance = true;
                snake.keyPressedInSnake = true;
                snake.moveMadeByPlayer = true;
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
